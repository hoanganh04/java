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
//Return number of samples.
    public int getNumberOfSamples(int n) {
        return numberOfSamples;
    }

    @Override
    public Double predict(LocalDate date) {
        Double [] predictionData = new Double[this.numberOfSamples];
        Double data_check;
        boolean array_check=true;

        LocalDate sampleYear = date.minus(1, ChronoUnit.YEARS);

        for (int i = 0; i < this.numberOfSamples; i += 1) {
            Integer indexOfDate = data.getRecordingDates().indexOf(sampleYear);
//Index of date validation.
            if (indexOfDate!=0){
                data_check = data.getPrecipitation().get(i);
//Prediction Data elements validation.
                if (data_check!=null){
                    predictionData[i]=data_check;
                }
                else {
                    array_check=false;
                    break;
                }
                sampleYear = date.minus(1, ChronoUnit.YEARS);
            }
            else {
                array_check=false;
                break;
            }
        }
// Prediction Data array validation.
        if (array_check){
            return Arrays.stream(predictionData).mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        }
        else {
            return null;
        }
    }


}
