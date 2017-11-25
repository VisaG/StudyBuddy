using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using StudyBuddy.Models;

using AppServiceHelpers.Forms;
using AppServiceHelpers;
using AppServiceHelpers.Abstractions;

using StudyBuddy.Models;
using Microsoft.WindowsAzure.MobileServices;
using Microsoft.WindowsAzure.MobileServices.Sync;


namespace StudyBuddy.ViewModels
{




	public class HomePageViewModel:BaseAzureViewModel<Student>
	{


		EasyMobileServiceClient client;

		Student user ;

		public ObservableCollection<Student> Student { get; set; }
		public ObservableCollection<Group> LatestGroups { get; set; }
		public ObservableCollection<Group> FeaturedGroups { get; set; }
		public ObservableCollection<Request> Requests { get; set; }


		public HomePageViewModel(EasyMobileServiceClient client):base(client)
		{

			user = Settings.Default.currentUser;
			this.client = client;


			init().ConfigureAwait(false);
		}

		public async Task init()
		{
			
			IMobileServiceTable<Group> g = client.MobileService.GetTable<Group>();
			IMobileServiceTable<Request> r = client.MobileService.GetTable<Request>();

			Requests=await r.Where(x => x.student2 == user.Id).ToCollectionAsync();
			FeaturedGroups=await g.Where(x => x.GroupId == 1).ToCollectionAsync();
			LatestGroups = await g.ToCollectionAsync();
		}


	}
}
