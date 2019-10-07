package org.course.lab04;


import org.junit.Test;

import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;

public class InteroperabilityExerciseTest {

    @Test
    public void kotlinImplementationShouldExposeCompanionMethodsAndFieldsAsStatics() {
        Alarm weekDay = Alarm.weekDays(7, 0);
        assertEquals(5, weekDay.getDays().length);

        Alarm weekEnd = new Alarm(11, 30, Alarm.WEEKEND);
        assertEquals(2, weekEnd.getDays().length);
    }

    @Test
    public void kotlinImplementationShouldExposeOverloadedConstructor() {
        Alarm friday = new Alarm(11, DayOfWeek.FRIDAY);
        assertEquals(1, friday.getDays().length);
    }

    @Test
    public void kotlinImplementationShouldExposeExtensionMethods() {
        Alarm longWekend = new Alarm(11, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        assertEquals(3, Alarms.schedule(longWekend));


    }
}
