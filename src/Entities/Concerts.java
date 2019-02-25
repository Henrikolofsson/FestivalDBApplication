package Entities;

public class Concerts {
    private String band_id;
    private String day;
    private String time;
    private String scene;

    public Concerts(String band_id, String day, String time, String scene){
        this.band_id = band_id;
        this.day = day;
        this.time = time;
        this.scene = scene;
    }

    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "Concerts{" +
                "band_id='" + band_id + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", scene='" + scene + '\'' +
                '}';
    }
}
