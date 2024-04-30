package com.edominguez.moviedb.features.home.view

import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.HomeViewFragmentBinding
import com.edominguez.moviedb.databinding.MapsViewFragmentBinding
import com.edominguez.moviedb.features.home.viewmodel.HomeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsVieFragment : BaseFragment<MapsViewFragmentBinding>(), OnMapReadyCallback {

    private val homeViewModel: HomeViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "HomeViewFragment"

    override fun listenToObserver() {
        observe(homeViewModel.homeVMDelegate.showUnknownError, this::onError)
    }

    // ---- Initialize your view here

    override fun init() {
        bindUI()
        initMap()
        setOnClickListeners()
    }

    // ----- Logic Methods

    private fun setOnClickListeners() {

    }

    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    // ----- UI Methods

    private fun bindUI() {
        communication.onFragmentEvent(ProtocolAction.OnSelectedMapsItem(HomeActivity.BOTTOM_MENU_ITEM_MAPS))
    }

    override fun onMapReady(_googleMap: GoogleMap) {
       /* locationViewModel.setGoogleMap(_googleMap)
        locationViewModel.addMarkerToMap(locationViewModel.getGoogleMap(),
            LatLng(
                args.requestCardData.location.latitude,
                args.requestCardData.location.longitude
            )
        )*/
    }

}