package org.course.lab04

import org.course.lab04.Alarm.Companion.LOG
import org.slf4j.LoggerFactory
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.math.absoluteValue

/**
 * Exercise 1:
 * Take a look at the Alarm Api. This Api is written for a Java audience. Enhance the Alarm class with Kotlin's interoperability annotations,
 * so that all tests in @see org.course.lab04.InteroperabilityExerciseTest.*java* succeed.
 * The implementation of Alarm must not be changed.
 */
class Alarm constructor(val hour: Int = 0, val minute: Int = 0, vararg val days: DayOfWeek) {

    companion object {
        fun weekDays(hour: Int, minute: Int) = Alarm(hour, minute, * WEEKDAYS)

        val WEEKDAYS = arrayOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
        val WEEKEND = arrayOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
        val LOG = LoggerFactory.getLogger(Alarm::class.java)
    }
}

private val alarmTimer = Timer("Alarm")

fun Alarm.schedule():Int {
    val MINUTE_IN_MILLIS: Long = 60 * 1000
    val DAY_IN_MINUTES: Long = 24 * 60
    val WEEK_IN_MILLIS: Long = 7 * DAY_IN_MINUTES * MINUTE_IN_MILLIS
    val now = LocalDateTime.now()
    val nowMillis = now.atZone(ZoneId.of("Europe/Amsterdam")).toInstant().toEpochMilli()
    fun inMinutes(hour: Int, minute: Int) = hour * 60 + minute

    fun scheduledEpoch(weekDay: DayOfWeek): Long = ((weekDay.value - now.dayOfWeek.value).absoluteValue.toLong()).let { inDays ->
        val fromNowInMinutes = inMinutes(hour, minute) - inMinutes(now.hour, now.minute) + inDays * DAY_IN_MINUTES
        (nowMillis + fromNowInMinutes * MINUTE_IN_MILLIS).let { scheduledEpoch ->
            if (scheduledEpoch < nowMillis) scheduledEpoch + WEEK_IN_MILLIS else scheduledEpoch
        }
    }
    return this.days
            .map { Date(scheduledEpoch(it)) }
            .sorted()
            .map {
                alarmTimer.schedule(object : TimerTask() { override fun run() { LOG.info("Alarm! $it") } }, it)
                LOG.info("Scheduled alarm at: $it")
            }.size

}