package top.techqi.chaotic.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.View
import top.techqi.chaotic.BuildConfig
import top.techqi.chaotic.util.LogU

/**
 * Fragment 基类
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class BaseFragment : Fragment() {
    @Suppress("PropertyName", "HasPlatformType")
    protected open val TAG = javaClass.simpleName

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    protected var firstPrimed = false
        private set

    @CallSuper
    override fun onAttach(context: Context?) {
        if (DEBUG) LogU.vv(TAG, "onAttach")
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(state: Bundle?) {
        if (DEBUG) LogU.vv(TAG, "onCreate")
        super.onCreate(state)
    }

    @CallSuper
    override fun onViewCreated(view: View, state: Bundle?) {
        if (DEBUG) LogU.vv(TAG, "onViewCreated")
        super.onViewCreated(view, state)
    }

    @CallSuper
    override fun onActivityCreated(state: Bundle?) {
        if (DEBUG) LogU.vv(TAG, "onActivityCreated")
        super.onActivityCreated(state)
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
        setPrimary()
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
    override fun onDestroyView() {
        super.onDestroyView()
        if (DEBUG) LogU.vv(TAG, "onDestroyView")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        if (DEBUG) LogU.vv(TAG, "onDestroy")
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        if (DEBUG) LogU.vv(TAG, "onDetach")
    }


    @CallSuper
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        setPrimary()
    }

    @CallSuper
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        setPrimary()
    }

    protected open fun onPrimary(first: Boolean) {}

    private fun setPrimary() {
        if (userVisibleHint && isResumed && isAdded && !isHidden) {
            onPrimary(firstPrimed)
            firstPrimed = false
        }
    }
}
