package at.piwa.thermometer.webui.database;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@DynamoDBTable(tableName = "Temperature")
public class TemperatureDto {


    @DynamoDBHashKey(attributeName = "TemperatureID")
    private String temperatureID;

    @DynamoDBAttribute
    private Payload payload;

    @Data
    @NoArgsConstructor
    @DynamoDBDocument
    public static class Payload {

        @DynamoDBAttribute(attributeName = "TemperatureID")
        private String temperatureID;

        @DynamoDBAttribute
        @DynamoDBTypeConverted( converter = TemperatureDto.Payload.DateTimeConverter.class )
        private DateTime time;

        @DynamoDBAttribute(attributeName = "sensor")
        private String sensorId;

        @DynamoDBAttribute
        private Double temperature;

        public static class DateTimeConverter implements DynamoDBTypeConverter<Long, DateTime> {

            @Override
            public Long convert( final DateTime time ) {
                return time.getMillis();
            }

            @Override
            public DateTime unconvert( final Long longValue ) {
                return new DateTime(longValue);
            }

        }
    }

}


