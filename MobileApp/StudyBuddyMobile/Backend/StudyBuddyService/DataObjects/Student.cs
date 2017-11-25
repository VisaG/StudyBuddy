using Microsoft.Azure.Mobile.Server;
using System.Collections.Generic;

namespace StudyBuddyService.DataObjects
{
    public class Student : EntityData
    {
        public Student()
        {
            this.Courses = new HashSet<Course>();
            this.Groups = new HashSet<Group>();
            this.Interests = new HashSet<Interest>();
        }
        public string UserId { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string Password { get; set; }

        public int StudyLevel { get; set; }

        public string Image { get; set; }

        public virtual ICollection<Course> Courses { get; set; }
        public virtual ICollection<Group> Groups { get; set; }
        public virtual ICollection<Interest> Interests { get; set; }
    }
}