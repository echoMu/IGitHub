package com.echomu.github.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

/**
 * Created by echoMu.
 */
abstract class BaseViewType<T : Any> {

    @LayoutRes
    abstract fun getItemLayoutRes(): Int

    abstract fun isMatchViewType(any: Any): Boolean

    abstract fun bind(holder: BaseViewHolder,
                      data: T,
                      position: Int)

    abstract fun getSpanSize(): Int

    fun onCreateDataBindingViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
            BaseViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
            ))

    open fun bind(holder: BaseViewHolder,
                  data: T,
                  position: Int,
                  payLoads: List<Any>) =
            with(holder) {
                bind(this, data, position)
                binding.executePendingBindings()
            }

}