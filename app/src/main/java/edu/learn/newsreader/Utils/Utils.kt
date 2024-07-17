package edu.learn.newsreader.Utils

import org.ocpsoft.prettytime.PrettyTime
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    @JvmStatic
    fun dateFormat(oldStringDate: String?): String? {
        var newDate: String?
        val inputFormat: DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale(
                country
            )
        )
        try {
            val date = inputFormat.parse(oldStringDate!!)

            val outputFormat: DateFormat = SimpleDateFormat("E ,dd MMM yyyy",
                Locale("en", "IN"))
            newDate = date?.let { outputFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            newDate = oldStringDate
        }

        return newDate
    }

    private val country: String
        get() {
            val locale = Locale("en", "IN")
            val country = locale.country.toString()
            return country.lowercase(Locale.getDefault())
        }

    @JvmStatic
    fun dateToTimeFormat(oldStringDate: String?): String? {
        val p = PrettyTime(Locale("en", "IN"))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss",
                Locale.ENGLISH
            )
            val date = oldStringDate?.let { sdf.parse(it) }
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return isTime
    }
}
