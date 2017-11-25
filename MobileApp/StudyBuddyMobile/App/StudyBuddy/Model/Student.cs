using System;
using System.Collections.Generic;

using AppServiceHelpers.Models;


namespace StudyBuddy.Models
{

	public class Student:EntityData
	{



		public string userId { get; set; }

		public String firstName { get; set; }

		public String lastName { get; set; }

		public String password { get; set; }

		public int studyLevel { get; set; }

		public String image { get; set; }




	}
}
