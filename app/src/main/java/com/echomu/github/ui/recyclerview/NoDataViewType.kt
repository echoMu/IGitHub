package com.echomu.github.ui.recyclerview

/**
 * Created by echoMu.
 */
abstract class NoDataViewType<T : Any> : BaseViewType<T>() {

    abstract fun bind(holder: BaseViewHolder,
                      position: Int)

    abstract fun isMatchViewType(position: Int,
                                 headerCount: Int,
                                 itemCount: Int,
                                 footerCount: Int): Boolean

    override fun bind(holder: BaseViewHolder, data: T, position: Int) {
        // no implementation
    }

    override fun bind(holder: BaseViewHolder, data: T,
                      position: Int,
                      payLoads: List<Any>) {
        // no implementation
    }

    override fun isMatchViewType(any: Any): Boolean = false

    fun bind(holder: BaseViewHolder,
             position: Int,
             payLoads: List<Any>) =
            with(holder) {
                bind(this, position)
                binding.executePendingBindings()
            }

}