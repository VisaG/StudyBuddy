using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using Microsoft.Azure.Mobile.Server;
using Microsoft.Azure.Mobile.Server.Tables;
using StudyBuddyService.DataObjects;

namespace StudyBuddyService.Models
{
    public class StudyBuddyContext : DbContext
    {
        // You can add custom code to this file. Changes will not be overwritten.
        // 
        // If you want Entity Framework to alter your database
        // automatically whenever you change your model schema, please use data migrations.
        // For more information refer to the documentation:
        // http://msdn.microsoft.com/en-us/data/jj591621.aspx

        private const string connectionStringName = "Name=MS_TableConnectionString";

        public StudyBuddyContext() : base(connectionStringName)
        {
        } 

        public DbSet<Student> Students { get; set; }

        public DbSet<Course> Courses { get; set; }
        public DbSet<Group> Groups { get; set; }

        public DbSet<Request> Requests { get; set; }

        public DbSet<InterestStudents> InterestStudents { get; set; }
        public DbSet<GroupStudents> GroupStudents { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Add(
                new AttributeToColumnAnnotationConvention<TableColumnAttribute, string>(
                    "ServiceTableColumn", (property, attributes) => attributes.Single().ColumnType.ToString()));
        }
    }

}
