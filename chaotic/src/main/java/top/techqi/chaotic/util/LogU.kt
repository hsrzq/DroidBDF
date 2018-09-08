package top.techqi.chaotic.util

import android.util.Log
import top.techqi.chaotic.BuildConfig

/**
 * 控制台Log封装
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object LogU {
    val TAG = PluginApp.NAME
    var DEBUG = BuildConfig.DEBUG

    fun vv(tag: String?, msg: String?, tr: Throwable? = null) {
        if (DEBUG) Log.v(tag ?: TAG, "$msg", tr)
    }

    fun dd(tag: String?, msg: String?, tr: Throwable? = null) {
        if (DEBUG) Log.d(tag ?: TAG, "$msg", tr)
    }

    fun ii(tag: String?, msg: String?, tr: Throwable? = null) {
        if (DEBUG) Log.i(tag ?: TAG, "$msg", tr)
    }

    fun ww(tag: String?, msg: String?, tr: Throwable? = null) {
        if (DEBUG) Log.w(tag ?: TAG, "$msg", tr)
    }

    fun ee(tag: String?, msg: String?, tr: Throwable? = null) {
        if (DEBUG) Log.e(tag ?: TAG, "$msg", tr)
    }

    fun v(tag: String?, tr: Throwable?) = vv(tag, "", tr)
    fun d(tag: String?, tr: Throwable?) = dd(tag, "", tr)
    fun i(tag: String?, tr: Throwable?) = ii(tag, "", tr)
    fun w(tag: String?, tr: Throwable?) = ww(tag, "", tr)
    fun e(tag: String?, tr: Throwable?) = ee(tag, "", tr)

    fun v(tag: String?, format: String, vararg args: Any?) = vv(tag, format.format(*args))
    fun d(tag: String?, format: String, vararg args: Any?) = dd(tag, format.format(*args))
    fun i(tag: String?, format: String, vararg args: Any?) = ii(tag, format.format(*args))
    fun w(tag: String?, format: String, vararg args: Any?) = ww(tag, format.format(*args))
    fun e(tag: String?, format: String, vararg args: Any?) = ee(tag, format.format(*args))
}
