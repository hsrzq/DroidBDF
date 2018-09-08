package top.techqi.droidbdf.pangu.base

import android.content.Context
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import top.techqi.chaotic.BuildConfig
import top.techqi.chaotic.pref.PrefActivity
import top.techqi.chaotic.util.LogU

/**
 * 首选项Fragment
 */
@Suppress("unused", "MemberVisibilityCanBePrivate", "UNCHECKED_CAST")
abstract class PrefFragment : PreferenceFragment() {
    @Suppress("PropertyName", "HasPlatformType")
    protected open val TAG = javaClass.simpleName

    @Suppress("PropertyName")
    protected open val DEBUG = BuildConfig.DEBUG

    protected lateinit var activity: PrefActivity
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as PrefActivity
    }

    protected fun <T> bindPreferenceSummaryToValue(preference: Preference, defValue: T) {
        preference.onPreferenceChangeListener = activity

        val shared = PreferenceManager.getDefaultSharedPreferences(preference.context)
        when (defValue) {
            is Boolean -> activity.onPreferenceChange(preference, shared.getBoolean(preference.key, defValue))
            is Int -> activity.onPreferenceChange(preference, shared.getInt(preference.key, defValue))
            is Long -> activity.onPreferenceChange(preference, shared.getLong(preference.key, defValue))
            is Float -> activity.onPreferenceChange(preference, shared.getFloat(preference.key, defValue))
            is String -> activity.onPreferenceChange(preference, shared.getString(preference.key, defValue))
            is Set<*> -> {
                try {
                    activity.onPreferenceChange(preference, shared.getStringSet(preference.key, defValue as? Set<String>))
                } catch (ex: ClassCastException) {
                    LogU.w(TAG, ex)
                }
            }
            else -> LogU.w(TAG, UnsupportedOperationException("UNSUPPORTED value type"))
        }
    }
}