package com.edominguez.moviedb.features.home.maps.datasource.service

import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreCollections
import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreFields
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.maps.model.LatLng

class MapsService(private val fireStoreDB: FirebaseFirestore) {

    fun getUserLocations(): Task<QuerySnapshot> {
        return fireStoreDB.collection(FireStoreCollections.USERS).get()
    }

    fun saveOrUpdateUserPosition(deviceID: String, latLng: LatLng, createdAt: String): Task<Void> {
        val documentData = hashMapOf(
            FireStoreFields.CREATED_AT to createdAt,
            FireStoreFields.LATITUDE to latLng.latitude.toString(),
            FireStoreFields.LONGITUDE to latLng.longitude.toString()
        )

        return fireStoreDB.collection(FireStoreCollections.USERS)
            .document(deviceID)
            .set(documentData)
    }

}