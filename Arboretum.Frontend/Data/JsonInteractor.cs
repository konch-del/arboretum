using Newtonsoft.Json;
using System.Diagnostics.CodeAnalysis;

namespace Arboretum.Frontend.Data
{
    public static class JsonInteractor
    {
        public static TObject DeserializeJson<TObject>(string dto) 
        {
            if (dto == null) { return default(TObject); }

            return JsonConvert.DeserializeObject<TObject>(dto);
        }

        public static string SerializeJson<TObject>([NotNull] this Stream stream, TObject dto)
        {
            if (dto == null) { return null; }
            
            return JsonConvert.SerializeObject(dto);
        }
    }
}
