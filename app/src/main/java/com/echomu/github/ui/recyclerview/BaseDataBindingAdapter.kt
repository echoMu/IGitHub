package com.echomu.github.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by echoMu.
 */
abstract class BaseDataBindingAdapter<T : Any>
    : RecyclerView.Adapter<BaseViewHolder>() {

    protected abstract fun getLayoutResByPosition(position: Int): Int

    protected abstract fun getItemByPosition(position: Int): T?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
            BaseViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
            ))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItemByPosition(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {
        getItemByPosition(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int =
            getLayoutResByPosition(position)

}