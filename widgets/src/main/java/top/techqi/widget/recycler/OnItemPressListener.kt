package top.techqi.widget.recycler

import android.view.View
import android.view.ViewGroup

/**
 * RecyclerView 长按事件
 * @param I 被长按的条目数据(父)类型
 */
@Suppress("unused")
interface OnItemPressListener<in I> {
    /**
     * 条目被长按事件回调
     * @param parent 父容器（RecyclerView）
     * @param view 被长按视图，可以是条目的一部分，以便处理同一条目不同位置功能不同的场景
     * @param position 被长按的位置下标
     * @param item 被长按的条目数据
     * @param attaches 其它需要附加传递的数据
     */
    fun onItemPress(parent: ViewGroup, view: View, id: Long, position: Int, item: I, vararg attaches: Any?)
}
