package pkg.dates

import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.joda.time.chrono.ISOChronology
import org.joda.time.{Period, DateTime, DateTimeZone}

@RunWith(classOf[JUnitRunner])
class DaylightSavingsTest extends Suite {
  val zone = DateTimeZone.forID("Australia/Sydney") // a time zone that has daylight savings
  val chron = ISOChronology.getInstance(zone) // internationally accepted, slightly different to Gregorian.
  val epoch = new DateTime(0L, chron)
  val begin = epoch.withYear(2011).withMonthOfYear(10).withDayOfMonth(2).withHourOfDay(3) // 2 not allowed
  val end = epoch.withYear(2012).withMonthOfYear(4).withDayOfMonth(1).withHourOfDay(2)
  val min1 = Period.minutes(1)

  def testBeginEnd() {
    println("\nBegin:")
    println(begin.minus(min1))
    println(begin + " <-- Notice both time zone and hr increment at trans'n")
    println("\nEnd:")
    println(end.minus(min1))
    println(end + " <-- Notice both time zone and hr decrement at trans'n")
  }
}