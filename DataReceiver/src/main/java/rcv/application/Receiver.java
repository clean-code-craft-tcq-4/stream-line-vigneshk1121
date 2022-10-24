package rcv.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rcv.models.Battery;

public class Receiver {

    public static void main(String[] args) {

        ArrayList<Battery> batteries = getBatteryData();

        List<Float> batteryTemperatures = getBatteryTempList(batteries);
        List<Float> batterySoc = getBatterySocList(batteries);

        System.out.println("Maximum temperature is : " + Collections.max(batteryTemperatures));
        System.out.println("Mininmum temperature is : " + Collections.min(batteryTemperatures));

        System.out.println("Maximum State of charge is : " + Collections.max(batterySoc));
        System.out.println("Mininmum State of charge is : " + Collections.min(batterySoc));

        System.out.println("Simple Moving average of temperature is : " + getSMA(batteryTemperatures, 5));
        System.out.println("Simple Moving average of temperature is : " + getSMA(batterySoc, 5));

    }

    private static Float getSMA(List<Float> values, int numberOfPeriod) {
        List<Float> subList;
        if (values.size() <= numberOfPeriod) {
            subList = values;
        } else {
            subList = values.subList(values.size() - numberOfPeriod, values.size());
        }

        Float sum = 0f;
        for (Float value : subList) {
            sum = sum + value;
        }

        return sum / numberOfPeriod;

    }

    private static List<Float> getBatterySocList(ArrayList<Battery> batteries) {
        List<Float> batterySoc = new ArrayList<Float>();
        for (Battery battery : batteries) {
            batterySoc.add(battery.getStateOfCharge());
        }

        return batterySoc;
    }

    private static List<Float> getBatteryTempList(ArrayList<Battery> batteries) {
        List<Float> batteryTemperatures = new ArrayList<Float>();
        for (Battery battery : batteries) {
            batteryTemperatures.add(battery.getTemperature());
        }

        return batteryTemperatures;
    }

    private static ArrayList<Battery> getBatteryData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String jsonData = "";
        try {
            jsonData = reader.readLine();
        } catch (IOException e) {
            System.err.println("Failed to receive input from sender");
        }

        if (jsonData.length() == 0) {
            System.err.println("No data found");
        }
        Gson gson = new Gson();

        Type batteryTypeList = new TypeToken<ArrayList<Battery>>() {
        }.getType();

        return gson.fromJson(jsonData, batteryTypeList);

    }

}
