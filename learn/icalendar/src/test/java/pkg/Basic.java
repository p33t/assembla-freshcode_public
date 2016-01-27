package pkg;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.property.*;
import biweekly.util.Duration;
import junit.framework.TestCase;
import pkg.builder.biweekly.ICalendarBuilder;
import pkg.builder.biweekly.component.VAlarmBuilder;
import pkg.builder.biweekly.component.VEventBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Basic extends TestCase {
    int y = 2016;
    int m = 1;
    int d = 25;

    private static Date ymd(int year, int month, int day) {
        return calendar(year, month, day).getTime();
    }

    private static Calendar calendar(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // Calendar month is 0-based
        c.set(Calendar.DATE, day);
        c.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return c;
    }

    private static Date dateTime(int year, int month, int day, int hrs, int mins) {
        Calendar c = calendar(year, month, day);
        c.set(Calendar.HOUR, hrs);
        c.set(Calendar.MINUTE, mins);
        return c.getTime();
    }

    /* TODO: This might be scope related (from Outlook sample)
METHOD:PUBLISH             Probably don't use just yet...see http://www.kanzaki.com/docs/ical/method.html
X-CALSTART:20160125T000000   Identifies the start time of the first instance of an appointment in the iCalendar file.  Ignored on Outlook import
X-CALEND:20160127T010000Z
X-CALEND:20160127T000000
X-CLIPSTART:20160121T140000Z Indicates the start of the date range that the user selected for export during the creation of the iCalendar file.  Ignored on Outlook import.
X-CLIPEND:20160128T140000Z
X-WR-RELCALID:{0000002E-7033-CD74-D555-E8B62594B1D6}

MS Outlook notes:
- Can hover over calendar checkbox to see last updated
- Send/Receive button also updates the calendar



    */
    public void testSimpleEvent() throws IOException {
        DateTimeStamp timestamp = new DateTimeStamp(new Date()); // probably a pseudo-version
        ICalendar ical = new ICalendarBuilder()
                .productId("-//learn.freshcode.biz//learn-icalendar")
                .calendarScale(CalendarScale.gregorian())
                .addProperty(new RawProperty("X-WR-CALNAME", "My Calendar")) // was in Motocal and Outlook
                .addEvent(new VEventBuilder()
                        // From Wikipedia "Outlook 2002 and Outlook 2003 demand a UID and a DTSTAMP"
                        .uid("jbloggs_2016-01-21") // A uuid that is deterministic?  Perhaps add type of hours or some origin code?
                        .summary("Whole Day Event")
                        .description("Description of whole day event")
                        .dateTimeStamp(timestamp)
                        .dateStart(dateStart(y, m, d))
                        // Might be more logical to use duration because dateEnd appears to be exclusive (?!)
                        .duration(Duration.builder().days(1).build())
                        .vEvent)
                .addEvent(new VEventBuilder()
                        .uid("jbloggs_2016-01-22T003000Z")
                        .summary("2 hrs with reminder (not)")
                        .description("Description 2")
                        .dateTimeStamp(timestamp)
                        .dateStart(dateTime(y, m, d + 1, 0, 30)) // 10:30a GMT+10
                        .duration(Duration.builder().hours(2).build())
                        // NOTE: Reminders will NOT be set if they are in the past
                        // PLUS, they don't seem to work at all with Internet Calendars (?!)
                        .addAlarm(new VAlarmBuilder(
                                Action.display(),
                                new Trigger(Duration.builder().minutes(15).prior(true).build(),
//                                        Related.START ... not used in Outlook
                                        null))
                                .description("Reminder") // required by Outlook
                                .vAlarm)
                        .vEvent)
                .iCalendar;
        Biweekly.write(ical).go(System.out);
        // NOTE: Use python http server in the target directory for Outlook Internet Calendar testing.
        // Logging is handy too.
        // python -m SimpleHTTPServer
        Biweekly.write(ical).go(new FileWriter("target/basic.ics"));
    }

    private DateStart dateStart(int year, int month, int day) {
        return new DateStart(ymd(year, month, day), false);
    }
}
