using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Controllers;
using System.Web.Http.OData;
using Microsoft.Azure.Mobile.Server;
using StudyBuddyService.DataObjects;
using StudyBuddyService.Models;
using StudyBuddyService.Extensions;

namespace StudyBuddyService.Controllers
{
    public class StudentController : TableController<Student>
    {
        protected override void Initialize(HttpControllerContext controllerContext)
        {
            base.Initialize(controllerContext);
            StudyBuddyContext context = new StudyBuddyContext();
            DomainManager = new EntityDomainManager<Student>(context, Request);
        }

    
        public IQueryable<Student> GetAllStudents()
        {
            return Query();
        }

        // GET tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public SingleResult<Student> GetStudent(string id)
        {
            return Lookup(id);
        }

        // PATCH tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task<Student> PatchStudent(string id, Delta<Student> patch)
        {
            return UpdateAsync(id, patch);
        }

        // POST tables/TodoItem
        public async Task<IHttpActionResult> PostStudent(Student item)
        {
            Student current = await InsertAsync(item);
            return CreatedAtRoute("Tables", new { id = current.Id }, current);
        }

        // DELETE tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task DeleteStudent(string id)
        {
            return DeleteAsync(id);
        }
    }
}