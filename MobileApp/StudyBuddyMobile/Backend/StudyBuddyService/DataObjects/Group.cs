using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class Group : EntityData
    {
        public Group()
        {
            this.Students = new HashSet<Student>();
        }
        public int GroupId { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string image { get; set; }

        public virtual ICollection<Student> Students { get; set; }
    }
}