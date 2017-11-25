using System;
using System.Collections.Generic;

using StudyBuddy.Models;


namespace StudyBuddy
{
	public class DataUtil
	{
		public static int stuId;


		public static List<Student> getStudbygrpid(int grpid)
		{
			List<Student> stud = new List<Student>();
			List<StudentGroup> studgroup = getDetails();

			for (int i = 0; i < studgroup.Count; i++)
			{
				StudentGroup studGrp = studgroup[i];
				if (grpid == studGrp.GroupId)
				{
					stud.Add(getStudentById(studGrp.Id));
				}
			}

			return stud;
		}

		public static Student getStudentById(int StudId)
		{
			Student stud = null;
			List<Student> studs = DataStudentUtil.getStudentAllDetails();

			return stud;

		}

		public static List<Group> getGroupsByLoggedInStudent()
		{
			List<Group> grpListById = new List<Group>();
			List<StudentGroup> stuGrpList = getDetails();


			return grpListById;
		}

		public static Group getGroupById(int grpId)
		{
			
			Group grp = null;
			List<Group> grps = getGroupAllDetails();

			return grp;

		}

		public static List<Group> getGroupAllDetails()
		{
			List<Group> groupDetailsList = new List<Group>();

			return groupDetailsList;

		}




		public static List<StudentGroup> getDetails()
		{
			List<StudentGroup> studentGroupList = new List<StudentGroup>();
			StudentGroup studgrp = new StudentGroup();


			return studentGroupList;
		}

	}
}

