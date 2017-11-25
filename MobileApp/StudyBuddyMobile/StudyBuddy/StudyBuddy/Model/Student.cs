using System;
namespace StudyBuddy.Models
{
	public class Student
	{

		public int studentId { get; set; }
		public String firstName { get; set; }
		public String lastName { get; set; }
		public String email { get; set; }
		public String password { get; set; }
		public String studyLevel { get; set; }
		public String fileImage { get; set; }

		public int avail { get; set; }


		public Student()
		{

		}

	}
}
