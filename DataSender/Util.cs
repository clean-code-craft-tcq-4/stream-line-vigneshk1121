using System.Text.Json;
using System;

namespace DataSender
{
    public static class Util
    {
        public static string ConvertListToJSON(dynamic input)
        
			string data= JsonSerializer.Serialize(input);
			Console.WriteLine("printing battery status >>>" data);
            return JsonSerializer.Serialize(input);
        }
    }
}
