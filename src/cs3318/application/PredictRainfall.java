package cs3318.application;

import cs3318.datastore.RainfallDataSource;
import cs3318.datastore.RainfallDataSourceCSV;
import cs3318.datastore.RainfallDataSourceRandom;
import cs3318.exceptions.IllegalRainfallDataSourceException;

public class PredictRainfall {
    public static void main(String [] args) throws IllegalRainfallDataSourceException {
        RainfallDataSource corkAirport;

        try {
            corkAirport = new RainfallDataSourceCSV("Cork Airport", "resources/Rainfall-daily-cork-airport-1962-present.csv");
        }
        catch (IllegalRainfallDataSourceException e) {
            corkAirport = new RainfallDataSourceRandom("Cork Airport (dummy)");
        }

        corkAirport.getRecordingDates().forEach(System.out::println);
        corkAirport.getPrecipitation().forEach(System.out::println);
    }
}
