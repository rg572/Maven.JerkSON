package io.zipcoder;

import io.zipcoder.utils.ItemParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemParserCustomTest {

    @Test
    public void jerksonToListTest1(){
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "fuck##this jerkson##bull:@##shi^*%t##";
        List<String> expected = Arrays.asList("fuck","this jerkson","bull:@","shi^*%t");

        // Act
        List<String> actual = parser.jerksonToList(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void jerksonToListTest2(){
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "fuck##this jerkson##bull:@##shi^*%t";
        List<String> expected = Arrays.asList("fuck","this jerkson","bull:@","shi^*%t");

        // Act
        List<String> actual = parser.jerksonToList(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getKeyValuePairsTest1() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "fuck;this;jerkson;bullshit";
        List<String> expected = Arrays.asList("fuck","this","jerkson","bullshit");

        // Act
        List<String> actual = new ArrayList<>();
        actual = parser.getKeyValueStrings(input);


        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getKeyValuePairsTest2() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "fuck;this;jerkson;bullshit;";
        List<String> expected = Arrays.asList("fuck", "this", "jerkson", "bullshit");

        // Act
        List<String> actual = new ArrayList<>();
        actual = parser.getKeyValueStrings(input);


        // Assert
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = ItemParseException.class)
    public void getKeyValuePairsTest3() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "fuck;this;jerkson bullshit";
        List<String> expected = Arrays.asList("fuck","this","jerkson","bullshit");

        // Act
        List<String> actual = new ArrayList<>();
        actual = parser.getKeyValueStrings(input);


        // Assert
        //Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToKeyValueTest1() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck@This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToKeyValueTest2() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck:This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToKeyValueTest3() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck^This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToKeyValueTest4() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck*This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToKeyValueTest5() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck%This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ItemParseException.class)
    public void stringToKeyValueTest6() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck;This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = ItemParseException.class)
    public void stringToKeyValueTest7() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck##This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ItemParseException.class)
    public void stringToKeyValueTest8() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck*This^shit";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ItemParseException.class)
    public void stringToKeyValueTest9() throws ItemParseException{
        // Arrange
        ItemParser parser = new ItemParser();
        String input = "Fuck This";
        List<String> expected = Arrays.asList("Fuck","This");

        // Act
        List<String> actual = parser.stringToKeyValue(input);

        // Assert
        Assert.assertEquals(expected, actual);
    }



}