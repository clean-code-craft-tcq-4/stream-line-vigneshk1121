package rcv.application;

import java.util.Collections;
import java.util.List;

import rcv.models.Battery;
import rcv.utils.BatteryDataProcessorUtils;
import rcv.utils.ReaderUtils;

public class Receiver {

    public static void main(String[] args) {

        List<Battery> batteries = ReaderUtils.getBatteryData();

        List<Float> batteryTemperatures = ReaderUtils.getBatteryTempList(batteries);
        List<Float> batterySoc = ReaderUtils.getBatterySocList(batteries);

        System.out.println("Maximum temperature is : " + Collections.max(batteryTemperatures));
        System.out.println("Mininmum temperature is : " + Collections.min(batteryTemperatures));

        System.out.println("Maximum State of charge is : " + Collections.max(batterySoc));
        System.out.println("Mininmum State of charge is : " + Collections.min(batterySoc));

        System.out.println("Simple Moving average of temperature is : "
                + BatteryDataProcessorUtils.getSMA(batteryTemperatures, 5));
        System.out.println(
                "Simple Moving average of temperature is : " + BatteryDataProcessorUtils.getSMA(batterySoc, 5));

    }

}
