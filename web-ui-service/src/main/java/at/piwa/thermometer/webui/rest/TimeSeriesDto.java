package at.piwa.thermometer.webui.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TimeSeriesDto {

    private DateTime time;
    private List<Double> temperatureList = new ArrayList<>();

    public String getWellFormedTime() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("HH:mm:ss");
        return dtfOut.print(time);
    }

    public String getWellFormedDate() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd.MM.yyyy");
        return dtfOut.print(time);
    }

    public String getWellFormedForGraph() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        return dtfOut.print(time);
    }

    public String getWellFormedForGraph2() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dtfOut.print(time);
    }

}
