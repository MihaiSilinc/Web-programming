using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AspCoreMVCEF.Data;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using AspCoreMVCEF.Models;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;


namespace lab10.Controllers
{
    public class MainController : Controller
    {
        private readonly DBContext _context;

        public MainController(DBContext context)
        {
            _context = context;
        }
        public IActionResult Index()
        {
            return View("Login");
        }

        public IActionResult Login(string username, string password)
        {
            List<User> users = _context.Users.Where(user =>
                user.Username.Equals(username) &&
                user.Password.Equals(password)).ToList();
            if (users.Count().Equals(0))
            {
                return View("FailLogin");
            }

            HttpContext.Session.SetString("user", JsonSerializer.Serialize(users[0]));
            return Redirect("/Main/Books");
        }

        public IActionResult Books()
        {
            string userStr = HttpContext.Session.GetString("user");
            if (userStr == null)
                return Redirect("/Main");
            return View("Books");
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Remove("user");
            return Redirect("/Main");
        }

        public IActionResult ManageBooks()
        {
            string userStr = HttpContext.Session.GetString("user");
            if (userStr == null)
                return Redirect("/Main");
            return View("ManageBooks");
        }

        public IActionResult ViewBooks()
        {
            return View("ViewBooks");
        }
        public IActionResult ChangePassword()
        {
            return View("ChangePassword");
        }

        public string UpdatePassword(string newPassword, string confirmPassword, string oldPassword)
        {
            User user = JsonSerializer.Deserialize<User>(HttpContext.Session.GetString("user"));

            if (newPassword == null)
                return "New passsword must not be empty";
            if (confirmPassword == null)
                return "Confirm password must not be empty";
            if (oldPassword == null)
                return "Old Password must not be empty";

            if (newPassword.Equals(confirmPassword))
            {
                if (oldPassword.Equals(user.Password))
                {
                    try
                    {
                        User updatedUser = new User() { Username = user.Username, Password = newPassword };
                        _context.Users.Update(updatedUser);
                        _context.SaveChanges();
                        HttpContext.Session.SetString("user", JsonSerializer.Serialize(updatedUser));
                        return "Password updated";
                    }
                    catch (Exception exc)
                    {
                        return "Password was not updated";
                    }
                }
                else
                {
                    return "Old password is not correct";
                }

            }
            else
            {
                return "Password and confirm password must be equal";
            }
        }

        public List<Books> GetBooks(string author, DateTime startDate, DateTime endDate)
        {
            DateTime defaultDate = new DateTime();
            List<Books> books = _context.Books.ToList();
            if (author != null)
                books = books.Where(books => books.Author.Equals(author)).ToList();

            if (!endDate.Equals(defaultDate) && !startDate.Equals(defaultDate))
                books = books.Where(books =>
                books.Date.CompareTo(startDate) > 0
                && books.Date.CompareTo(endDate) < 0).ToList();

            return books.OrderByDescending(books => books.Date).ToList();
        }

        public List<string> GetTitlesOfUser()
        {
            string userStr = HttpContext.Session.GetString("user");
            if (userStr == null)
                return new List<String>();
            User user = JsonSerializer.Deserialize<User>(userStr);

            return _context.Books
                .Where(books => books.User.Equals(user.Username))
                .Select(books => books.Title)
                .ToList();
        }

        public Books GetBooksWithTitle(string title)
        {
            string userStr = HttpContext.Session.GetString("user");
            if (userStr == null)
                return new Books();
            User user = JsonSerializer.Deserialize<User>(userStr);

            return _context.Books
                .Where(books => books.User.Equals(user.Username) && books.Title.Equals(title))
                .First();
        }

        public IActionResult CreateAccount()
        {
            return View("CreateAccount");
        }

        public string SaveAccount(string username, string password, string confirmPassword)
        {
            if (username == null)
                return "Username must not be empty";
            if (password == null)
                return "Password must not be empty";
            if (confirmPassword == null)
                return "Confirm Password must not be empty";

            if (password.Equals(confirmPassword))
            {
                try
                {
                    _context.Users.Add(new User() { Username = username, Password = password });
                    _context.SaveChanges();
                    return "Account created";
                }
                catch (Exception exc)
                {
                    return "Username is taken";
                }
            }
            else
            {
                return "Password and confirm password must be equal";
            }
        }

        public string AddBooks(string title, string author, DateTime date, string text)
        {
            User user = JsonSerializer.Deserialize<User>(HttpContext.Session.GetString("user"));
            Books books = new Books()
            {
                Title = title,
                User = user.Username,
                Author = author,
                Date = date,
                Text = text
            };

            try
            {
                _context.Books.Add(books);
                _context.SaveChanges();
                return "Added books";
            }
            catch (Exception exc)
            {
                return "Bad title";
            }
        }

        public string DeleteBooks(string title)
        {
            User user = JsonSerializer.Deserialize<User>(HttpContext.Session.GetString("user"));
            Books books = new Books()
            {
                Title = title,
                User = user.Username,
            };
            try
            {
                _context.Remove(
                   _context.Books.Single(books => books.Title.Equals(title) && books.User.Equals(user.Username))
                );
                _context.SaveChanges();
                return "Deleted books";
            }
            catch (Exception exc)
            {
                return "Bad title";
            }
        }

        public string UpdateBooks(string title, string author, DateTime date, string text)
        {
            User user = JsonSerializer.Deserialize<User>(HttpContext.Session.GetString("user"));
            Books books = new Books()
            {
                Title = title,
                User = user.Username,
                Author = author,
                Date = date,
                Text = text
            };

            try
            {
                _context.Books.Update(books);
                _context.SaveChanges();
                return "Updated books";
            }
            catch (Exception exc)
            {
                return "Bad title";
            }
        }
    }
}