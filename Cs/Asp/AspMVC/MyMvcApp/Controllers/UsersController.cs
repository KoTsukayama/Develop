using Microsoft.AspNetCore.Mvc;
using MyMvcApp.Data; // ← ここを確認
using MyMvcApp.Models;
using System.Linq;

namespace MyMvcApp.Controllers
{
    public class UsersController : Controller
    {
        private readonly ApplicationDbContext _context;

        public UsersController(ApplicationDbContext context)
        {
            _context = context;
        }

        public IActionResult Index()
        {
            // var users = _context.Users.ToList();
            // return View(users);
            return View();
        }
    }
}
