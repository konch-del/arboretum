﻿using Arboretum.Frontend.Dtos;
using Newtonsoft.Json;

namespace Arboretum.Frontend.Data
{
    public class MockInteractor
    {
        private static readonly string WorkingDirectory = Path.Combine(Directory.GetCurrentDirectory(), "Mocks");

        public static UserPlantsDto GetUserPlant()
        {
            using (StreamReader reader = new StreamReader($"{WorkingDirectory}/myPlantsMock.json"))
            {
                string json = reader.ReadToEnd();
                return JsonConvert.DeserializeObject<UserPlantsDto>(json);
            }
        }

        public static string Login()
        {
            Console.WriteLine("Login into account");
            return "1";
        }

        public static string Register()
        {
            Console.WriteLine("Registered account");
            return "1";
        }

        public static void LoadPictures()
        {
            Console.WriteLine("Loaded pictures succesfully");
        }

        public static string GetPicture (string id)
        {
            return $"{WorkingDirectory}\\Photos\\{id}.jpg";
        }
    }
}
