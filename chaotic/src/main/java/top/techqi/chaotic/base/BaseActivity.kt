package top.techqi.chaotic.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import top.techqi.chaotic.BuildConfig
import top.techqi.chaotic.util.LogU

/**
 * Activity 基类
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class BaseActivity : AppCompatActivity() {
    @Suppress("PropertyName", "HasPlatformType")
    protected open val TAG = javaClass.simpleName

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    var created = false
        private set
    var started = false
        private set
    var resumed = false
        private set
    val paused
        get() = !resumed
    val stopped
        get() = !started
    val destroyed
        get() = !created

    var restarted = 0
        private set

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        if (DEBUG) LogU.vv(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        created = true
    }

    @CallSuper
    override fun onStart() {
        if (DEBUG) LogU.vv(TAG, "onStart")
        super.onStart()
        started = true
    }

    @CallSuper
    override fun onResume() {
        if (DEBUG) LogU.vv(TAG, "onResume")
        super.onResume()
        resumed = true
    }

    @CallSuper
    override fun onPause() {
        resumed = false
        super.onPause()
        if (DEBUG) LogU.vv(TAG, "onPause")
    }

    @CallSuper
    override fun onStop() {
        started = false
        super.onStop()
        if (DEBUG) LogU.vv(TAG, "onStop")
    }

    @CallSuper
    override fun onDestroy() {
        created = false
        super.onDestroy()
        if (DEBUG) LogU.vv(TAG, "onDestroy")
    }

    @CallSuper
    override fun onRestart() {
        if (DEBUG) LogU.vv(TAG, "onRestart")
        super.onRestart()
        restarted++
    }
}
