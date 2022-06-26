package com.echomu.github.ui.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.echomu.github.BR

/**
 * Created by echoMu.
 */
class BaseViewHolder(val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Any) =
            with(binding) {
                setVariable(BR.data, data)
                executePendingBindings()
            }

}