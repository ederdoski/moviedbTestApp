package com.edominguez.moviedb.features.home.maps.datasource.service

import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreCollections
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FireStoreService(private val fireStoreDB: FirebaseFirestore) {

    fun getUserLocations(): Task<QuerySnapshot> {
        return fireStoreDB.collection(FireStoreCollections.USERS).get()
    }

}