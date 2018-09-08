package top.techqi.chaotic.util

import android.annotation.SuppressLint
import android.app.Application
import top.techqi.chaotic.R

/**
 * 在任意模块中都能获取到常规信息的辅助类
 */
@SuppressLint("PrivateApi")
@Suppress("unused", "MemberVisibilityCanBePrivate", "HasPlatformType")
object PluginApp {
    val INSTANCE: Application by lazy {
        try {
            val clazz = Class.forName("android.app.AppGlobals")
            val method = clazz.getMethod("getInitialApplication")
            return@lazy method.invoke(null) as Application
        } catch (ex: Exception) {
            throw IllegalStateException("CANNOT get Application Context", ex)
        }
    }

    val NAME by lazy { INSTANCE.getString(R.string.name)!! }
}
