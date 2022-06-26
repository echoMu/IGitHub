package com.echomu.github.ui.repository.adapter

import com.echomu.github.BR
import com.echomu.github.R
import com.echomu.github.data.model.repository.RepositoryData
import com.echomu.github.ui.recyclerview.BaseViewHolder
import com.echomu.github.ui.recyclerview.BaseViewType
import com.echomu.github.ui.recyclerview.MultiViewTypeDataBindingAdapter

/**
 * Created by echoMu.
 */
class RepositoryAdapter : MultiViewTypeDataBindingAdapter<RepositoryData>() {

    init {
        addViewType(RepositoryViewType())
    }

}

class RepositoryViewType : BaseViewType<RepositoryData>() {

    override fun getItemLayoutRes(): Int = R.layout.item_repository

    override fun isMatchViewType(any: Any): Boolean =
            any is RepositoryData

    override fun bind(holder: BaseViewHolder, data: RepositoryData, position: Int) {
        holder.binding.setVariable(BR.data, data)
    }

    override fun getSpanSize(): Int = 0

}