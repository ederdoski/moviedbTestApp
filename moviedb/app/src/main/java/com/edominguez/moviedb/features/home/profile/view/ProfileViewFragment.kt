package com.edominguez.moviedb.features.home.profile.view

import android.view.View
import androidx.fragment.app.FragmentManager
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.core.common.utils.Dialogs
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.ProfileLayoutViewFragmentBinding
import com.edominguez.moviedb.features.home.movies.view.HomeActivity
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData
import com.edominguez.moviedb.features.home.profile.view.cell.ReviewListAdapter
import com.edominguez.moviedb.features.home.profile.view.cell.ReviewsAdapter
import com.edominguez.moviedb.features.home.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileViewFragment : BaseFragment<ProfileLayoutViewFragmentBinding>() {

    private val profileViewModel: ProfileViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "ProfileViewFragment"

    override fun listenToObserver() {
        observe(profileViewModel.profileVMDelegate.showUnknownError, this::onError)
        observe(profileViewModel.profileVMDelegate.onListReviewsResponse, this::onListReviewsResponse)
        observe(profileViewModel.profileVMDelegate.onListReviewsResponseFailed, this::onListReviewsResponseFailed)
    }

    // ---- Initialize your view here

    override fun init() {
        //initAdapter()
        initListAdapter()
        setOnClickListeners()
        profileViewModel.getUserReviews()
        communication.onFragmentEvent(ProtocolAction.OnSelectedMenuItem(HomeActivity.BOTTOM_MENU_ITEM_PROFILE))
    }

    // ----- Logic Methods
    private fun setOnClickListeners() { }

    private fun initListAdapter() {
        val adapter = ReviewListAdapter(reviewClickListener)
        bindingView.rvReviews.adapter = adapter
    }

    private fun initAdapter() {
        val tmpAdapter = ReviewsAdapter(onItemClickListener = reviewClickListener)
        bindingView.rvReviews.adapter = tmpAdapter
    }

    private fun setListOfReviews(aReviews: List<ReviewsResponseData>) {
        val tmpAdapter = bindingView.rvReviews.adapter as ReviewListAdapter
        tmpAdapter.submitList(aReviews)

        //TODO REVISAR
        //aReviews.observe(this) { list -> adapter.submitList(list) }

        //val tmpAdapter = bindingView.rvReviews.adapter as ReviewsAdapter
        //tmpAdapter.addData(aReviews)
    }

    private val reviewClickListener = object : IOnItemClickViewHolder {
        override fun onItemClick(caller: View?, position: Int) {}
    }

    private fun onListReviewsResponse(data:List<ReviewsResponseData>) {
        setListOfReviews(data)
    }

    private fun onListReviewsResponseFailed(event: Unit) {
        showDialog(getString(R.string.txt_atention), getString(R.string.txt_get_review_failed))
    }

    // ----- UI Methods

    private fun showDialog(title: String, description:String) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val errorDialogFragment = Dialogs(title, description)
        errorDialogFragment.show(fragmentManager, getString(R.string.txt_dialog_tag))
    }

}