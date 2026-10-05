package models;

/**
 * Schedule is COMPOSED inside Course — demonstrates COMPOSITION.
 * If the Course is destroyed, its Schedule is destroyed too.
 * Schedule has no independent lifecycle outside a Course.
 */
public class Schedule {
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String room;

    public Schedule(String dayOfWeek, String startTime, String endTime, String room) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    // --- Getters / Setters ---

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime + "-" + endTime + " (Room " + room + ")";
    }
}
