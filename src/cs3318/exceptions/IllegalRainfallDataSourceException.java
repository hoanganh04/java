package cs3318.exceptions;

//Exception for FileNotFound Exception
public class IllegalRainfallDataSourceException extends Exception {
    public IllegalRainfallDataSourceException(){
        System.out.println("File not found");
    }
}
