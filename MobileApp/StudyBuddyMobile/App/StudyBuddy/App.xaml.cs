using System;
using Xamarin.Forms;
using StudyBuddy.ViewModels;
using StudyBuddy.Views;
using StudyBuddy.Models;

using AppServiceHelpers;
using AppServiceHelpers.Abstractions;
using AppServiceHelpers.Utils;


using System.Collections.Generic;
using System.Linq;
using System.Text;
using GalaSoft.MvvmLight.Ioc;
using GalaSoft.MvvmLight.Views;
using Microsoft.Practices.ServiceLocation;

using System.Collections.ObjectModel;

namespace StudyBuddy
{



	public class Settings
	{
		// this is the default static instance you'd use like string text = Settings.Default.SomeSetting;
		public readonly static Settings Default = new Settings();

		public Student currentUser { get; set; } // add setting properties as you wish


		public ObservableCollection<Student> Students { get; set; }
		public ObservableCollection<GroupStudents> GroupStudents { get; set; }
		public ObservableCollection<Group> Groups { get; set; }
		public ObservableCollection<Interest> Interests { get; set; }
		public ObservableCollection<InterestStudents> InterestStudents { get; set; }
		public ObservableCollection<Request> Requests { get; set; }
	}


	public partial class App : Application
	{


	

	    EasyMobileServiceClient client;

		public App()
		{


			Settings.Default.Groups = new ObservableCollection<Group>{
				new Group{Name="Xamarin",image="https://lh4.googleusercontent.com/--6HuPTRFJ7w/AAAAAAAAAAI/AAAAAAAAAJ4/SUatxvJy0uQ/photo.jpg"},
				new Group{Name="C#",image="https://cdn.codementor.io/assets/tutors/c-sharp-tutors-online.png"},
				new Group{Name="Java",image="https://pbs.twimg.com/profile_images/426420605945004032/K85ZWV2F_400x400.png"},
				new Group{Name="Sports",image="http://proprofs.com/quiz-school/topic_images/p18lq7ediepl816p6s04171vo23.jpg"}};

			Settings.Default.Students = new ObservableCollection<Student>{
				new Student{userId="miladroy",firstName="Milad",lastName="roigari",studyLevel=4,image="https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/5/005/00b/134/26103bf.jpg"},
				new Student{userId="Ramya",firstName="Ramya",lastName="",studyLevel=4,image=""},
				new Student{userId="Visa",firstName="Visa",lastName="",studyLevel=4,image=""}
			};

			Settings.Default.Interests = new ObservableCollection<Interest>{
				new Interest{Name="Programming"},
				new Interest{Name="Psychology"},
				new Interest{Name="Management"},
				new Interest{Name="Business"}
			};


			Settings.Default.Requests = new ObservableCollection<Request>{
				new Request{student1="Ramya",student2="Milad",type=""},
				new Request{student1="Ramya",student2="Milad",type=""},
				new Request{student1="Visa",student2="Milad",type=""},
				new Request{student1="Visa",student2="Milad",type=""}
			};


			InitializeComponent();


			client = new EasyMobileServiceClient();
			client.Initialize("http://studybuddy.azurewebsites.net");
			client.RegisterTable<Student>();
			client.RegisterTable<Course>();
			client.RegisterTable<Interest>();
			client.RegisterTable<Group>();
			client.RegisterTable<Request>();
			client.RegisterTable<GroupStudents>();
			client.RegisterTable<InterestStudents>();
			client.FinalizeSchema();


			MainPage = new MainPage(client);//LoginPage(client);




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
