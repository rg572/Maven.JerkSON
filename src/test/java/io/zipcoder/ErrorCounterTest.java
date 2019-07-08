package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorCounterTest {

    @Test
    public void noErrorsTest(){
        // Arrange
        ErrorCounter counter = ErrorCounter.getInstance();
        Integer expected = 0;

        // Act
        Integer actual = counter.getNumberOfErrors();

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void incrementErrorsTest(){
        // Arrange
        ErrorCounter counter = ErrorCounter.getInstance();
        Integer expected = 2;

        // Act
        counter.incrementErrors();
        counter.incrementErrors();
        Integer actual = counter.getNumberOfErrors();

        // Assert
        Assert.assertEquals(expected, actual);
    }

}