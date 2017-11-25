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

		public ObservableCollection<Student> Students { get; set; }

		public ObservableCollection<Group> Groups { get; set; }

		public ObservableCollection<Request> Requests { get; set; }


		public HomePageViewModel(EasyMobileServiceClient client):base(client)
		{

			user = Settings.Default.currentUser;
			this.client = client;

			Students = Settings.Default.Students;
			Groups = Settings.Default.Groups;
			Requests = Settings.Default.Requests;
		}

		public async Task init()
		{
			
	
		}


	}
}
