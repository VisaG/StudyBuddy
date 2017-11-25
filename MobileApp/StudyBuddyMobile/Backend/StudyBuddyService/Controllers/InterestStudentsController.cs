using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Controllers;
using System.Web.Http.OData;
using Microsoft.Azure.Mobile.Server;
using StudyBuddyService.DataObjects;
using StudyBuddyService.Models;

namespace StudyBuddyService.Controllers
{
    public class InterestStudentsController : TableController<InterestStudents>
    {
        protected override void Initialize(HttpControllerContext controllerContext)
        {
            base.Initialize(controllerContext);
            StudyBuddyContext context = new StudyBuddyContext();
            DomainManager = new EntityDomainManager<InterestStudents>(context, Request);
        }

        // GET tables/TodoItem
        public IQueryable<InterestStudents> GetAllInterestStudents()
        {
            return Query();
        }

        // GET tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public SingleResult<InterestStudents> GetInterestStudents(string id)
        {
            return Lookup(id);
        }

        // PATCH tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task<InterestStudents> PatchInterestStudents(string id, Delta<InterestStudents> patch)
        {
            return UpdateAsync(id, patch);
        }

        // POST tables/TodoItem
        public async Task<IHttpActionResult> PostInterestStudents(InterestStudents item)
        {
            InterestStudents current = await InsertAsync(item);
            return CreatedAtRoute("Tables", new { id = current.Id }, current);
        }

        // DELETE tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task DeleteInterestStudents(string id)
        {
            return DeleteAsync(id);
        }
    }
}