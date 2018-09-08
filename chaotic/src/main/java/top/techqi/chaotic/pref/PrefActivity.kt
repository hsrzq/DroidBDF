package top.techqi.chaotic.pref

import android.content.Intent
import android.content.res.Configuration
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.RingtonePreference
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import top.techqi.chaotic.BuildConfig
import top.techqi.chaotic.util.LogU

/**
 * 首选项基类
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class PrefActivity : PreferenceActivity(), Preference.OnPreferenceChangeListener {
    @Suppress("PropertyName", "HasPlatformType")
    open val TAG = javaClass.simpleName

    @Suppress("PropertyName", "HasPlatformType")
    protected open val NAME by lazy { TAG }

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    @Suppress("PropertyName")
    val SOURCE: String? by lazy { intent.getStringExtra(EXTRA_SOURCE) }

    private val delegate: AppCompatDelegate by lazy { AppCompatDelegate.create(this, null) }

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

    val supportActionBar: ActionBar?
        get() = delegate.supportActionBar

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        LogU.vv(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        delegate.installViewFactory()
        delegate.onCreate(savedInstanceState)
        created = true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delegate.onPostCreate(savedInstanceState)
    }

    @CallSuper
    override fun onStart() {
        LogU.vv(TAG, "onStart")
        super.onStart()
        delegate.onStart()
        started = true
    }

    @CallSuper
    override fun onResume() {
        LogU.vv(TAG, "onResume")
        super.onResume()
        resumed = true
    }

    override fun onPostResume() {
        super.onPostResume()
        delegate.onPostResume()
    }

    @CallSuper
    override fun onPause() {
        resumed = false
        super.onPause()
        LogU.vv(TAG, "onPause")
    }

    @CallSuper
    override fun onStop() {
        started = false
        delegate.onStop()
        super.onStop()
        LogU.vv(TAG, "onStop")
    }

    @CallSuper
    override fun onDestroy() {
        created = false
        delegate.onDestroy()
        super.onDestroy()
        LogU.vv(TAG, "onDestroy")
    }

    @CallSuper
    override fun onRestart() {
        LogU.vv(TAG, "onRestart")
        super.onRestart()
        restarted++
    }

    fun setSupportActionBar(toolbar: Toolbar?) {
        delegate.setSupportActionBar(toolbar)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        delegate.setContentView(layoutResID)
    }

    override fun setContentView(view: View) {
        delegate.setContentView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        delegate.setContentView(view, params)
    }

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
        delegate.addContentView(view, params)
    }

    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        delegate.setTitle(title)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        delegate.onConfigurationChanged(newConfig)
    }

    override fun getMenuInflater(): MenuInflater {
        return delegate.menuInflater
    }

    override fun invalidateOptionsMenu() {
        delegate.invalidateOptionsMenu()
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        intent.putExtra(EXTRA_SOURCE, NAME)
        super.startActivityForResult(intent, requestCode)
    }

    override fun startActivities(intents: Array<out Intent>, options: Bundle?) {
        intents.forEach { it.putExtra(EXTRA_SOURCE, NAME) }
        super.startActivities(intents, options)
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        val value = newValue.toString()

        when (preference) {
            is ListPreference -> preference.findIndexOfValue(value).let { preference.summary = if (it >= 0) preference.entries[it] else null }
            is RingtonePreference -> preference.summary = RingtoneManager.getRingtone(this, Uri.parse(value))?.getTitle(this)
            else -> preference.summary = value
        }
        return true
    }

    companion object {
        const val EXTRA_SOURCE = "extra_source"
    }
}