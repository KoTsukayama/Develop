using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using MyMvcApp.Models;
using System.Linq;


namespace MyMvcApp.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    public IActionResult Privacy()
    {
        return View();
    }

    public IActionResult Test()
    {
        return View();
    }

    public IActionResult Plotly()
    {
        return View();
    }

    [HttpGet]
    public JsonResult GetProgrammingTrends()
    {
        var languages = new List<string>
    {
        "Python", "Java", "C", "C++", "JavaScript", "PHP", "C#", "Swift", "Ruby", "Go", "Kotlin", "TypeScript"
    };

        var random = new Random();
        var data = new List<object>();

        // 各言語の最初の年のランキングを設定
        var initialRankings = new Dictionary<string, int>
    {
        { "Python", 5 },
        { "Java", 1 },
        { "C", 2 },
        { "C++", 3 },
        { "JavaScript", 6 },
        { "PHP", 7 },
        { "C#", 8 },
        { "Swift", 9 },
        { "Ruby", 10 },
        { "Go", 11 },
        { "Kotlin", 12 },
        { "TypeScript", 13 }
    };

        // 上昇・下降する傾向の言語を設定
        var increasingLanguages = new List<string> { "Python", "JavaScript", "Go", "TypeScript" };
        var decreasingLanguages = new List<string> { "Java", "C", "C++", "PHP", "Ruby" };

        // 2000年から2023年まで
        for (int year = 2000; year <= 2023; year++)
        {
            foreach (var language in languages)
            {
                // 最初の年のランキングは固定
                int ranking = year == 2000 ? initialRankings[language] : 0;

                // 2001年以降は前年のランキングを元に少し変動
                if (year > 2000)
                {
                    ranking = data
                        .Where(d => (d as dynamic).Year == year - 1 && (d as dynamic).Language == language)
                        .Select(d => (d as dynamic).Ranking)
                        .FirstOrDefault();

                    // 上昇する言語はランキングが上がる
                    if (increasingLanguages.Contains(language))
                    {
                        ranking = Math.Max(ranking - 1, 1); // 最低ランク1
                    }
                    // 下降する言語はランキングが下がる
                    else if (decreasingLanguages.Contains(language))
                    {
                        ranking = Math.Min(ranking + 1, 10); // 最大ランク10
                    }
                    else
                    {
                        // その他はランダムに変動
                        int change = random.Next(-1, 2); // -1, 0, +1 のいずれか
                        ranking = Math.Clamp(ranking + change, 1, 10); // ランキングは1から10の範囲に制限
                    }
                }

                data.Add(new { Year = year, Language = language, Ranking = ranking });
            }
        }

        // 言語ごとにデータをグループ化
        var groupedData = data
            .GroupBy(d => (d as dynamic).Language)
            .Select(g => new
            {
                Language = g.Key,
                Data = g.Select(d => new { Year = (d as dynamic).Year, Ranking = (d as dynamic).Ranking }).ToList()
            })
            .ToList();

        return Json(groupedData);
    }


    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
