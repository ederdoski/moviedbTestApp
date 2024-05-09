package com.edominguez.moviedb.features.home.profile.view.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.databinding.ReviewUserViewCellBinding
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData

class ReviewListAdapter(
    private val onItemClickListener: IOnItemClickViewHolder
) : ListAdapter<ReviewsResponseData, ReviewsViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bindingDataInHolder(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewsResponseData>() {
            override fun areItemsTheSame(oldUser: ReviewsResponseData, newUser: ReviewsResponseData): Boolean {
                // Las propiedades del usuario pueden haber cambiado si se recargan desde la base de datos, pero el ID es fijo
                return oldUser.id == newUser.id
            }

            override fun areContentsTheSame(oldUser: ReviewsResponseData, newUser: ReviewsResponseData): Boolean {
                // Si usas equals, tu objeto debe sobrescribir correctamente Object#equals()
                // Retornar incorrectamente false aquí resultará en demasiadas animaciones.
                return oldUser == newUser
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return ReviewsViewHolder(
            ReviewUserViewCellBinding.inflate (
                LayoutInflater.from(parent.context), parent, false
            ), onItemClickListener
        )
    }
}