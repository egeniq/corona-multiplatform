package com.multiplatform.sample.androidApp

import android.content.Context
import android.telephony.TelephonyManager
import java.util.*

/**
 * Created by Dima Kovalenko.
 */
object Utils {

    fun getCountryNameByISO(userCountryISO: String?): String? {
        if (userCountryISO == null) {
            return null
        }
        try {
            val loc = Locale("", userCountryISO)
            return loc.displayCountry
        } catch (e: Exception) {
            return null
        }
    }

    fun getUserCountry(context: Context): String? {
        try {
            val tm =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US)
                }
            }
        } catch (e: Exception) {
        }
        return null
    }

    fun localeToEmoji(locale: Locale): String? {
        val countryCode = locale.country
        val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(
            Character.toChars(
                secondLetter
            )
        )
    }

    fun mapCountryName (isoName: String?): String? {
        return when (isoName) {
            "United States" -> "US"
            else -> isoName
        }
    }
}