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
  val begin1am = epoch.withYear(2011).withMonthOfYear(10).withDayOfMonth(2).withHourOfDay(1)
  val begin3amDst = begin1am.withHourOfDay(3) // 2 not allowed
  val end1amDst = epoch.withYear(2012).withMonthOfYear(4).withDayOfMonth(1).withHourOfDay(1)
  // NOTE the ambiguity!
  val end2amDst = end1amDst.withHourOfDay(2)
  val end2am = epoch.withYear(2012).withMonthOfYear(4).withDayOfMonth(1).withHourOfDay(2)
  val min1 = Period.minutes(1)

  def testBegin() {
    println("\nBegin:")
    require(begin1am.plusHours(1) == begin3amDst)
    println(begin3amDst.minus(min1))
    println(begin3amDst + " <-- Notice both time zone and hr increment at trans'n")
  }

  def testEnd() {
    println("\nEnd:")
    require(end1amDst.plusHours(2) == end2am)
    require(end2amDst.plusHours(1) == end2am)
    println(end2am.minus(min1))
    println(end2am + " <-- Notice both time zone and hr decrement at trans'n")
  }
}