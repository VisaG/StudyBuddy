using System;
using System.Linq;
using Xamarin.Forms;
using System.Threading.Tasks;
using System.Collections.ObjectModel;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;
using AppServiceHelpers.Forms;
using AppServiceHelpers;
using AppServiceHelpers.Abstractions;


using StudyBuddy.Models;
using Microsoft.WindowsAzure.MobileServices;
using Microsoft.WindowsAzure.MobileServices.Sync;

using StudyBuddy.Views;

namespace StudyBuddy.ViewModels
{
	public class LoginPageViewModel : BaseAzureViewModel<Student>
	{

		public INavigation Navigation;

		string user = "";
		string pass = "";

		public LoginPageViewModel(EasyMobileServiceClient client) : base(client)
		{


			IMobileServiceTable<Student> studentTable = client.MobileService.GetTable<Student>();


			NavigationCommand = new Command(async() =>
		   {
			   List<Student> items = await studentTable
					.Where(x => x.userId == user && x.password == pass)
					.ToListAsync();
		

		


			   //var s = await studentTable.GetItemsAsync();

			   if (items.Count > 0)
			   {
				   Settings.Default.currentUser = items.FirstOrDefault();
				   await Navigation.PushModalAsync(new Views.MainPage(client));

			   }

		   });






			Title = "Login";
		}

		public string User
		{
			get { return user; }
			set
			{
				user = value;
				SetProperty(ref user, value, "user");
			}
		}
		public string Pass
		{
			get { return pass; }
			set
			{
				pass = value;
				SetProperty(ref pass, value, "pass");
			}
		}

		//new public event PropertyChangedEventHandler PropertyChanged;

		//new void OnPropertyChanged([CallerMemberName] string name = "")
		//{
		//	PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
		//}
		public ICommand NavigationCommand { get; set; }





	}
}
