package cs3318.model;

import cs3318.datastore.RainfallDataSource;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class AveragingPrediction extends RainfallPrediction {
    private int numberOfSamples = 3;

    public AveragingPrediction(RainfallDataSource source) {
        super(source);
    }

    public void setNumberOfSamples(int n) {
        this.numberOfSamples = n;
    }

    @Override
    public Double predict(LocalDate date) {
        Double [] predictionData = new Double[this.numberOfSamples];

        LocalDate sampleYear = date.minus(1, ChronoUnit.YEARS);

        for (int i = 0; i < this.numberOfSamples; i += 1) {
            Integer indexOfDate = data.getRecordingDates().indexOf(sampleYear);
            predictionData[i] = data.getPrecipitation().get(i);
            sampleYear = date.minus(1, ChronoUnit.YEARS);
        }

        return Arrays.stream(predictionData).mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }
}
