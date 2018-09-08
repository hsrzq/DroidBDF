package top.techqi.chaotic.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import top.techqi.chaotic.BuildConfig
import top.techqi.chaotic.util.LogU

/**
 * Activity 基类
 */
abstract class BaseActivity : AppCompatActivity() {
    @Suppress("PropertyName", "HasPlatformType")
    protected open val TAG = javaClass.simpleName

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        if (DEBUG) LogU.vv(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onStart() {
        if (DEBUG) LogU.vv(TAG, "onStart")
        super.onStart()
    }

    @CallSuper
    override fun onResume() {
        if (DEBUG) LogU.vv(TAG, "onResume")
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        if (DEBUG) LogU.vv(TAG, "onPause")
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        if (DEBUG) LogU.vv(TAG, "onStop")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        if (DEBUG) LogU.vv(TAG, "onDestroy")
    }

    @CallSuper
    override fun onRestart() {
        if (DEBUG) LogU.vv(TAG, "onRestart")
        super.onRestart()
    }
}
