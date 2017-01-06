package iii_conventions

import java.time.LocalDate

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val myDate = this.asLocalDate()
        val otherDate = other.asLocalDate()

        return myDate.compareTo(otherDate)
    }

    operator fun plus(interval: TimeInterval): MyDate {
        val myDate = asLocalDate()

        val retDate = when (interval) {
            TimeInterval.DAY -> myDate.plusDays(1)
            TimeInterval.WEEK -> myDate.plusWeeks(1)
            TimeInterval.YEAR -> myDate.plusYears(1)
        }

        return MyDate(retDate.year, retDate.monthValue, retDate.dayOfMonth)
    }

    fun asLocalDate(): LocalDate = LocalDate.of(year, month, dayOfMonth)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange =
        DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var currentDate: MyDate = start
            var iterating = false

            override fun hasNext(): Boolean {
                val nextDate = currentDate.nextDay()

                return nextDate <= endInclusive
            }

            override fun next(): MyDate {
                if (!iterating) {
                    iterating = true
                } else {
                    currentDate = currentDate.nextDay()
                }

                return currentDate
            }

        }
    }

    operator fun contains(d: MyDate): Boolean {
        val startDate = start.asLocalDate()
        val endDate = endInclusive.asLocalDate()
        val test = d.asLocalDate()

        return test >= startDate && test <= endDate
    }
}
