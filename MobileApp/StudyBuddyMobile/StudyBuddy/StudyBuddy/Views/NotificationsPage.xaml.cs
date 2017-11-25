using System;
using System.Collections.Generic;

using System.Collections.ObjectModel;
using Xamarin.Forms;

namespace StudyBuddy.Views
{
	public partial class NotificationsPage : ContentPage
	{
		
		
		public NotificationsPage()
		{
			InitializeComponent();
		}

		protected new virtual void OnAppearing()
		{
			
		}

		 void OnSubmitButtonClicked(object sender, EventArgs args)
		{
			Refresh();
		}
		public void Refresh()
		{

			notificaitonStack.Children.Clear();

			foreach (var n in Settings.Default.Notifications)
			{
				Label l = new Label
				{
					Text = n,
					FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label)),
				};

				BoxView b= new BoxView
				{
					Color =Color.FromHex( "#FFD3E0"),
					HeightRequest = 2,
					WidthRequest = 100
				};
				notificaitonStack.Children.Add(l);
				notificaitonStack.Children.Add(b);
				                               
			}

		}
	}
}
