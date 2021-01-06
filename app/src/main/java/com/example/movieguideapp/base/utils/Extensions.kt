package com.example.movieguideapp.base.utils

import okhttp3.RequestBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

const val API_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DEFAULT_PRETTY_DATE_FORMAT = "EEEE, MMM d"
const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
const val PRETTY_DAY_MONTH_DAY = "EEEE, MMMM d"
const val FORMAT_TIME_ONLY = "h:mm a"
const val FORMAT_DATE_SEPARATOR = "EEE, dd MMMM, h:mm a"

val JSONArray.contents: List<*>
    get() = (0 until length()).map { get(it) }

val JSONObject.contents: List<Pair<String, *>>
    get() {
        val list = mutableListOf<Pair<String, Any>>()
        keys().forEach {
            list.add(it to get(it))
        }
        return list
    }

fun <T : Any> T.toWeakReference(): WeakReference<T> {
    return WeakReference(this)
}

fun JSONObject?.toMap(): Map<String, String> {
    if (this == null) {
        return emptyMap()
    }

    return contents.associate { (key, value) -> key to value.toString() }
}

fun String?.fromJsonToMap(): Map<String, String> {
    return if (isNullOrBlank()) {
        emptyMap()
    } else {
        JSONObject(this).toMap()
    }
}

fun String?.letIfNotEmpty(func: (value: String) -> Unit) {
    this?.let { if (it.isNotEmpty()) func.invoke(it) }
}

inline fun <reified T> String.getJsonValue(valueKey: String, vararg objectKeys: String): T? {
    return try {
        var jsonObject = JSONObject(this)
        if (objectKeys.isEmpty()) {
            return if (T::class == Double::class) {
                jsonObject.getDouble(valueKey) as? T // hack for the double parsing
            } else {
                jsonObject.get(valueKey) as? T
            }
        }

        for (element in objectKeys) {
            jsonObject = jsonObject.getJSONObject(element)
        }
        return jsonObject.get(valueKey) as? T
    } catch (e: JSONException) {
        null
    }
}

fun Any?.nonNullableToString(): String? {
    return this?.toString()
}

fun List<*>.isAllElementsDistinct() = distinct().count() == size

fun <T> List<T>.appendToNewList(newObject: T): List<T> {
    val newList = mutableListOf(newObject)
    newList.addAll(this)
    return newList
}

fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)

fun String?.toCalendar(
    format: String = DEFAULT_DATE_FORMAT,
    timeZone: TimeZone = UTC_TIME_ZONE
): Calendar? {
    if (this.isNullOrEmpty()) return null
    return try {
        val sdf = createSimpleDataFormat(format, timeZone).parse(this)
        val cal = createCalendar()
        sdf?.let { cal.time = it }
        cal
    } catch (exception: Exception) {
        null
    }
}

fun Calendar.setFirstDateOfTheWeek(timeZone: TimeZone = UTC_TIME_ZONE) {
    this.timeZone = timeZone
    add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - get(Calendar.DAY_OF_WEEK))
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Calendar?.toFormattedString(format: String = "EEEE, MMM d", timeZone: TimeZone): String {
    val text: String? = try {
        val simpleDateFormat = createSimpleDataFormat(format, timeZone)
        this?.let { simpleDateFormat.format(it.time) }
    } catch (exception: Exception) {
        null
    }
    return text.orEmpty()
}

fun String?.toCalendarFromUtc(): Calendar? {
    return try {
        val parsedText =
            this?.replace("'T'", " ")?.replace(".SSSZ", "")?.replace("T", " ") ?: return null
        val sdf = createSimpleDataFormat("yyyy-MM-dd HH:mm:ss").parse(parsedText)
        val cal = createCalendar()
        sdf?.let { cal.time = it }
        cal
    } catch (exception: Exception) {
        null
    }
}

fun String?.toPrettyDate(
    format: String = DEFAULT_PRETTY_DATE_FORMAT,
    parseFormat: String = DEFAULT_DATE_FORMAT,
    timeZone: TimeZone = UTC_TIME_ZONE
): String {
    return this?.let {
        val parseDate = createSimpleDataFormat(parseFormat, timeZone).parse(it) ?: return ""
        val outFormat = createSimpleDataFormat(format, timeZone)
        outFormat.format(parseDate)
    } ?: ""
}

fun Calendar.isSameDay(otherCalendar: Calendar?): Boolean {
    if (otherCalendar == null) return false

    return get(Calendar.ERA) == otherCalendar.get(Calendar.ERA) &&
            get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
            get(Calendar.DAY_OF_YEAR) == otherCalendar.get(Calendar.DAY_OF_YEAR)
}

fun Calendar.isOneDayBefore(otherCalendar: Calendar?): Boolean {
    if (otherCalendar == null) return false

    return get(Calendar.ERA) == otherCalendar.get(Calendar.ERA) &&
            get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
            get(Calendar.DAY_OF_YEAR) == otherCalendar.get(Calendar.DAY_OF_YEAR) - 1
}

fun Calendar.isSameWeek(otherCalendar: Calendar?): Boolean {
    if (otherCalendar == null) return false
    return get(Calendar.WEEK_OF_YEAR) == otherCalendar.get(Calendar.WEEK_OF_YEAR)
}

fun Map<String, *>.toStringsMap() = mapValues { it.value.toString() }

fun listOfNotEmpty(vararg elements: CharSequence?): List<CharSequence?> =
    elements.filterNot { it.isNullOrEmpty() }

fun RequestBody.readAsString(): String {
    val buffer = Buffer()
    writeTo(buffer)

    val outputStream = ByteArrayOutputStream()
    buffer.writeTo(outputStream)
    return outputStream.toString(Charsets.UTF_8.name())
}

fun MutableMap<String, String>.getAndRemove(key: String): String? {
    val map = get(key) ?: return null
    remove(key)
    return try {
        JSONArray(map).contents.joinToString(",")
    } catch (e: Exception) {
        map
    }
}

fun Map<String, String>.getAllValues(): List<String> {
    return values.flatMap { value ->
        try {
            JSONArray(value).contents.map { it.toString() }
        } catch (e: Exception) {
            listOf(value)
        }
    }
}

fun <K, V> mapOfNotNulls(vararg pairs: Pair<K, V?>) = pairs.mapNotNull { pair ->
    pair.second?.let { value -> pair.first to value }
}.toMap()
