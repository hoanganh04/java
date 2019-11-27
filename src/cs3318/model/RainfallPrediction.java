package cs3318.model;

import cs3318.datastore.RainfallDataSource;

import java.time.LocalDate;
import java.util.Objects;

public abstract class RainfallPrediction {
    protected RainfallDataSource data;

    public RainfallPrediction(RainfallDataSource source) {
        Objects.requireNonNull(source);
        this.data = source;
    }

    public abstract Double predict(LocalDate date);
}