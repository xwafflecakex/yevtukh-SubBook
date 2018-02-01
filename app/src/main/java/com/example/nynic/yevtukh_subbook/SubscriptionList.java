package com.example.nynic.yevtukh_subbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by nynic on 2018-01-31.
 */

public class SubscriptionList {
    private LinkedHashMap<String, Float> subList = new LinkedHashMap<>();


    public LinkedHashMap<String, Float> getSubList(){
        return subList;
    }

    public void setSubList(LinkedHashMap<String, Float> subList) {
        this.subList = subList;
    }
}
