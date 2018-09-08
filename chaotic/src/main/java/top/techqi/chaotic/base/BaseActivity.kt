package top.techqi.chaotic.base

import android.content.Intent
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

    @Suppress("PropertyName", "HasPlatformType")
    protected open val NAME by lazy { TAG }

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    @Suppress("PropertyName")
    val SOURCE: String? by lazy { intent.getStringExtra(EXTRA_SOURCE) }

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

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        intent.putExtra(EXTRA_SOURCE, NAME)
        super.startActivityForResult(intent, requestCode)
    }

    override fun startActivities(intents: Array<out Intent>, options: Bundle?) {
        intents.forEach { it.putExtra(EXTRA_SOURCE, NAME) }
        super.startActivities(intents, options)
    }

    companion object {
        const val EXTRA_SOURCE = "extra_source"
    }
}
