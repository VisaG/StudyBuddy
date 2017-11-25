using System;
using System.Collections.Generic;
using StudyBuddy.Models;

namespace StudyBuddy
{
	public class DataCourseUtil
	{
		public static int Id;

		public static List<Courses> getStudentCourses()
		{
			List<Courses> studentCourseList = new List<Courses>();

			Courses course1 = new Courses(); 			course1.Id = 1; 			course1.courseId = 10;

			Courses course2 = new Courses(); 			course2.Id = 1; 			course2.courseId = 12;

			Courses course3 = new Courses(); 			course3.Id = 1; 			course3.courseId = 13;

			Courses course4 = new Courses(); 			course4.Id = 2; 			course4.courseId = 10;

			Courses course5 = new Courses(); 			course5.Id = 2; 			course5.courseId = 13;

			Courses course6 = new Courses(); 			course6.Id = 3; 			course6.courseId = 14;

			Courses course7 = new Courses(); 			course7.Id = 3; 			course7.courseId = 10;

			Courses course8 = new Courses(); 			course8.Id = 3; 			course8.courseId = 12;



			studentCourseList.Add(course1);
			studentCourseList.Add(course2);
			studentCourseList.Add(course3);
			studentCourseList.Add(course4);
			studentCourseList.Add(course5);
			studentCourseList.Add(course6);
			studentCourseList.Add(course7);
			studentCourseList.Add(course8);



			return studentCourseList;
		}


		//Get student by CourseID by Id
		public static List<CourseId> getCourseByCourseId()
		{


			List<Courses> stuCourse = getStudentCourses();

			List<CourseId> courseById = DataCourseIdUtil.getCourseDescription();

			List<CourseId> courseTakenByStu = new List<CourseId>();

			for (int i = 0; i < stuCourse.Count; i++)
			{

				if (stuCourse[i].Id == Id)
				{
					for (int j = 0; j < courseById.Count; j++)
					{
						if (stuCourse[i].courseId == courseById[j].courseId )
						{
							courseTakenByStu.Add(courseById[j]);
						}
					}

				}

			}
			return courseTakenByStu;
		}




	}
}
