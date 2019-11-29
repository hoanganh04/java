package cs3318.application;

/**
 * Import required class from different packages.
 */
import cs3318.datastore.RainfallDataSource;
import cs3318.datastore.RainfallDataSourceCSV;
import cs3318.datastore.RainfallDataSourceRandom;
import cs3318.exceptions.IllegalRainfallDataSourceException;

/**
 * Try to predict rainfall of Cork Airport. Take a csv file as input for Source
 * then print date and prediction to the terminal.
 */
public class PredictRainfall {
    public static void main(String[] args) throws IllegalRainfallDataSourceException {
        RainfallDataSource corkAirport;
        //Try to assign data to corkAirport, throw error if file not exist.
        try {
            corkAirport = new RainfallDataSourceCSV("Cork Airport",
                    "resources/Rainfall-daily-cork-airport-1962-present.csv");
        } catch (IllegalRainfallDataSourceException e) {
            corkAirport = new RainfallDataSourceRandom("Cork Airport (dummy)");
            throw new IllegalRainfallDataSourceException("File not found.");
        }
        //Print record date and predict via System.out
        corkAirport.getRecordingDates().forEach(System.out::println);
        corkAirport.getPrecipitation().forEach(System.out::println);
    }
}
