using System.Text.Json;
using System;

namespace DataSender
{
    public static class Util
    {
        public static string ConvertListToJSON(dynamic input)
        {
			Console.WriteLine("printing battery status >>>");
            return JsonSerializer.Serialize(input);
        }
    }
}
