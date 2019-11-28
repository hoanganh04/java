package cs3318.model;

import cs3318.datastore.RainfallDataSource;
import cs3318.exceptions.NullSourceException;

import java.time.LocalDate;
import java.util.Objects;

public abstract class RainfallPrediction {
    protected RainfallDataSource data;

    public RainfallPrediction(RainfallDataSource source) throws NullSourceException {
        Objects.requireNonNull(source);
        this.data = source;
    }

    public abstract Double predict(LocalDate date) throws NullSourceException;
}