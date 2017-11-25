using System;
using System.Collections.Generic;
using AppServiceHelpers.Models;

namespace StudyBuddy.Models
{
	public class Group : EntityData
	{

		public int GroupId { get; set; }
		public string Name { get; set; }
		public string Description { get; set; }
		public string image { get; set; }

	}
}
