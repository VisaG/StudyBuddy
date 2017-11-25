using System;
using System.Collections.Generic;
using System.Diagnostics.Contracts;

using StudyBuddy.Models;

namespace StudyBuddy
{
	public class DataStudentUtil

	{

		public static int loginid;
		public static string email;



		public static List<Student> getStudentAllDetails()

		{
			List<Student> studentDetailsList = new List<Student>();

			Student stud1 = new Student();
			stud1.studentId = 1;
			stud1.firstName = "Ramya";
			stud1.lastName = "Sivaraman";
			stud1.studyLevel = "Management";
			stud1.email = "ram@scu.edu";
			stud1.password = "ram";
			stud1.fileImage = "ram.png";
			stud1.avail = 5;



			Student stud2 = new Student();
			stud2.studentId = 2;
			stud2.firstName = "Visa";
			stud2.lastName = "Gopal";
			stud2.studyLevel = "Computer Engineering";
			stud2.email = "visa@scu.edu";
			stud2.password = "visa";
			stud2.fileImage = "visa.png";
			stud2.avail = 5;
		

			Student stud3 = new Student();
			stud3.studentId = 3;
			stud3.firstName = "Milad";
			stud3.lastName = "Roigari";
			stud3.studyLevel = "Computer Engineering";
			stud3.email = "milad@scu.edu";
			stud3.password = "milad";
			stud3.fileImage = "milad.png";

	


			studentDetailsList.Add(stud1);
			studentDetailsList.Add(stud2);
			studentDetailsList.Add(stud3);

			return studentDetailsList;
		}

		//Get student by email id
		public static Student getStudentByEmailId()
		{


			Student student = new Student();

			List<Student> stuByEmail = new List<Student>();

			stuByEmail = getStudentAllDetails();

			for (int i = 0; i < stuByEmail.Count; i++)
			{
				Student s = stuByEmail[i];

				if (string.Compare(s.email, email, StringComparison.CurrentCulture) == 0)
				{
					student = s;
					break;
				}

			}
			return student;
		}
	}
}
