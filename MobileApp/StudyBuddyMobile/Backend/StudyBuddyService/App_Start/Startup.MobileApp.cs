using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Entity;
using System.Web.Http;
using Microsoft.Azure.Mobile.Server;
using Microsoft.Azure.Mobile.Server.Authentication;
using Microsoft.Azure.Mobile.Server.Config;
using StudyBuddyService.DataObjects;
using StudyBuddyService.Models;
using Owin;
using System.Data.Entity.Migrations;
using StudyBuddyService.Migrations;

namespace StudyBuddyService
{
    public partial class Startup
    {
        public static void ConfigureMobileApp(IAppBuilder app)
        {
            HttpConfiguration config = new HttpConfiguration();

            //For more information on Web API tracing, see http://go.microsoft.com/fwlink/?LinkId=620686 
            config.EnableSystemDiagnosticsTracing();

            new MobileAppConfiguration()
                .UseDefaultConfiguration()
                .ApplyTo(config);

            // Use Entity Framework Code First to create database tables based on your DbContext
            Database.SetInitializer(new StudyBuddyInitializer());
            var migrator = new DbMigrator(new Migrations.Configuration());
            migrator.Update();

            // To prevent Entity Framework from modifying your database schema, use a null database initializer
            // Database.SetInitializer<StudyBuddyContext>(null);

            MobileAppSettingsDictionary settings = config.GetMobileAppSettingsProvider().GetMobileAppSettings();

            if (string.IsNullOrEmpty(settings.HostName))
            {
                // This middleware is intended to be used locally for debugging. By default, HostName will
                // only have a value when running in an App Service application.
                app.UseAppServiceAuthentication(new AppServiceAuthenticationOptions
                {
                    SigningKey = ConfigurationManager.AppSettings["SigningKey"],
                    ValidAudiences = new[] { ConfigurationManager.AppSettings["ValidAudience"] },
                    ValidIssuers = new[] { ConfigurationManager.AppSettings["ValidIssuer"] },
                    TokenHandler = config.GetAppServiceTokenHandler()
                });
            }
            app.UseWebApi(config);
        }
    }

    public class StudyBuddyInitializer : CreateDatabaseIfNotExists<StudyBuddyContext>
    {
        protected override void Seed(StudyBuddyContext context)
        {
            Course c = new Course();
            c.Id = Guid.NewGuid().ToString();
            c.Name = "Xamarin";

            List<Student> students = new List<Student>
            {
                new Student { Id = Guid.NewGuid().ToString(), FirstName = "Milad", LastName="roigari" }
            };

            foreach (Student s in students)
            {
                s.Courses.Add(c);
                context.Set<Student>().Add(s);
            }

            base.Seed(context);
        }
    }
}

