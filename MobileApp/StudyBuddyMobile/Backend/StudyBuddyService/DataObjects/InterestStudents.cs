using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class InterestStudents : EntityData
    {

        public string Interest_Id   { get; set; }
        public string Student_Id { get; set; }

    }
}