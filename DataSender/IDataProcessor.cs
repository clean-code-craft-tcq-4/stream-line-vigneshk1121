using System.Collections.Generic;

namespace DataSender
{
    interface IDataProcessor
    {
        List<SensorParameter> ReadInputData();

        void CreateInputSource();

        void WriteDataToFile();

        List<SensorParameter> ProcessData();
    }
}
