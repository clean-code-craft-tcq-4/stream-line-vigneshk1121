using System;
using System.Diagnostics.CodeAnalysis;
using System.Text.Json;

namespace DataSender
{
    public static class Program
    {
        [ExcludeFromCodeCoverage]
        public static void Main(string[] args)
        {
            var class1 = new CSVDataProcessor("SensorReading.csv");
            var sensorReadings = class1.ProcessData();
            Console.WriteLine(JsonSerializer.Serialize(sensorReadings));
        }
    }
}
