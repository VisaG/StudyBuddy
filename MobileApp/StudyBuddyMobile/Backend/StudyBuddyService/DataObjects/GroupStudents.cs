using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class GroupStudents : EntityData
    {

        public string Group_Id { get; set; }
        public string Student_Id { get; set; }

    }
}