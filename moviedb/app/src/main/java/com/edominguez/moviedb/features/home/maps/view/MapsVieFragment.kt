package com.edominguez.moviedb.features.home.maps.view

import android.location.Location
import androidx.fragment.app.FragmentManager
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.common.utils.Dialogs
import com.edominguez.moviedb.core.common.utils.Functions
import com.edominguez.moviedb.core.common.utils.MapsDelegate
import com.edominguez.moviedb.core.common.utils.NotificationDelegate
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.core.extensions.toVisible
import com.edominguez.moviedb.core.permissions.PermissionHelperEvents
import com.edominguez.moviedb.core.permissions.PermissionsDelegate
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.MapsViewFragmentBinding
import com.edominguez.moviedb.features.home.maps.datasource.model.MapsMarkersViewInput
import com.edominguez.moviedb.features.home.maps.datasource.model.UserPositionResponseData
import com.edominguez.moviedb.features.home.maps.viewmodel.MapsViewModel
import com.edominguez.moviedb.features.home.movies.view.HomeActivity
import com.edominguez.moviedb.features.home.movies.viewmodel.HomeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsVieFragment : BaseFragment<MapsViewFragmentBinding>(), OnMapReadyCallback, MapsDelegate.MapsListener {

    private val mapsDelegate: MapsDelegate by inject()
    private val homeViewModel: HomeViewModel by viewModel()
    private val permissionsDelegate: PermissionsDelegate by inject()
    private val fireStoreViewModel: MapsViewModel by viewModel()
    private val notificationDelegate: NotificationDelegate by inject()

    // ---- Base functions

    override fun screenName() = "MapsVieFragment"

    override fun listenToObserver() {
        observe(homeViewModel.homeVMDelegate.showUnknownError, this::onError)
        observe(fireStoreViewModel.fireStoreVMDelegate.onUsersLocationResponse, this::onUsersLocationResponse)
        observe(fireStoreViewModel.fireStoreVMDelegate.onUserLocationSavedResponse, this::onUserLocationSavedResponse)
        observe(fireStoreViewModel.fireStoreVMDelegate.onUsersLocationResponseFailed, this::onUsersLocationResponseFailed)
    }

    // ---- Initialize your view here

    override fun init() {
        bindUI()
        initMap()
        setOnClickListeners()
    }

    // ----- Logic Methods

    private fun setOnClickListeners() {}

    private fun checkNotificationPermissions() {
        permissionsDelegate.requestPushNotificationPermissions(this, object : PermissionHelperEvents {
            override fun onSuccessPermissionsGranted() {
                createNewNotification()
            }

            override fun onDeniedPermissions() {
                showErrorMessage(getString(R.string.txt_no_granted_notification_permissions), getString(R.string.notification_description_new_position_saved_without_permits))
            }
        })
    }

    private fun onUsersLocationResponse(data: List<UserPositionResponseData>){
        mapsDelegate.resetMap()
        data.forEach{
            mapsDelegate.addMarkerToMap(it.id, it.latLng, it.createdAt)
        }
    }

    private fun onUsersLocationResponseFailed(event:Unit){
        showErrorMessage(getString(R.string.txt_error), getString(R.string.txt_error_user_positions))
    }

    private fun onUserLocationSavedResponse(isSaved:Boolean) {
        checkNotificationPermissions()
    }

    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun startGetLocationFlow() {
        if(mapsDelegate.checkGpsEnabled(requireActivity())) {
            if(mapsDelegate.checkLocationPermissionEnabled(requireContext())) {
                mapsDelegate.startFlowWhenLocationPermissionsEnabled(
                    activity = requireActivity(),
                    mapsListener = this
                )
            }else{
                mapsDelegate.startFlowWhenLocationPermissionsDisabled(requireActivity())
            }
        }else{
           showErrorMessage(getString(R.string.txt_error), getString(R.string.txt_error_gps_description))
        }
    }

    // ----- UI Methods

    private fun bindUI() {
        communication.onFragmentEvent(ProtocolAction.OnSelectedMenuItem(HomeActivity.BOTTOM_MENU_ITEM_MAPS))
    }

    override fun onMapReady(map: GoogleMap) {
        fireStoreViewModel.getUserLocations()
        mapsDelegate.setGoogleMap(map)
        startGetLocationFlow()
        mapsDelegate.checkClicksInMarker(this)
    }

    override fun onMarkerClicked(clickedMarker: MapsMarkersViewInput) {
        bindingView.lyUserUpdates.toVisible()
        bindingView.txtDeviceID.text = clickedMarker.id
        bindingView.txtLastUpdate.text = Functions.getFormatDate(clickedMarker.createdAt)
    }

    override fun onNeedToSaveNewPosition(latLng: LatLng) {
        fireStoreViewModel.getUserLocations()
        fireStoreViewModel.saveOrUpdateNewLocation(Functions.getDeviceID(requireContext()), latLng)
    }

    override fun onLocationObtained(location: Location) {
        val currentPosition = LatLng(location.latitude, location.longitude)
        mapsDelegate.updateUserPosition(currentPosition, this)
    }

    override fun locationUnknown() {
        showErrorMessage(getString(R.string.txt_error), getString(R.string.txt_location_unknown))
    }

    private fun createNewNotification() {
        val pendingIntent = notificationDelegate.createPendingIntent(HomeActivity::class.java, 0)

        notificationDelegate.showNotification(
            title = getString(R.string.notification_title_new_position_saved),
            message = getString(R.string.notification_description_new_position_saved),
            pendingIntent = pendingIntent
        )
    }

    private fun showErrorMessage(title: String, description:String) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val errorDialogFragment = Dialogs(title, description)
        errorDialogFragment.show(fragmentManager, getString(R.string.txt_dialog_tag))
    }
}