using System;
using System.Collections.Generic;
using StudyBuddy.Models;

namespace StudyBuddy
{
	public class DataInterestUtil
	{

		public static int Id;

		public static List<Interest> getStudentInterest()
		{
			List<Interest> studentInterestList = new List<Interest>();

			Interest interst1 = new Interest();
			interst1.Id = 1;
			interst1.interest = "Badminton";

			Interest interst2 = new Interest();
			interst2.Id = 1;
			interst2.interest = "Singing";

			Interest interst3 = new Interest(); 			interst3.Id = 2; 			interst3.interest = "Dance";


			Interest interst4 = new Interest(); 			interst4.Id = 2; 			interst4.interest = "Reading";


			Interest interst5 = new Interest(); 			interst5.Id = 3; 			interst5.interest = "Reading";


			Interest interst6 = new Interest(); 			interst6.Id = 3; 			interst6.interest = "Singing";

			Interest interst7 = new Interest(); 			interst7.Id = 3; 			interst7.interest = "Soccer";



			studentInterestList.Add(interst1);
			studentInterestList.Add(interst2);
			studentInterestList.Add(interst3);
			studentInterestList.Add(interst4);
			studentInterestList.Add(interst5);
			studentInterestList.Add(interst6);
			studentInterestList.Add(interst7);


			return studentInterestList;
		}


		//Get student by Interest bu Id
		public static List<Interest> getStudentInterestById()
		{


			List<Interest> stuInterest = new List<Interest>();

			stuInterest = getStudentInterest();

			List<Interest> stuInterestById = new List<Interest>();


			for (int i = 0; i < stuInterest.Count; i++)
			{
				

				if (stuInterest[i].Id == Id)
				{
					stuInterestById.Add(stuInterest[i]);

				}

			}
			return stuInterestById;
		}
	



	}
}
