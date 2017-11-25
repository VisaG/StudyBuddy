using System;
using Xamarin.Forms;
using StudyBuddy.ViewModels;
using StudyBuddy.Views;
using StudyBuddy.Models;

using System.Collections.ObjectModel;


namespace StudyBuddy
{
	public static class ViewModelLocator
	{
		static HomePageViewModel homepageVM;
		public static HomePageViewModel HomePageViewModel
		=> homepageVM ?? (homepageVM = new HomePageViewModel());

	}




	public class Settings
	{
		// this is the default static instance you'd use like string text = Settings.Default.SomeSetting;
		public readonly static Settings Default = new Settings();

		public Student currentUser { get; set; } // add setting properties as you wish


		public ObservableCollection<String> Notifications { get; set; }
		public ObservableCollection<Student> Students { get; set; }

		public ObservableCollection<Group> Groups { get; set; }
		public ObservableCollection<Interest> Interests { get; set; }

	}





	public partial class App : Application
	{



		public App()
		{
			InitializeComponent();
			Settings.Default.Notifications = new ObservableCollection<String>();
			MainPage = new LoginPage();


		}

		protected override void OnStart()
		{
			// Handle when your app starts
		}

		protected override void OnSleep()
		{
			// Handle when your app sleeps
		}

		protected override void OnResume()
		{
			// Handle when your app resumes
		}
	}
}
