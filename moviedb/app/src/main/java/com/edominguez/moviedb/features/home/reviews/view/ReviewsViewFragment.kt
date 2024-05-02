package com.edominguez.moviedb.features.home.reviews.view

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.common.utils.Dialogs
import com.edominguez.moviedb.core.common.utils.EMPTY_STRING
import com.edominguez.moviedb.core.common.utils.Functions
import com.edominguez.moviedb.core.common.utils.MAX_QUANTITY_OF_STARS
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.core.permissions.PermissionHelperEvents
import com.edominguez.moviedb.core.permissions.PermissionsDelegate
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.ReviewsViewFragmentBinding
import com.edominguez.moviedb.features.home.movies.view.HomeActivity
import com.edominguez.moviedb.features.home.reviews.viewmodel.UploadReviewsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsViewFragment : BaseFragment<ReviewsViewFragmentBinding>() {

    private val permissionsDelegate: PermissionsDelegate by inject()
    private val uploadReviewsViewModel: UploadReviewsViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "ReviewsViewFragment"

    override fun listenToObserver() {
        observe(uploadReviewsViewModel.uploadReviewsVMDelegate.showUnknownError, this::onError)
        observe(uploadReviewsViewModel.uploadReviewsVMDelegate.onReviewUploadedResponse, this::onReviewUploadedResponse)
    }

    // ---- Initialize your view here

    override fun init() {
        setOnClickListeners()
        communication.onFragmentEvent(ProtocolAction.OnSelectedMenuItem(HomeActivity.BOTTOM_MENU_ITEM_UPLOAD_REVIEWS))
    }

    // ----- Logic Methods

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        uri?.let {
            bindingView.movieReview.imgUpload.setImageURI(it)
            uploadReviewsViewModel.setImagePath(it)
        }
    }

    private fun setOnClickListeners() {

        bindingView.movieReview.btnClear.setOnClickListener{
            resetUI()
        }

        bindingView.movieReview.btnSendReview.setOnClickListener{
            attemptToUploadReview()
        }

        bindingView.movieReview.imgUpload.setOnClickListener {
            startImagePickerFlow()
        }

    }

    private fun attemptToUploadReview() {
        var haveError = false

        if(bindingView.movieReview.edtReview.text.toString().isBlank()) {
            haveError = true
        }

        if(uploadReviewsViewModel.getImagePath() == null) {
            haveError = true
        }

        if(!haveError) {
            uploadReviewsViewModel.uploadReview(Functions.getDeviceID(requireContext()), bindingView.movieReview.edtReview.text.toString(), bindingView.movieReview.ratingBar.rating)
        }else{
            showDialog(getString(R.string.txt_error), getString(R.string.txt_missing_data))
        }
    }

    private fun onReviewUploadedResponse(isSaved:Boolean) {
        if(isSaved) {
            resetUI()
            showDialog(getString(R.string.txt_atention), getString(R.string.txt_review_success))
        }else{
            showDialog(getString(R.string.txt_error), getString(R.string.txt_review_failed))
        }
    }

    private fun startImagePickerFlow() {
        permissionsDelegate.requestReadAndWriteExternalStoragePermissions(this, object :
            PermissionHelperEvents {
            override fun onSuccessPermissionsGranted() {
                pickImageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            override fun onDeniedPermissions() {
                showDialog(getString(R.string.txt_no_granted_notification_permissions), getString(R.string.txt_image_permission))
            }
        })
    }

    // ----- UI Methods

    private fun resetUI() {
        bindingView.movieReview.edtReview.text.clear()
        uploadReviewsViewModel.setImagePath(null)
        bindingView.movieReview.ratingBar.rating = MAX_QUANTITY_OF_STARS
        bindingView.movieReview.imgUpload.setImageResource(R.drawable.ic_image_attach)
    }

    private fun showDialog(title: String, description:String) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val errorDialogFragment = Dialogs(title, description)
        errorDialogFragment.show(fragmentManager, getString(R.string.txt_dialog_tag))
    }
}
