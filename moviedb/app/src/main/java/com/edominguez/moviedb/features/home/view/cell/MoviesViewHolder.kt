package com.edominguez.moviedb.features.home.view.cell

import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseViewHolder
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.core.common.utils.MOVIE_DB_IMG_API
import com.edominguez.moviedb.core.extensions.setImageSrcFromUrl
import com.edominguez.moviedb.databinding.MoviesViewCellBinding
import com.edominguez.moviedb.features.home.datasource.model.MovieData

class MoviesViewHolder(
    private val binding: MoviesViewCellBinding,
    onItemClickListener: IOnItemClickViewHolder
) : BaseViewHolder<MovieData>(binding, onItemClickListener) {

    override fun bindingDataInHolder(data: MovieData) {
        super.bindingDataInHolder(data)
        binding.imgMovie.setImageSrcFromUrl(MOVIE_DB_IMG_API + data.posterPath, R.drawable.img_moviedb, binding.root.context)
    }
}