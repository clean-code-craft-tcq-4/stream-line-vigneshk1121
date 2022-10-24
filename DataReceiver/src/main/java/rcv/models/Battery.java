package rcv.models;

import com.google.gson.annotations.SerializedName;

public class Battery {

    @SerializedName(value = "Temperature")
    private float temperature;

    @SerializedName(value = "StateOfCharge")
    private float StateOfCharge;

    public Battery(float temperature, float stateOfCharge) {
        super();
        this.temperature = temperature;
        StateOfCharge = stateOfCharge;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getStateOfCharge() {
        return StateOfCharge;
    }

    public void setStateOfCharge(float stateOfCharge) {
        StateOfCharge = stateOfCharge;
    }

    @Override
    public String toString() {
        return "Battery [temperature=" + temperature + ", StateOfCharge=" + StateOfCharge + "]";
    }

}
