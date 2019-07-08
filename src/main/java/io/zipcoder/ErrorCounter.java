package io.zipcoder;

public class ErrorCounter {
    private static ErrorCounter ourInstance = new ErrorCounter();
    private Integer numberOfErrors = 0;

    public static ErrorCounter getInstance() {
        return ourInstance;
    }

    private ErrorCounter() {
    }

    public Integer getNumberOfErrors(){
        return numberOfErrors;
    }

    public void incrementErrors(){
        numberOfErrors++;
    }
}
