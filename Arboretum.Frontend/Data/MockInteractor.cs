using Arboretum.Frontend.Dtos;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arboretum.Frontend.Data
{
    public class MockInteractor
    {
        public static UserPlantsDto GetUserPlant()
        {
            using (StreamReader reader = new StreamReader("D:\\Programming\\Projects\\GardenBins\\Arboretum.Frontend\\Mocks\\myPlantsMock.json"))
            {
                string json = reader.ReadToEnd();
                return JsonConvert.DeserializeObject<UserPlantsDto>(json);
            }
        }
    }
}
