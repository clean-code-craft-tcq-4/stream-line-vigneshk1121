package rcv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rcv.models.Battery;

public class ReaderUtils {
    public static List<Float> getBatterySocList(List<Battery> batteries) {
        List<Float> batterySoc = new ArrayList<Float>();
        for (Battery battery : batteries) {
            batterySoc.add(battery.getStateOfCharge());
        }

        return batterySoc;
    }

    public static List<Float> getBatteryTempList(List<Battery> batteries) {
        List<Float> batteryTemperatures = new ArrayList<Float>();
        for (Battery battery : batteries) {
            batteryTemperatures.add(battery.getTemperature());
        }

        return batteryTemperatures;
    }

    public static List<Battery> getBatteryData() {
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
