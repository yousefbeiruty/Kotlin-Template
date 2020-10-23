package com.it.yousefl.kotlintemplate.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.it.yousefl.kotlintemplate.BaseApplication
import java.util.Calendar.getInstance

object PreferenceUtil {
    private fun getDefaultSharedPreference(context: Context): SharedPreferences? {
        return if (PreferenceManager.getDefaultSharedPreferences(
                BaseApplication.instance?.applicationContext
            ) != null
        ) PreferenceManager.getDefaultSharedPreferences(
            BaseApplication.instance?.applicationContext
        ) else null
    }

    fun setSelectedLanguageId(id: String?) {
        val prefs =
            BaseApplication.instance?.applicationContext?.let {
                getDefaultSharedPreference(
                    it
                )
            }
        val editor = prefs!!.edit()
        editor.putString("app_language_id", id)
        editor.apply()
    }

    fun getSelectedLanguageId(): String? {
        return BaseApplication.instance?.applicationContext?.let {
            getDefaultSharedPreference(it)
                ?.getString("app_language_id", "en")
        }
    }
}