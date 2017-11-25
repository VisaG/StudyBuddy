using System;
using System.Collections.Generic;

using AppServiceHelpers;
using AppServiceHelpers.Abstractions;
using AppServiceHelpers.Utils;

using StudyBuddy.Models;

using Xamarin.Forms;
using Microsoft.Practices.ServiceLocation;

namespace StudyBuddy.Views
{
	public partial class LoginPage : ContentPage
	{

		public LoginPage(EasyMobileServiceClient client)
		{
			InitializeComponent();
			var vm = new ViewModels.LoginPageViewModel(client);
			vm.Navigation = this.Navigation;
			this.BindingContext = vm;

		}

		protected override void OnAppearing()
		{
			base.OnAppearing();

			//var vm = (ViewModels.LoginPageViewModel)BindingContext;
			//vm.RefreshCommand.Execute(null);
		}






	}
}
