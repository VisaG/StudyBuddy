using System;
using System.Collections.Generic;
using StudyBuddy.Models;


namespace StudyBuddy
{
	public class DataCourseIdUtil
	{
		public static List<CourseId> getCourseDescription()
		{
			List<CourseId> courseList = new List<CourseId>();

			CourseId course1 = new CourseId();
			course1.courseId = 10;
			course1.courseDesc = "COEN 252-Computer Forensics";

			CourseId course2 = new CourseId();
			course2.courseId = 12;
			course2.courseDesc = "COEN 285-Software Engineering";

			CourseId course3 = new CourseId();
			course3.courseId = 13;
			course3.courseDesc = "COEN 285-Software Engineering";

		



			courseList.Add(course1);
			courseList.Add(course2);
			courseList.Add(course3);


			return courseList;
		}
	}
}
