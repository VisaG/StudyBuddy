using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class Interest : EntityData
    {
        public Interest()
        {
            this.Students = new HashSet<Student>();
        }
        public string Name { get; set; }
        public string Description { get; set; }
        public virtual ICollection<Student> Students { get; set; }

    }
}