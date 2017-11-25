using System;
using System.Collections.Generic;
using AppServiceHelpers.Models;

namespace StudyBuddy.Models
{
	public class GroupStudents : EntityData
	{
		public string Group_Id { get; set; }
		public string Student_Id { get; set; }

	}
}
