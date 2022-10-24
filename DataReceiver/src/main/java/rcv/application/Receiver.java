package rcv.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rcv.models.Battery;

public class Receiver {

    public static void main(String[] args) {

        // Enter data using BufferReader
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

        ArrayList<Battery> batteries = gson.fromJson(jsonData, batteryTypeList);

        for (Battery battery : batteries) {
            System.out.println(battery);
        }

    }

}
