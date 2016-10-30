package pl.librus.client;

import org.joda.time.LocalDate;

import java.io.Serializable;

class Event implements Serializable {

    private String category;
    private String description;
    private LocalDate date;
    private int lessonNumber;

    Event(String category, String description, LocalDate date, int lessonNumber) {
        this.description = description;
        this.category = category;
        this.date = date;
        this.lessonNumber = lessonNumber;
    }

    String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
