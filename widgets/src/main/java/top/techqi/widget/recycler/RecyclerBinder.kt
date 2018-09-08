package top.techqi.widget.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * RecyclerView 视图绑定器
 */
@Suppress("unused", "UNUSED_PARAMETER", "MemberVisibilityCanPrivate", "UNCHECKED_CAST")
abstract class RecyclerBinder<I, VH : RecyclerView.ViewHolder>(protected val context: Context) {
    internal fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return onCreateHolder(inflater, parent)
    }

    internal fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: Any, payloads: List<Any>?) {
        return onBindHolder(holder as VH, position, item as I, payloads)
    }

    internal fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: Any) {
        return onBindHolder(holder as VH, position, item as I)
    }

    abstract fun onCreateHolder(inflater: LayoutInflater, parent: ViewGroup): VH

    open fun onBindHolder(holder: VH, position: Int, item: I, payloads: List<Any>?) {
        onBindHolder(holder, position, item)
    }

    abstract fun onBindHolder(holder: VH, position: Int, item: I)

    open fun onViewAttachedToWindow(holder: VH) {}

    open fun onViewDetachedFromWindow(holder: VH) {}

    open fun onFailedToRecycleView(holder: VH) = false

    open fun onViewRecycled(holder: VH) {}
}
