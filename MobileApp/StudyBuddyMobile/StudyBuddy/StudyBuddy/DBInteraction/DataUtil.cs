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
					stud.Add(getStudentById(studGrp.StudentId));
				}
			}

			return stud;
		}

		public static Student getStudentById(int StudId)
		{
			Student stud = null;
			List<Student> studs = DataStudentUtil.getStudentAllDetails();
			for (int i = 0; i < studs.Count; i++)
			{
				stud = studs[i];
				if (stud.studentId == StudId)
				{
					return stud;
				}
			}
			return stud;

		}

		public static List<Group> getGroupsByLoggedInStudent()
		{
			List<Group> grpListByStudentId = new List<Group>();
			List<StudentGroup> stuGrpList = getDetails();

			for (int i = 0; i < stuGrpList.Count; i++)
			{
				StudentGroup studGrp = stuGrpList[i];
				if (stuId == studGrp.StudentId)
				{
					grpListByStudentId.Add(getGroupById(studGrp.GroupId));
				}
			}

			return grpListByStudentId;
		}

		public static Group getGroupById(int grpId)
		{
			
			Group grp = null;
			List<Group> grps = getGroupAllDetails();
			for (int i = 0; i < grps.Count; i++)
			{
				grp = grps[i];
				if (grp.GroupId == grpId)
				{
					return grp;
				}
			}
			return grp;

		}

		public static List<Group> getGroupAllDetails()
		{
			List<Group> groupDetailsList = new List<Group>();

			Group grp1 = new Group();
			grp1.Name = "Xamarin Group";
			grp1.image = "xamagon.png";
			grp1.GroupId = 1;
			grp1.Description = "Hey!Lets Learn Xamarin!!!";


			Group grp2 = new Group();
			grp2.Name = "Soccer Group";
			grp2.image = "soccer_1.png";
			grp2.GroupId = 2;
			grp2.Description = "Welcome to Soccer Club!!";



			Group grp3 = new Group();
			grp3.Name = "Guitar Group";
			grp3.image = "Guitar.png";
			grp3.GroupId = 3;
			grp3.Description = "Lets play music :)";

			Group grp4 = new Group();
			grp4.Name = "C# Group";
			grp4.image = "http://devstickers.com/assets/img/pro/2p4i.png";
			grp4.GroupId = 4;
			grp4.Description = "Lets c#";



			//for (Group : countOfGroupsInDB) {
			//  Group grp = new Group();
			//grp.setName("retrievve from db");
			//grp.setId("retrievve from db");
			//groupDetailsList.add(grp);
			// }
			groupDetailsList.Add(grp1);
			groupDetailsList.Add(grp2);
			groupDetailsList.Add(grp3);
			groupDetailsList.Add(grp4);

			return groupDetailsList;

		}




		public static List<StudentGroup> getDetails()
		{
			List<StudentGroup> studentGroupList = new List<StudentGroup>();
			StudentGroup studgrp = new StudentGroup();
			studgrp.StudentId = 1;
			studgrp.GroupId = 4;
			studentGroupList.Add(studgrp);

			StudentGroup studgrp1 = new StudentGroup();
			studgrp1.StudentId = 2;
			studgrp1.GroupId = 1;
			studentGroupList.Add(studgrp1);
			StudentGroup studgrp3 = new StudentGroup();
			studgrp3.StudentId = 3;
			studgrp3.GroupId = 1;
			studentGroupList.Add(studgrp3);
			StudentGroup studgrp4 = new StudentGroup();
			studgrp4.StudentId = 3;
			studgrp4.GroupId = 2;
			studentGroupList.Add(studgrp4);
			StudentGroup studgrp5 = new StudentGroup();
			studgrp5.StudentId = 1;
			studgrp5.GroupId = 3;
			studentGroupList.Add(studgrp5);
			StudentGroup studgrp6 = new StudentGroup();
			studgrp6.StudentId = 2;
			studgrp6.GroupId = 3;
			studentGroupList.Add(studgrp6);

			StudentGroup studgrp7 = new StudentGroup();
			studgrp7.StudentId = 2;
			studgrp7.GroupId = 2;
			studentGroupList.Add(studgrp7);

			return studentGroupList;
		}

	}
}

