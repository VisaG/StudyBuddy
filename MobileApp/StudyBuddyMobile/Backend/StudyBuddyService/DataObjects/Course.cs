using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class Course : EntityData
    {
        public Course()
        {
            this.Students = new HashSet<Student>();
        }
        public string Name { get; set; }
        public string Description { get; set; }
        public virtual ICollection<Student> Students { get; set; }

    }
}