package com.edominguez.moviedb.features.home.profile.view.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.edominguez.moviedb.core.base.BaseAdapter
import com.edominguez.moviedb.core.base.BaseViewHolder
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.databinding.ReviewUserViewCellBinding
import com.edominguez.moviedb.features.home.profile.datasource.model.ReviewsResponseData

class ReviewsAdapter(
    private val dataList: MutableList<ReviewsResponseData> = mutableListOf(),
    private val onItemClickListener: IOnItemClickViewHolder
) : BaseAdapter<ReviewsResponseData>(dataList, onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ReviewsResponseData> {
        return ReviewsViewHolder(
            ReviewUserViewCellBinding.inflate (
                LayoutInflater.from(parent.context), parent, false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ReviewsResponseData>, position: Int) {
        holder.bindingDataInHolder(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun addData(data: List<ReviewsResponseData>) {
        dataList.addAll(data)
        this.notifyDataSetChanged()
    }
}