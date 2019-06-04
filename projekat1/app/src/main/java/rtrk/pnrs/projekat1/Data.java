package rtrk.pnrs.projekat1;

public class Data {

    private String day;
    private String date_time;
    private String location;
    private String temperature;
    private String pressure;
    private String humidity;
    private String sun_set;
    private String sun_rise;
    private String wind_speed;
    private String wind_direction;

    public Data(String day, String date_time,String location, String temperature, String humidity, String pressure,
                String sun_set, String sun_rise, String wind_direction, String wind_speed) {
            this.day =  day;
            this.location = location;
            this.date_time = date_time;
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            this.sun_rise = sun_rise;
            this.sun_set = sun_set;
            this.wind_speed = wind_speed;
            this.wind_direction = wind_direction;
        }



    public String getDay() { return day; }

    public String getDate_time() {
        return date_time;
    }

    public String getLocation() {
        return location;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSun_set() {
        return sun_set;
    }

    public String getSun_rise() {
        return sun_rise;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWind_direction() {
        return wind_direction;
    }
}
