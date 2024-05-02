package com.edominguez.moviedb.features.home.profile.datasource.service

import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreCollections
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ProfileService(private val fireStoreDB: FirebaseFirestore) {
    fun getUserReviews(): Task<QuerySnapshot> {
        return fireStoreDB.collection(FireStoreCollections.REVIEWS).get()
    }
}