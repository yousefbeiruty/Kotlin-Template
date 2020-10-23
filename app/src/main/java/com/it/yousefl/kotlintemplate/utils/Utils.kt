package com.it.yousefl.kotlintemplate.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.it.yousefl.kotlintemplate.ui_views.AuthActivity
import com.it.yousefl.kotlintemplate.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val TAG = "Utils"
    private val gson = Gson()

    @Synchronized
    fun setValue(context: Context?, key: String?, value: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(key, value).apply()
    }

    @Synchronized
    fun getValue(context: Context?, key: String?, defaultValue: String?): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, defaultValue)
    }


    @Synchronized
    fun setValue(context: Context?, key: String?, value: Float) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putFloat(key, value).apply()
    }

    @Synchronized
    fun getValue(context: Context?, key: String?, defaultValue: Float): Float {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getFloat(key, defaultValue)
    }

    @Synchronized
    fun setValue(context: Context?, key: String?, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putInt(key, value).commit()
    }

    @Synchronized
    fun getValue(context: Context?, key: String?, defaultValue: Int): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(key, defaultValue)
    }

    @Synchronized
    fun setValue(context: Context?, key: String?, value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean(key, value).commit()
    }

    @Synchronized
    fun getValue(context: Context?, key: String?, defaultValue: Boolean): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean(key, defaultValue)
    }

    @Synchronized
    fun setValue(context: Context?, key: String?, value: Long) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putLong(key, value).commit()
    }

    @Synchronized
    fun getValue(context: Context?, key: String?, defaultValue: Long): Long {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getLong(key, defaultValue)
    }

    fun isValidPhoneNumber(editText: EditText): Boolean {
        var status = false
        if (editText.text.toString().trim { it <= ' ' }.length >= 10) {
//            if (editText.getText().toString().trim().startsWith("00962")) {
//                status = true;
//            }
            status = true
        }
        return status
    }

    fun isValidEmail(editText: EditText): Boolean {
        var status = false
        if (editText.text.toString().trim { it <= ' ' }.length > 8) {
            if (editText.text.toString().trim { it <= ' ' }.contains("@")) {
                status = true
            }
        }
        return status
    }

    fun getDaysDifference(date1: String?, date2: String?): Long {
        val dateFormat = SimpleDateFormat(
            "dd/MM/yyyy", Locale.ENGLISH
        )
        var mydate1: Date? = null
        var mydate2: Date? = null
        try {
            mydate1 = dateFormat.parse(date1)
            mydate2 = dateFormat.parse(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = mydate2!!.time - mydate1!!.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        return hours / 24
    }

    fun launchMaps(context: Context, latitude: Double, longitude: Double) {
//        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        context.startActivity(intent);
        val directionsBuilder = Uri.Builder()
            .scheme("https")
            .authority("www.google.com")
            .appendPath("maps")
            .appendPath("dir")
            .appendPath("")
            .appendQueryParameter("api", "1")
            .appendQueryParameter("destination", "$latitude,$longitude")
        context.startActivity(Intent(Intent.ACTION_VIEW, directionsBuilder.build()))
    }

    fun convertStringToDateTime(date: String?): String? {
        val dateFormat = SimpleDateFormat(
            "dd/MM/yyyy", Locale.ENGLISH
        )
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return try {
            val timeFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH)
            timeFormat.format(myDate)
        } catch (e: Exception) {
            ""
        }
    }


    fun isValidInput(editText: EditText): Boolean {
        var status = false
        if (editText.text.toString().trim { it <= ' ' }.length > 0) {
            status = true
        }
        return status
    }

    fun isValidIdentifier(editText: EditText): Boolean {
        var status = false
        if (editText.text.toString().trim { it <= ' ' }.length == 10) {
            status = true
        }
        return status
    }


//    fun getStringInLang(context: Context?, english: String?, arabic: String?): String? {
//        var str: String? = ""
//        str =
//            if (Utils.getValue(context, Constants.USER_LANGUAGE, "en")
//                    .equalsIgnoreCase(Constants.ENGLISH_LANGUAGE)
//            ) {
//                english
//            } else {
//                arabic
//            }
//        return str
//    }


    fun refreshLocal(context: Context) {
        try {
            var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            if (sharedPreferences.getString("lang", "en") == "en") {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                sharedPreferences.edit().putString("lang", "en").apply()
                updateLanguage(context, sharedPreferences)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    (context as Activity).window.decorView.layoutDirection =
                        View.LAYOUT_DIRECTION_LTR
                }
                PreferenceUtil.setSelectedLanguageId("en")
            } else {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                sharedPreferences.edit().putString("lang", "ar").apply()
                updateLanguage(context, sharedPreferences)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    (context as Activity).window.decorView.layoutDirection =
                        View.LAYOUT_DIRECTION_RTL
                }
                PreferenceUtil.setSelectedLanguageId("ar")
            }
        } catch (e: Exception) {
            Log.e("fff", "refreshLocal: ")
        }
    }


    fun updateLanguage(cxt: Context, sharedPreferences: SharedPreferences) {
//        setLocale(sharedPreferences.getString("lang", "en"));
        val local = Locale(sharedPreferences.getString("lang", "en"))
        Locale.setDefault(local)
        val configuration = cxt.resources.configuration
        configuration.setLocale(local)
        cxt.resources.updateConfiguration(configuration, cxt.resources.displayMetrics)
    }


    fun isValidPassword(password: EditText, confirmPassword: EditText): Boolean {
        var status = false
        if (password.text.toString()
                .trim { it <= ' ' }.length > 6 && confirmPassword.text.toString()
                .trim { it <= ' ' }.length > 6
        ) {
            if (password.text.toString() == confirmPassword.text.toString()) status = true
        }
        return status
    }


    fun goToActivityWithAnimation(context: Context, to: Class<*>?, finishAfter: Boolean) {
        val i = Intent(context, to)
        context.startActivity(i)
        (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        if (finishAfter) {
            context.finish()
        }
    }


    fun goToHomeActivityWithAnimation(context: Context, finishAfter: Boolean) {
        val i = Intent(context, AuthActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(i)
        (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        if (finishAfter) {
            context.finish()
        }
    }

    fun goToActivity(context: Context, to: Class<*>?, finishAfter: Boolean) {
        val i = Intent(context, to)
        context.startActivity(i)
        if (finishAfter) {
            (context as Activity).finish()
        }
    }

//    fun getCurrentLanguageFontPath(context: Context?): String? {
//        val fontPath: String
//        fontPath = if (Utils.getValue(
//                context,
//                Constants.USER_LANGUAGE,
//                Constants.ENGLISH_LANGUAGE
//            ).equalsIgnoreCase(Constants.ENGLISH_LANGUAGE)
//        ) {
//            "fonts/english_font.ttf"
//        } else {
//            "fonts/arabic_font.ttf"
//        }
//        return fontPath
//    }


    fun getDialog(context: Context, msg: String?, title: String?) {
        val alertDialog2 = AlertDialog.Builder(
            context
        )
        alertDialog2.setTitle(title)
        alertDialog2.setMessage(msg)


// Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton(
            context.resources.getString(R.string.ok)
        ) { dialog, which ->
            // Write your code here to execute after dialog
            //                        Toast.makeText(context,
            //                                "You clicked on YES", Toast.LENGTH_SHORT)
            //                                .show();
        }

// Setting Negative "NO" Btn
//        alertDialog2.setNegativeButton("NO",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Write your code here to execute after dialog
//                        Toast.makeText(context,
//                                "You clicked on NO", Toast.LENGTH_SHORT)
//                                .show();
//                        dialog.cancel();
//                    }
//                });

// Showing Alert Dialog
        alertDialog2.show()
    }

    fun toJson(`object`: Any?): String? {
        return gson.toJson(`object`)
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>?): T {
        return gson.fromJson(json, classOfT)
    }

    fun <T> fromJson(jsonElement: JsonElement?, classOfT: Class<T>?): T {
        return gson.fromJson(jsonElement, classOfT)
    }

    fun getDateTrip(): String? {
        // Get current date to send it
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd/MM/yyyy")
        return df.format(c)
    }

    private fun distance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val earthRadius = 3958.75 // in miles, change to 6371 for kilometer output
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        val sindLat = Math.sin(dLat / 2)
        val sindLng = Math.sin(dLng / 2)
        val a = Math.pow(sindLat, 2.0) + (Math.pow(sindLng, 2.0)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)))
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return earthRadius * c // output distance, in MILES
    }
}