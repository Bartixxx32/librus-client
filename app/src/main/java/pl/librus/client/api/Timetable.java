package pl.librus.client.api;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Timetable implements Serializable {

    private static final long serialVersionUID = -1448021508657217605L;
    private final String TAG = "librus-client-log";
    private final Map<LocalDate, SchoolDay> timetable = new HashMap<>();


    Timetable() {
    }

    public Lesson getLesson(LocalDate date, int lessonNumber) {
        if (timetable.containsKey(date)) {
            if (timetable.get(date).getLesson(lessonNumber) != null) {
                return timetable.get(date).getLesson(lessonNumber);
            }
        }
        return null;
    }

    void addSchoolDay(SchoolDay schoolDay) {
        timetable.put(schoolDay.getDate(), schoolDay);
    }

    Map<LocalDate, SchoolDay> getTimetable() {
        return timetable;
    }

    public SchoolDay getSchoolDay(LocalDate date) {
        return timetable.get(date);
    }
}
