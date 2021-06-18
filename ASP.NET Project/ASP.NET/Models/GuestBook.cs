
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspCoreMVCEF.Models
{
	public class Books
	{
		[Key]
		public string Title { get; set; }
		public string User { get; set; }
		public string Text { get; set; }
		public string Author { get; set; }
		public DateTime Date { get; set; }
	}
}