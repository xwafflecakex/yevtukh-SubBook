package com.example.nynic.yevtukh_subbook;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by nynic on 2018-01-31.
 */

public class SubscriptionList extends Application{


    private static ArrayList<Subscription> subList = new ArrayList<>();


    public static void addToList(Subscription subscription){
        subList.add(subscription);
    }

    public static ArrayList<Subscription> getSubList() {
        return subList;
    }

    public void setSubList(ArrayList<Subscription> subList) {
        this.subList = subList;
    }
}
