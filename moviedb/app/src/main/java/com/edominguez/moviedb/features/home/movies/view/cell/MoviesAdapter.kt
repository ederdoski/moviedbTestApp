package com.edominguez.moviedb.features.home.movies.view.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.edominguez.moviedb.core.base.BaseAdapter
import com.edominguez.moviedb.core.base.BaseViewHolder
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.databinding.MoviesViewCellBinding
import com.edominguez.moviedb.features.home.movies.datasource.model.MovieData

class MoviesAdapter (
    private val dataList: MutableList<MovieData> = mutableListOf(),
    private val onItemClickListener: IOnItemClickViewHolder
) : BaseAdapter<MovieData>(dataList, onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieData> {
        return MoviesViewHolder(
            MoviesViewCellBinding.inflate (
                LayoutInflater.from(parent.context), parent, false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MovieData>, position: Int) {
        holder.bindingDataInHolder(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun addData(data: List<MovieData>) {
        dataList.addAll(data)
        this.notifyDataSetChanged()
    }
}