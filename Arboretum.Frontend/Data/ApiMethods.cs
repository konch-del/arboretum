using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arboretum.Frontend.Data
{
    public static class ApiMethods
    {
        public static string GetPlantInfo = "{0}/plantInfo?planId={1}";
        public static string GetUserPlants = "{0}/myplant?userId={1}";
        public static string Login = "{0}/login?email={1}&password={2}";
        public static string Register = "{0}/saveUser";
        public static string LoadPicture = "{0}/loadPicture?plantId={1}";
        public static string GetPicture = "{0}/getPicture?plantId={1}";
    }
}
