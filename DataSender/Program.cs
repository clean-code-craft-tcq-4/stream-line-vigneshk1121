using System;
using System.Diagnostics.CodeAnalysis;

namespace DataSender
{
    public static class Program
    {
        [ExcludeFromCodeCoverage]
        public static void Main(string[] args)
        {
            var class1 = new CSVDataProcessor("SensorReading.csv");
            var sensorReadings = class1.ProcessData();
            Console.WriteLine(Util.ConvertListToJSON(sensorReadings));
        }
    }
}
