package cs3318.model;

import cs3318.datastore.RainfallDataSource;
import cs3318.exceptions.NullSourceException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class AveragingPrediction extends RainfallPrediction {
    private int numberOfSamples = 3;

    public AveragingPrediction(RainfallDataSource source) throws NullSourceException {
        super(source);
    }

    public void setNumberOfSamples(int n) {
        this.numberOfSamples = n;
    }

    // Return number of samples.
    public int getNumberOfSamples(int n) {
        return numberOfSamples;
    }

    @Override
    public Double predict(LocalDate date) throws NullSourceException {
        Double[] predictionData = new Double[this.numberOfSamples];
        Double data_check;
        boolean array_check = true;

        LocalDate sampleYear = date.minus(1, ChronoUnit.YEARS);

        for (int i = 0; i < this.numberOfSamples; i += 1) {
            try {
                Integer indexOfDate = data.getRecordingDates().indexOf(sampleYear);
                // Prediction Data elements validation.
                try {
                    data_check = data.getPrecipitation().get(i);
                    predictionData[i] = data_check;
                    sampleYear = date.minus(1, ChronoUnit.YEARS);
                } catch (NumberFormatException e) {

                    array_check = false;
                    System.out.println(e);
                    break;
                }
            } catch (NullPointerException e) {
                throw new NullSourceException("Source is null,can not obtain.");
            }
        }
        // Prediction Data array validation.
        if (array_check) {
            return Arrays.stream(predictionData).mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        } else {
            return null;
        }

    }
}