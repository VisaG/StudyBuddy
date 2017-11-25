using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xamarin.Forms;

using StudyBuddy;
using StudyBuddy.ViewModels;
using StudyBuddy.Models;

namespace StudyBuddy.Views
{
	public partial class HomePage : ContentPage
	{
		public HomePage()
		{
			InitializeComponent();



			List<Group> groups = DataUtil.getGroupAllDetails();

			imgFeatured1.Source = groups[0].image;
			lblFeatured1.Text = groups[0].Name;
			imgFeatured1.ClassId = groups[0].GroupId.ToString();

			imgFeatured2.Source = groups[1].image;
			lblFeatured2.Text = groups[1].Name;
			imgFeatured2.ClassId = groups[1].GroupId.ToString();

			imgFeatured3.Source = groups[2].image;
			lblFeatured3.Text = groups[2].Name;
			imgFeatured3.ClassId = groups[2].GroupId.ToString();

			imgFeatured4.Source = groups[3].image;
			lblFeatured4.Text = groups[3].Name;
			imgFeatured4.ClassId = groups[3].GroupId.ToString();


		}

		async void OnAcceptButtonClicked(object sender, EventArgs args)
		{
			await Task.Delay(100);
			visaRequests.IsVisible = false;


			Settings.Default.Notifications.Add("You formed group Xamarin with  Ramya");
		



		}

		void OnTapGestureRecognizerTapped(object sender, EventArgs args)
		{
	
			var imageSender = (Image)sender;
			Navigation.PushAsync(new GroupDetailPage(int.Parse(imageSender.ClassId)));
		}

	}
}
