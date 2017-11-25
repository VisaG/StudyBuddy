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




	public class SearchPageViewModel:BaseAzureViewModel<Student>
	{


		EasyMobileServiceClient client;

		Student user ;

		public ObservableCollection<Student> Student { get; set; }
		public ObservableCollection<Group> LatestGroups { get; set; }
		public ObservableCollection<Group> FeaturedGroups { get; set; }
		public ObservableCollection<Request> Requests { get; set; }


		public SearchPageViewModel(EasyMobileServiceClient client):base(client)
		{

			user = Settings.Default.currentUser;
			this.client = client;

			LatestGroups = new ObservableCollection<Group>{
				new Group{Name="Xamarin",image="https://lh4.googleusercontent.com/--6HuPTRFJ7w/AAAAAAAAAAI/AAAAAAAAAJ4/SUatxvJy0uQ/photo.jpg"},
				new Group{Name="C#",image="https://cdn.codementor.io/assets/tutors/c-sharp-tutors-online.png"},
				new Group{Name="Java",image="https://pbs.twimg.com/profile_images/426420605945004032/K85ZWV2F_400x400.png"},
				new Group{Name="Sports",image="http://proprofs.com/quiz-school/topic_images/p18lq7ediepl816p6s04171vo23.jpg"}};
		

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
