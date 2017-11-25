using System;
using System.Collections.Generic;

using Xamarin.Forms;
using XLabs.Forms.Controls;
using XLabs.Forms;
using StudyBuddy;
using StudyBuddy.ViewModels;
using StudyBuddy.Models;

namespace StudyBuddy.Views
{
	public class Repeater : XLabs.Forms.Controls.RepeaterView<Group>
	{
	}

	public partial class HomePage : ContentPage
	{
		public HomePage()
		{
			InitializeComponent();


			//BindingContext = new HomePageViewModel();


		}


		void OnTapGestureRecognizerTapped(object sender, EventArgs args)
		{
	
			var imageSender = (Image)sender;
			Navigation.PushAsync(new GroupDetailPage(int.Parse(imageSender.ClassId)));
		}

	}
}
