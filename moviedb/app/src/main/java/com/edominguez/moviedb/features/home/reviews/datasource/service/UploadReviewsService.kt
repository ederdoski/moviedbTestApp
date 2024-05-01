package com.edominguez.moviedb.features.home.reviews.datasource.service

import android.util.Log
import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreCollections
import com.edominguez.moviedb.features.home.maps.datasource.model.FireStoreFields
import com.edominguez.moviedb.features.home.reviews.datasource.model.UploadReviewsRequestData
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class UploadReviewsService(private val fireStoreDB: FirebaseFirestore, private val fireStorage: FirebaseStorage) {

    fun uploadReview(uploadReviewsRequestData: UploadReviewsRequestData): Task<DocumentReference> {
        val documentData = hashMapOf<String, Any>()

        val imageUri = uploadReviewsRequestData.path
        val storageRef = fireStorage.reference
        val imagesRef = storageRef.child("images/${System.currentTimeMillis()}.jpg")

        val uploadTask = imagesRef.putFile(imageUri)

        val resultTask = TaskCompletionSource<DocumentReference>()

        uploadTask.addOnSuccessListener {
            imagesRef.downloadUrl.addOnSuccessListener { downloadUri ->
                documentData[FireStoreFields.PATH] = downloadUri.toString()
                documentData[FireStoreFields.RATING] = uploadReviewsRequestData.rating
                documentData[FireStoreFields.COMMENT] = uploadReviewsRequestData.comment

                fireStoreDB.collection(FireStoreCollections.REVIEWS)
                    .add(documentData)
                    .addOnSuccessListener { docRef ->
                        resultTask.setResult(docRef)
                    }
                    .addOnFailureListener { e ->
                        resultTask.setException(e)
                    }
            }
        }.addOnFailureListener { e ->
            resultTask.setException(e)
        }

        return resultTask.task
    }

}