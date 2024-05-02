package com.edominguez.moviedb.features.home.profile.view.cell

import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseViewHolder
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.core.common.utils.MOVIE_DB_IMG_API
import com.edominguez.moviedb.core.extensions.setImageSrcFromUrl
import com.edominguez.moviedb.databinding.ReviewUserViewCellBinding
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData

class ReviewsViewHolder(
    private val binding: ReviewUserViewCellBinding,
    onItemClickListener: IOnItemClickViewHolder
) : BaseViewHolder<ReviewsResponseData>(binding, onItemClickListener) {

    override fun bindingDataInHolder(data: ReviewsResponseData) {
        super.bindingDataInHolder(data)

        binding.txtReview.text = data.comment
        binding.ratingBar.rating = data.rating
        binding.imgReview.setImageSrcFromUrl(data.path, R.drawable.img_moviedb, binding.root.context)
    }
}