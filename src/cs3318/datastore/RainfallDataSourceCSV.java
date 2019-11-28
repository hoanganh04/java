package cs3318.datastore;

import cs3318.exceptions.IllegalRainfallDataSourceException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class RainfallDataSourceCSV extends RainfallDataSource {
    public RainfallDataSourceCSV(String station, String dataSource) throws IllegalRainfallDataSourceException {
        super(station, dataSource);
    }

    @Override
    protected void getDataFrom(String source) throws IllegalRainfallDataSourceException {
        try (Scanner scanner = new Scanner(new File(this.getDataSource()))) {
            this.readCSVDataFrom(scanner);
        } catch (FileNotFoundException e) {
            throw new IllegalRainfallDataSourceException("File not found.");
        }
    }

    private void readCSVDataFrom(Scanner scanner) {
        Integer recordNumber = 0;
        this.precipitation = new ArrayList<>();
        this.recordingDates = new HashMap<>();

        while (scanner.hasNext()) {
            String record = scanner.next();
            try {
                this.recordingDates.put(recordNumber, this.getDateFrom(record));
                this.precipitation.add(this.getPrecipitationFrom(record));
                recordNumber += 1;
            } catch (NumberFormatException | DateTimeParseException e) {
                // ignore the record ... is this a wise course of action
                System.out.println(e);
            }
        }
    }

    private Double getPrecipitationFrom(String line) {
        String[] fields = line.split(",");

        return Double.parseDouble(fields[1]);

    }

    private LocalDate getDateFrom(String line) {
        String[] fields = line.split(",");
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MMM-yy" );
        LocalDate ld = LocalDate.parse( fields[0], f );

        return ld;
    }
}
