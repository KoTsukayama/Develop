using Microsoft.EntityFrameworkCore;
using MyMvcApp.Data;

var builder = WebApplication.CreateBuilder(args);

// appsettings.json から設定を読み込む
builder.Configuration.AddJsonFile("appsettings.json", optional: false, reloadOnChange: true);

// 🔹 PostgreSQL を使用するための `ApplicationDbContext` 設定
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseNpgsql(connectionString)); // ← PostgreSQL を使用する場合は `UseNpgsql`

// 🔹 コントローラの追加
builder.Services.AddControllersWithViews();

var app = builder.Build();

// ミドルウェアの設定
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

// 🔹 ルーティングの設定
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Users}/{action=Index}/{id?}");

app.Run();
