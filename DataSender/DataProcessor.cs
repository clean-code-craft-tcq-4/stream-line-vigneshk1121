using System;
using System.Collections.Generic;

namespace DataSender
{
    public abstract class DataProcessor : IDataProcessor
    {
        public abstract void CreateInputSource();

        public abstract List<SensorParameter> ReadInputData();

        public virtual List<SensorParameter> GenerateInputData()
        {
            int numberOfSamples = 50;
            int maximumTemperature = 45;
            int minimumTemperature = 0;
            Random temperature = new Random();
            Random stateofCharge = new Random();
            List<SensorParameter> sensorParameters = new List<SensorParameter>();
            for (int i = 1; i <= numberOfSamples; i++)
            {
                sensorParameters.Add(new SensorParameter()
                {
                    Temperature = temperature.Next(minimumTemperature, maximumTemperature),
                    StateOfCharge = ((float)stateofCharge.NextDouble())
                });
            }

            return sensorParameters;
        }

        public abstract void WriteDataToFile();
        public abstract List<SensorParameter> ProcessData();
    }
}
