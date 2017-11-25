using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(StudyBuddyService.Startup))]

namespace StudyBuddyService
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureMobileApp(app);
        }
    }
}