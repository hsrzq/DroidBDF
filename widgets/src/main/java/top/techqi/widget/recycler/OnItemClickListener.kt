package top.techqi.widget.recycler

import android.view.View
import android.view.ViewGroup

/**
 * RecyclerView 短按事件
 * @param I 被短按的条目数据(父)类型
 */
@Suppress("unused")
interface OnItemClickListener<in I> {
    /**
     * 条目被短按事件回调
     * @param parent 父容器（RecyclerView）
     * @param view 被短按视图，可以是条目的一部分，以便处理同一条目不同位置功能不同的场景
     * @param position 被短按的位置下标
     * @param item 被短按的条目数据
     * @param attaches 其它需要附加传递的数据
     */
    fun onItemClick(parent: ViewGroup, view: View, position: Int, item: I, vararg attaches: Any?)
}
