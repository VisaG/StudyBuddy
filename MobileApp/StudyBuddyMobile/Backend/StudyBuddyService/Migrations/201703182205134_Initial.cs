namespace StudyBuddyService.Migrations
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity.Infrastructure.Annotations;
    using System.Data.Entity.Migrations;
    
    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Courses", "Student_Id", "dbo.Students");
            DropIndex("dbo.Courses", new[] { "Student_Id" });
            CreateTable(
                "dbo.Groups",
                c => new
                    {
                        Id = c.String(nullable: false, maxLength: 128,
                            annotations: new Dictionary<string, AnnotationValues>
                            {
                                { 
                                    "ServiceTableColumn",
                                    new AnnotationValues(oldValue: null, newValue: "Id")
                                },
                            }),
                        GroupId = c.Int(nullable: false),
                        Name = c.String(),
                        Description = c.String(),
                        image = c.String(),
                        Version = c.Binary(nullable: false, fixedLength: true, timestamp: true, storeType: "rowversion",
                            annotations: new Dictionary<string, AnnotationValues>
                            {
                                { 
                                    "ServiceTableColumn",
                                    new AnnotationValues(oldValue: null, newValue: "Version")
                                },
                            }),
                        CreatedAt = c.DateTimeOffset(nullable: false, precision: 7,
                            annotations: new Dictionary<string, AnnotationValues>
                            {
                                { 
                                    "ServiceTableColumn",
                                    new AnnotationValues(oldValue: null, newValue: "CreatedAt")
                                },
                            }),
                        UpdatedAt = c.DateTimeOffset(precision: 7,
                            annotations: new Dictionary<string, AnnotationValues>
                            {
                                { 
                                    "ServiceTableColumn",
                                    new AnnotationValues(oldValue: null, newValue: "UpdatedAt")
                                },
                            }),
                        Deleted = c.Boolean(nullable: false,
                            annotations: new Dictionary<string, AnnotationValues>
                            {
                                { 
                                    "ServiceTableColumn",
                                    new AnnotationValues(oldValue: null, newValue: "Deleted")
                                },
                            }),
                    })
                .PrimaryKey(t => t.Id)
                .Index(t => t.CreatedAt, clustered: true);
            
            CreateTable(
                "dbo.StudentCourses",
                c => new
                    {
                        Student_Id = c.String(nullable: false, maxLength: 128),
                        Course_Id = c.String(nullable: false, maxLength: 128),
                    })
                .PrimaryKey(t => new { t.Student_Id, t.Course_Id })
                .ForeignKey("dbo.Students", t => t.Student_Id, cascadeDelete: true)
                .ForeignKey("dbo.Courses", t => t.Course_Id, cascadeDelete: true)
                .Index(t => t.Student_Id)
                .Index(t => t.Course_Id);
            
            CreateTable(
                "dbo.GroupStudents",
                c => new
                    {
                        Group_Id = c.String(nullable: false, maxLength: 128),
                        Student_Id = c.String(nullable: false, maxLength: 128),
                    })
                .PrimaryKey(t => new { t.Group_Id, t.Student_Id })
                .ForeignKey("dbo.Groups", t => t.Group_Id, cascadeDelete: true)
                .ForeignKey("dbo.Students", t => t.Student_Id, cascadeDelete: true)
                .Index(t => t.Group_Id)
                .Index(t => t.Student_Id);
            
            DropColumn("dbo.Courses", "Student_Id");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Courses", "Student_Id", c => c.String(maxLength: 128));
            DropForeignKey("dbo.GroupStudents", "Student_Id", "dbo.Students");
            DropForeignKey("dbo.GroupStudents", "Group_Id", "dbo.Groups");
            DropForeignKey("dbo.StudentCourses", "Course_Id", "dbo.Courses");
            DropForeignKey("dbo.StudentCourses", "Student_Id", "dbo.Students");
            DropIndex("dbo.GroupStudents", new[] { "Student_Id" });
            DropIndex("dbo.GroupStudents", new[] { "Group_Id" });
            DropIndex("dbo.StudentCourses", new[] { "Course_Id" });
            DropIndex("dbo.StudentCourses", new[] { "Student_Id" });
            DropIndex("dbo.Groups", new[] { "CreatedAt" });
            DropTable("dbo.GroupStudents");
            DropTable("dbo.StudentCourses");
            DropTable("dbo.Groups",
                removedColumnAnnotations: new Dictionary<string, IDictionary<string, object>>
                {
                    {
                        "CreatedAt",
                        new Dictionary<string, object>
                        {
                            { "ServiceTableColumn", "CreatedAt" },
                        }
                    },
                    {
                        "Deleted",
                        new Dictionary<string, object>
                        {
                            { "ServiceTableColumn", "Deleted" },
                        }
                    },
                    {
                        "Id",
                        new Dictionary<string, object>
                        {
                            { "ServiceTableColumn", "Id" },
                        }
                    },
                    {
                        "UpdatedAt",
                        new Dictionary<string, object>
                        {
                            { "ServiceTableColumn", "UpdatedAt" },
                        }
                    },
                    {
                        "Version",
                        new Dictionary<string, object>
                        {
                            { "ServiceTableColumn", "Version" },
                        }
                    },
                });
            CreateIndex("dbo.Courses", "Student_Id");
            AddForeignKey("dbo.Courses", "Student_Id", "dbo.Students", "Id");
        }
    }
}
