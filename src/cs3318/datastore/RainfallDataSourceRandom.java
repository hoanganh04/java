package cs3318.datastore;

import cs3318.exceptions.IllegalRainfallDataSourceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RainfallDataSourceRandom extends RainfallDataSource {
    public RainfallDataSourceRandom(String station) throws IllegalRainfallDataSourceException {
        super(station, "Random Source");
    }


    @Override
    protected void getDataFrom(String source) throws IllegalRainfallDataSourceException {
        Random numberGenerator = new Random();
        int randomSize = 10 + numberGenerator.nextInt(100);
        this.precipitation = new ArrayList<>(randomSize);
        this.recordingDates = new HashMap<>(randomSize);

        for (int i = 0; i < this.precipitation.size(); i += 1) {
            this.precipitation.set(i, numberGenerator.nextDouble());
            this.recordingDates.put(i, this.generateDate(i));
        }
    }

    private LocalDate generateDate(int i) {
        LocalDate today =  LocalDate.now();

        return today.minusDays(i);
    }
}
