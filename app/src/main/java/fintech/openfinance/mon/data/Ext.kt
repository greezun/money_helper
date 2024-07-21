package fintech.openfinance.mon.data

import android.content.Context
import android.content.SharedPreferences
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun getCurrentMonthShort(): String {
    val currentMonth = LocalDate.now().month
    return currentMonth.getDisplayName(TextStyle.SHORT, Locale("ru")).take(3)
}

private fun getPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
}

fun savePreference(context: Context, key: String, value: String) {
    val prefs = getPreferences(context)
    with(prefs.edit()) {
        putString(key, value)
        apply()
    }
}

fun getPreference(context: Context, key: String): String? {
    val prefs = getPreferences(context)
    return prefs.getString(key, null)
}