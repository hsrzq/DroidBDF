package top.techqi.widget.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * RecyclerView 适配器
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val linkers = HashMap<Class<*>, (Int, Any) -> RecyclerBinder<*, *>>()
    private val binders = HashMap<Int, RecyclerBinder<*, *>>()

    private lateinit var inflater: LayoutInflater
    private var provider: Provider? = null

    override fun onAttachedToRecyclerView(view: RecyclerView) {
        inflater = LayoutInflater.from(view.context)
    }

    fun register(clazz: Class<*>, linker: (Int, Any) -> RecyclerBinder<*, *>) {
        linkers[clazz] = linker
    }

    fun register(clazz: Class<*>, binder: RecyclerBinder<*, *>) {
        linkers[clazz] = { _, _ -> binder }
    }

    fun setItems(provider: Provider) {
        this.provider = provider
        notifyDataSetChanged()
    }

    fun setItems(array: Array<*>) {
        setItems(object : Provider {
            override val count: Int = array.size
            override fun get(position: Int): Any = array[position]!!
        })
    }

    fun setItems(list: List<*>) {
        setItems(object : Provider {
            override val count: Int = list.size
            override fun get(position: Int): Any = list[position]!!
        })
    }

    override fun getItemCount(): Int {
        return provider?.count ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        val binder = getBinderByPosition(position)
        val type = binder.hashCode()
        if (type !in binders) binders[type] = binder
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return binders[viewType]!!.createViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>?) {
        binders[holder.itemViewType]!!.bindViewHolder(holder, position, provider!![position], payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binders[holder.itemViewType]!!.bindViewHolder(holder, position, provider!![position])
    }

    fun getBinderByPosition(position: Int): RecyclerBinder<*, *> {
        val item = provider!![position]
        return getBinderByVirtual(position, item)
    }

    fun getBinderByHolder(holder: RecyclerView.ViewHolder): RecyclerBinder<*, *> {
        return binders[holder.itemViewType] ?: getBinderByPosition(holder.adapterPosition)
    }

    internal fun getBinderByVirtual(position: Int, item: Any): RecyclerBinder<*, *> {
        var clazz: Class<*>? = item::class.java
        while (clazz != null) {
            if (clazz in linkers) return linkers[clazz]!!(position, item)
            clazz = clazz.superclass
        }
        throw IllegalStateException("${item.javaClass} NOT registered")
    }

    interface Provider {
        val count: Int
        operator fun get(position: Int): Any
    }
}
