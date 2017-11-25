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




	public class ProfilePageViewModel:BaseAzureViewModel<Student>
	{


		EasyMobileServiceClient client;

		Student user ;

		public Student Student { get; set; }



		public ProfilePageViewModel(EasyMobileServiceClient client):base(client)
		{

			user = Settings.Default.currentUser;
			this.client = client;



		}

	


	}
}
