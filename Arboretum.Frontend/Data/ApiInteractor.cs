using Arboretum.Frontend.Dtos;
using Radzen;
using System.Net.Mime;
using System.Text;

namespace Arboretum.Frontend.Data
{
    public static class ApiInteractor
    {
        private static HttpClient _httpClient = new HttpClient();

        public static UserPlantsDto GetUserPlant()
        {
            return SendGetRequest<UserPlantsDto>(String.Format(ApiMethods.GetUserPlants)).Result;
        }

        public static UserPlantsDto Login(string email, string pswd)
        {
            return SendPostRequest<UserPlantsDto>(String.Format(ApiMethods.Login, Constants.ApiPath, email, pswd)).Result;
        }

        private static async Task<TResult> SendGetRequest<TResult>(string query)
        {
            var request = new HttpRequestMessage(HttpMethod.Get, query);
            var response = await ExecuteRequest(request);
            var content = response.Content.ReadAsStringAsync().Result;
            return JsonInteractor.DeserializeJson<TResult>(content);
        }

        private static async Task<TResult> SendGetRequestWithBody<TResult>(string query, string body)
        {
            var request = new HttpRequestMessage
            {
                Method = HttpMethod.Get,
                RequestUri = new Uri(query),
                Content = new StringContent(body, Encoding.UTF8, MediaTypeNames.Application.Json)
            };

            var response = await ExecuteRequest(request);
            var content = response.Content.ReadAsStringAsync().Result;
            return JsonInteractor.DeserializeJson<TResult>(content);
        }
        private static async Task<TResult> SendPostRequest<TResult>(string query)
        {
            var request = new HttpRequestMessage(HttpMethod.Post, query);
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
