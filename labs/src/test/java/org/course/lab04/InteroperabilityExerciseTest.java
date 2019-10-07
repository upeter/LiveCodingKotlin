package org.course.lab04;


import org.junit.Test;

import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InteroperabilityExerciseTest {

    @Test
    public void kotlinImplementationShouldExposeCompanionMethodsAndFieldsAsStatics() {
        //uncomment next lines

//        Alarm weekDay = Alarm.weekDays(7, 0);
//        assertEquals(5, weekDay.getDays().length);
//
//        Alarm weekEnd = new Alarm(11, 30, Alarm.WEEKEND);
//        assertEquals(2, weekEnd.getDays().length);
    }

    @Test
    public void kotlinImplementationShouldExposeOverloadedConstructor() {
        //uncomment next lines

//        Alarm friday = new Alarm(11, DayOfWeek.FRIDAY);
//        assertEquals(1, friday.getDays().length);
    }

    @Test
    public void kotlinImplementationShouldExposeExtensionMethods() {
        //uncomment next lines

//        Alarm longWekend = new Alarm(11, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
//        assertEquals(3, Alarms.schedule(longWekend));


    }
}
