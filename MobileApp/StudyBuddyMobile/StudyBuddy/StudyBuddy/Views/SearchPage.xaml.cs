using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xamarin.Forms;

using StudyBuddy.Models;

namespace StudyBuddy.Views
{
	public partial class SearchPage : ContentPage
	{

		bool availability = false;
		Group groupFound;

		public SearchPage()
		{
			InitializeComponent();
		}




		void OnSwitchAvailToggled(object sender, EventArgs args)
		{
			var s=(Switch)sender;
			availability = s.IsToggled;

		}

		void OnSwitchCourseToggled(object sender, EventArgs args)
		{
		}

		void OnSearchBarTextChanged(object sender, TextChangedEventArgs e)
		{
			searchResult.Children.Clear();
			studentResult.Children.Clear();
		}

		void OnSearchBarButtonPressed(object sender, EventArgs args)
		{
			searchResult.Children.Clear();
			studentResult.Children.Clear();


			DataSearchUtil.search = searchBar.Text;

			if (DataSearchUtil.search != null)
			{

				groupFound = DataSearchUtil.getGroupByName();


				if (groupFound != null)
				{
					createGroupSearchStack();
					createStudentSearchStack(groupFound.GroupId);

				}


			}

		}


		async void OnAddButtonPressed(object sender, EventArgs args)
		{
			var b = (Button)sender;
			b.IsEnabled = false;
			await Task.Delay(300);
			await DisplayAlert("Congratulations!", "Your request is successfully sent", "ok");
			Settings.Default.Notifications.Add("You sent a request to Ramya to from group C#");

		}

		void createGroupSearchStack()
		{
			Image addImage = new Image
			{
				Source = new FileImageSource
				{
					File = groupFound.image
				},

				WidthRequest = 30,
				HorizontalOptions = LayoutOptions.StartAndExpand
			};

			Label label = new Label
			{
				Text = groupFound.Name,
				FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label)),
				HorizontalOptions = LayoutOptions.End,
				VerticalOptions = LayoutOptions.Center


			};

			Button button = new Button
			{
				Text = "+",
				WidthRequest = 30,
				HeightRequest = 30,
				BorderColor = Color.Black,
				BorderWidth = 1
			};


			StackLayout stack = new StackLayout
			{
				Orientation = StackOrientation.Horizontal,
				Children = {
						addImage,
						label,
					button
					}
			};

			searchResult.Children.Add(stack);
		}

		void createStudentSearchStack(int groupId)
		{
			List<Student> studlist = DataUtil.getStudbygrpid(groupId);

			for (int i = 0; i < studlist.Count; i++)
			{

				if (availability && (studlist[i].avail!=Settings.Default.currentUser.avail))
					continue;
					
						
				Image addImage = new Image
				{
					Source = new FileImageSource
					{
						File = studlist[i].fileImage
					},

					WidthRequest = 30,
					HorizontalOptions = LayoutOptions.StartAndExpand
				};

				Label label = new Label
				{
					Text = studlist[i].firstName + " " + studlist[i].lastName,
					FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label)),
					HorizontalOptions = LayoutOptions.Start,
					VerticalOptions = LayoutOptions.Center

				};

				Button button = new Button
				{
					Text = "+",
					WidthRequest = 30,
					HeightRequest = 30,
					BorderColor = Color.Black,
					BorderWidth = 1

				};

				button.Clicked += OnAddButtonPressed;

				StackLayout stack = new StackLayout
				{
					Orientation = StackOrientation.Horizontal,
					Children = {
						addImage,
						label,
					button
					}
				};

				studentResult.Children.Add(stack);
			}
		}
	}
}
