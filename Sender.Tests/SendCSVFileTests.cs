using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using Xunit;

namespace DataSender.Tests
{
    public class SendCSVFileTests
    {

        private readonly string _fileName = "SensorReading.csv";

        [Fact(DisplayName = "Test to check if the CSV file is generated")]
        public void CreateNewCSVFileTest()
        {
            string workingDirectory = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            new CSVDataProcessor(_fileName).CreateInputSource();
            Assert.True(File.Exists(workingDirectory + "/SensorReading.csv"));
        }

        [Fact(DisplayName = "Test to check if the CSV file is having some values in it")]
        public void WriteContentToCSVFileTest()
        {
            WriteDataToAFile();
            Assert.True(new FileInfo("SensorReading.csv").Length > 0);
        }

        [Fact(DisplayName = "Test to check if 50 records are available in file")]
        public void TestFileRead()
        {
            var processDataClass = InstantiateObject();
            WriteDataToAFile();
            var value = processDataClass.ReadInputData();
            Assert.Equal(50, value.Count);
        }

        [Fact(DisplayName = "Test to check if records are created")]
        public void TestInputRecordInList()
        {
            var value = new CSVDataProcessor(_fileName).GenerateInputData();
            Assert.Equal(50, value.Count);
        }

        [Fact(DisplayName = " Test If input list is converted to JSON String")]
        public void TestConvertToJSONString()
        {
            List<SensorParameter> sensorParameters = new List<SensorParameter>();
            sensorParameters.Add(new SensorParameter() { Temperature = 40, StateOfCharge = 0.9f });
            sensorParameters.Add(new SensorParameter() { Temperature = 22, StateOfCharge = 0.2f });

            string jsonString = Util.ConvertListToJSON(sensorParameters);

            string expectedString = "[{\"Temperature\":40,\"StateOfCharge\":0.9},{\"Temperature\":22,\"StateOfCharge\":0.2}]";

            Assert.Equal(jsonString, expectedString);
        }

        private void WriteDataToAFile()
        {
            InstantiateObject().CreateInputSource();
            InstantiateObject().WriteDataToFile();
        }

        private CSVDataProcessor InstantiateObject()
        {
            return new CSVDataProcessor(_fileName);
        }
    }
}
