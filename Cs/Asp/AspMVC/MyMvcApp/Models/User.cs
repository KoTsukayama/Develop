using System.ComponentModel.DataAnnotations;

namespace MyMvcApp.Models
{
    public class User
    {
        [Key] // 主キー
        public int Id { get; set; }

        [Required]
        [MaxLength(100)]
        public string Name { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }
    }
}
