using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arboretum.Frontend.Data
{
    public class User
    {
        private static User _user;
        private static string _email;
        private static string _id;

        private static object syncRoot = new object();

        private User()
        {

        }

        public static User GetUserInstance()
        {
            if (_user == null) 
            {
                lock (syncRoot) 
                {
                    _user = new User();
                }
            }

            return _user;
        }

        public static void SetUserInfo(string email, string id)
        {
            GetUserInstance();
            _email = email;
            _id = id;
        }

        public static string GetUserId() 
        {
            return _id;
        }
    }
}
