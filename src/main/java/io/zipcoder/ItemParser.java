package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Pattern;

public class ItemParser {

    private final Integer NUMBEROFITEMFIELDS = 4;
    private ErrorCounter errorCounter = ErrorCounter.getInstance();

    public List<Item> parseItemList(String valueToParse) {
        List<String> jerksonStringList = jerksonToList(valueToParse);
        List<Item> itemList = new ArrayList<>();
        for(String jerksonObject : jerksonStringList){
            try{
                Item item = parseSingleItem(jerksonObject);
                itemList.add(item);
            } catch(ItemParseException e){
                errorCounter.incrementErrors();
            }
        }
        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        singleItem = Pattern.compile("##").split(singleItem)[0];  //shouldn't be need. '##' should be taken care of in parseItemList.
        List<String> jerksonPairs = getKeyValueStrings(singleItem);
        Map<String, String> fieldMap = getKeyValuePairs(jerksonPairs);

        String name = Pattern.compile("0").matcher(fieldMap.get("name").toLowerCase()).replaceAll("o");
        String type = fieldMap.get("type").toLowerCase();
        String expiration = fieldMap.get("expiration").toLowerCase();
        Double price;
        try{
            price = Double.parseDouble(fieldMap.get("price"));
        } catch(NumberFormatException e){
            throw new ItemParseException();
        }

        return new Item(name, price, type, expiration);
    }

    protected List<String> jerksonToList(String jerksonPayload){
        String[] jerksonStringArray = Pattern.compile("##").split(jerksonPayload);
        return Arrays.asList(jerksonStringArray);
    }

    protected List<String> getKeyValueStrings(String jerksonObject) throws ItemParseException {
        String[] jerksonStringArray = Pattern.compile(";").split(jerksonObject);
        if(jerksonStringArray.length != NUMBEROFITEMFIELDS){
            System.out.println(jerksonObject);
            throw new ItemParseException();
        }
        else{
            return Arrays.asList(jerksonStringArray);
        }
    }

    protected Map<String, String> getKeyValuePairs(List<String> keyValueStrings) throws ItemParseException{
        Pattern namePattern = Pattern.compile("name", Pattern.CASE_INSENSITIVE);
        Pattern pricePattern = Pattern.compile("price", Pattern.CASE_INSENSITIVE);
        Pattern typePattern = Pattern.compile("type", Pattern.CASE_INSENSITIVE);
        Pattern expirationPattern = Pattern.compile("expiration", Pattern.CASE_INSENSITIVE);

        Map<String, String> map = new HashMap<>();
        for(String str : keyValueStrings){
            List<String> pair = stringToKeyValue(str);

            if(namePattern.matcher(pair.get(0)).matches()){
                map.put("name", pair.get(1));
            } else if( pricePattern.matcher(pair.get(0)).matches()){
                map.put("price", pair.get(1));
            } else if( typePattern.matcher(pair.get(0)).matches()) {
                map.put("type", pair.get(1));
            } else if( expirationPattern.matcher(pair.get(0)).matches()) {
                map.put("expiration", pair.get(1));
            } else {
                throw new ItemParseException();
            }
        }

        if(map.entrySet().size() != NUMBEROFITEMFIELDS){
            throw new ItemParseException();
        }
        else {
            return map;
        }
    }

    protected List<String> stringToKeyValue(String keyValue) throws ItemParseException{
        String[] pair = Pattern.compile("[:@^*%]").split(keyValue);
        if(pair.length != 2){
            throw new ItemParseException();
        }
        else{
            return Arrays.asList(pair);
        }
    }
}
