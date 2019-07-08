package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.*;

public class GroceryReporter {
    private final String originalFileText;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    @Override
    public String toString() {
        List<Item> itemList = new ItemParser().parseItemList(originalFileText);
        Map<String, List<Item>> itemNameMap = new HashMap<>();
        for(Item item : itemList){
            List<Item> singleItem= new ArrayList<>();
            singleItem.add(item);
            itemNameMap.merge(item.getName(), singleItem, (v1, v2)-> {v1.addAll(v2); return v1;});
        }

        StringBuilder sbuild = new StringBuilder();
        for(Map.Entry<String, List<Item>> entry : itemNameMap.entrySet()){
            String entryName = entry.getKey().substring(0,1).toUpperCase() + entry.getKey().substring(1);
            Integer total = entry.getValue().size();
            Map<Double, Integer> priceMap = new HashMap<>();
            sbuild.append(headerString(entryName, total));
            for(Item item : entry.getValue()){
                priceMap.merge(item.getPrice(), 1, Integer::sum);
            }

            for(Map.Entry<Double, Integer> priceEntry: priceMap.entrySet()){
                sbuild.append(priceString(priceEntry.getKey(),priceEntry.getValue()));
            }
            sbuild.append("\n");
        }

        sbuild.append(errorString());

        return sbuild.toString();
    }

    protected String headerString(String name, Integer total){
        StringBuilder sbuild = new StringBuilder();
        sbuild.append(String.format("name:%8s        seen:%2d times\n", name, total));
        sbuild.append("=============        =============\n");
        return sbuild.toString();
    }

    protected String priceString(Double price, Integer total){
        StringBuilder sbuild = new StringBuilder();
        sbuild.append(String.format("Price:%7.2f        seen:%2d times\n",price, total));
        sbuild.append("-------------		 -------------\n");
        return sbuild.toString();
    }

    protected String errorString(){
        return String.format("Errors         	 	 seen:%2d times",ErrorCounter.getInstance().getNumberOfErrors());
    }

}
