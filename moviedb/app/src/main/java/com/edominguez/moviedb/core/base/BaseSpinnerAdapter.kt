package com.edominguez.moviedb.core.base

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.edominguez.moviedb.R

class BaseSpinnerAdapter<T>(
    context: Context,
    private val items: MutableList<T>,
    private val disableFirstItem: Boolean = false
): ArrayAdapter<T>(context, R.layout.spinner_item_view, items) {
    @Override
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val tv = view as TextView
        setSpinnerItemState(tv, position)
        return view
    }

    @Override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val tv = view as TextView
        if(disableFirstItem){
            setSpinnerItemState(tv, position)
        }
        return view
    }
    private fun setSpinnerItemState(view: TextView, position: Int) {
        if (position == 0) {
            view.setTextColor(Color.GRAY)
        } else {
            view.setTextColor(Color.BLACK)
        }
    }

    fun addNewItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}