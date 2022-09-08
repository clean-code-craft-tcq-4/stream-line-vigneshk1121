using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.IO;
using System.Reflection;

namespace DataSender
{
    public class CSVDataProcessor : DataProcessor, ICSVDataProcessor
    {
        private string _fileDirectory { get; set; }

        private string _fileName { get; set; }

        public CSVDataProcessor(string fileName)
        {
            _fileDirectory = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            _fileName = fileName;
        }

        [ExcludeFromCodeCoverage]
        public override List<SensorParameter> ProcessData()
        {
            CreateInputSource();
            WriteDataToFile();
            return ReadInputData();
        }

        public override void CreateInputSource()
        {
            var newFile = File.Create(Path.Combine(_fileDirectory, _fileName));
            newFile.Close();
        }

        public override void WriteDataToFile()
        {
            var sensorParameters = base.GenerateInputData();
            using StreamWriter writer = File.AppendText(Path.Combine(_fileDirectory, _fileName));
            foreach (var item in sensorParameters)
            {
                writer.Write(item.Temperature + "," + item.StateOfCharge + "\n");
            }
        }

        public override List<SensorParameter> ReadInputData()
        {
            List<SensorParameter> sensorReadings = new List<SensorParameter>();

            string fileDirectory = Path.Combine(_fileDirectory, _fileName);

            using StreamReader streamReader = new StreamReader(fileDirectory);
            while (!streamReader.EndOfStream)
            {
                string[] parameterColumn = streamReader.ReadLine().Split(',');
                sensorReadings.Add(new SensorParameter { Temperature = float.Parse(parameterColumn[0]), StateOfCharge = float.Parse(parameterColumn[1]) });
            }

            return sensorReadings;
        }
    }
}
