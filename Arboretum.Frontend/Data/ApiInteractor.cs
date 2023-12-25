using Arboretum.Frontend.Dtos;
using Microsoft.AspNetCore.Components.Forms;
using Newtonsoft.Json;
using Radzen;
using System.Net.Http.Headers;
using System.Net.Mime;
using System.Text;

namespace Arboretum.Frontend.Data
{
    public static class ApiInteractor
    {
        private static HttpClient _httpClient = new HttpClient();

        public static UserPlantsDto GetUserPlant(string userId)
        {
            return SendGetRequest<UserPlantsDto>(String.Format(ApiMethods.GetUserPlants, Constants.ApiPath, userId)).Result;
        }

        public static string Login(string email, string pswd)
        {
            return SendPostRequest<string>(String.Format(ApiMethods.Login, Constants.ApiPath, email, pswd)).Result;
        }

        public static PlantDto GetPlantById(string id)
        {
            return SendGetRequest<PlantDto>(String.Format(ApiMethods.GetPlantInfo, Constants.ApiPath, id)).Result;
        }

        public static string RegisterUser(string email, string pswd, string phone)
        {
            var body = new
            {
                User = new
                {
                    Email = email,
                    Phone = phone,
                    Password = pswd
                }
            };

            return SendPostRequest<string>(
                String.Format(ApiMethods.Register, Constants.ApiPath), body).Result;
        }

        public static async void LoadPicture(IBrowserFile picture, string plantId)
        {
            using (var multipartFormContent = new MultipartFormDataContent())
            {
                var fileStreamContent = new StreamContent(picture.OpenReadStream());
                fileStreamContent.Headers.ContentType = new MediaTypeHeaderValue("image/png");
                multipartFormContent.Add(fileStreamContent, name: "file", fileName: picture.Name);
                await SendPostRequestWithFile(String.Format(
                    ApiMethods.LoadPicture, Constants.ApiPath, plantId), multipartFormContent);
            }
        }

        private static async Task<TResult> SendGetRequest <TResult>(string query)
        {
            var request = new HttpRequestMessage(HttpMethod.Get, query);
            var response = await ExecuteRequest(request);
            var content = response.Content.ReadAsStringAsync().Result;
            return JsonInteractor.DeserializeJson<TResult>(content);
        }

        private static async Task SendPostRequestWithFile(string query, MultipartFormDataContent fileContent)
        {
            var response = await _httpClient.PostAsync(query, fileContent);
            response.EnsureSuccessStatusCode();
        }

        private static async Task<TResult> SendPostRequest<TResult>(string query)
        {
            var request = new HttpRequestMessage(HttpMethod.Post, query);
            var response = await ExecuteRequest(request);
            var content = response.Content.ReadAsStringAsync().Result;
            return JsonInteractor.DeserializeJson<TResult>(content);
        }
        private static async Task<TResult> SendPostRequest<TResult>(string query, object body)
        {
            var request = new HttpRequestMessage(HttpMethod.Post, query);
            var json = JsonConvert.SerializeObject(
                body, Formatting.None, new JsonSerializerSettings{NullValueHandling = NullValueHandling.Ignore});
            request.Content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await ExecuteRequest(request);
            var content = response.Content.ReadAsStringAsync().Result;
            return JsonInteractor.DeserializeJson<TResult>(content);
        }

        private static async Task<HttpResponseMessage> ExecuteRequest(HttpRequestMessage requestMessage)
        {
            HttpResponseMessage response = new HttpResponseMessage();
            try
            {
                response = await _httpClient.SendAsync(requestMessage).ConfigureAwait(false);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            response.EnsureSuccessStatusCode();
            Console.WriteLine(response.StatusCode.ToString());
            return response;
        }
    }
}
