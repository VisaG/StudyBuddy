using System;
using System.Linq;
using Xamarin.Forms;
using System.Threading.Tasks;
using System.Collections.ObjectModel;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;

using AppServiceHelpers.Forms;
using AppServiceHelpers;
using AppServiceHelpers.Abstractions;


using StudyBuddy.Models;

namespace StudyBuddy.ViewModels
{
	public class MainPageViewModel 
	{
		EasyMobileServiceClient client;

		HomePageViewModel homePageVM;
		ProfilePageViewModel profilePageVM;

		public HomePageViewModel HomePageVM
		{
			get { return homePageVM; }
		}

		public ProfilePageViewModel ProfilePageVM
		{
			get { return profilePageVM; }
		}

		public MainPageViewModel(EasyMobileServiceClient client) 
		{

			homePageVM = new HomePageViewModel(client);
			profilePageVM = new ProfilePageViewModel(client);


		}


	}
}
