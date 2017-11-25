using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class Request : EntityData
    {


        public Student student1 { get; set; }

        public Student student2 { get; set; }

        public int type { get; set; }



    }
}