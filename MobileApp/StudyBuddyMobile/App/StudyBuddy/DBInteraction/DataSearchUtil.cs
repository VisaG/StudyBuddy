using System;
using System.Collections.Generic;
using StudyBuddy.Models;
namespace StudyBuddy
{
	public class DataSearchUtil
	{
		public static string search;
		public static Group group;


		public static Group getGroupByName()
		{
			List<Group> grp = DataUtil.getGroupAllDetails();

			var value = grp.Find(item => item.Name.ToLower() == search.ToLower());

			return value;

			//for (int i = 0; i < grp.Count; i++)
			//{
			//	if (string.Equals(grp[i].Name, search, StringComparison.OrdinalIgnoreCase))
			//	{
			//		group = grp[i];
			//	}

			//}
			//return group;


		}

	}
}
