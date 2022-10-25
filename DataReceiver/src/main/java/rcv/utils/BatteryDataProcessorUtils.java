package rcv.utils;

import java.util.List;

public class BatteryDataProcessorUtils {
    public static Float getSMA(List<Float> values, int numberOfPeriod) {
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
}
