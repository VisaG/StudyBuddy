using System;
using System.Collections.Generic;
using Xamarin.Forms;

using StudyBuddy.Models;

namespace StudyBuddy.Views
{
	public partial class GroupDetailPage : ContentPage
	{

		Button button1;

		public GroupDetailPage(int GroupId)
		{
			InitializeComponent();


			Group grp = new Group();

			List<Student> studlist = new List<Student>();


			GroupName.Text = grp.Name;
			Des.Text = grp.Description;
			GroupImg.Source = Device.OnPlatform(
				iOS: ImageSource.FromFile(grp.image),
									Android: ImageSource.FromFile(grp.image),
									WinPhone: ImageSource.FromFile(grp.image));

			for (int j = 0; j < studlist.Count; j++)
			{
				Label label = new Label
				{
					//Text = studlist.ElementAt(j).StuName,
					Text = studlist[j].firstName + " " + studlist[j].lastName,
					FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label))
				};
				Image img = new Image
				{
					Source =
					 Device.OnPlatform(
							iOS: ImageSource.FromFile(studlist[j].image),
				Android: ImageSource.FromFile(studlist[j].image),
				WinPhone: ImageSource.FromFile(studlist[j].image))

				};
				button1 = new Button
				{
					// ClassId = j+"",
					Text = "x",
					FontSize = 10,
					FontAttributes = FontAttributes.Bold,
					BackgroundColor = Color.White,
					HorizontalOptions = LayoutOptions.EndAndExpand,

					//  IsVisible = false,

					// HeightRequest=10,
					// WidthRequest=10,

					BorderColor = Color.Black
					//  HorizontalOptions = LayoutOptions.End

				};
				BoxView boxview = new BoxView
				{

					HeightRequest = 1,
					WidthRequest = 100,
					BackgroundColor = Color.Black
				};

				Frame frame = new Frame
				{
					// ClassId = myGrpList.ElementAt(i).GroupId + "",
					//  OutlineColor = Color.Black,
					BackgroundColor = Color.Transparent,
					WidthRequest = 100,
					HeightRequest = 30,
					Content = new StackLayout
					{
						Orientation = StackOrientation.Horizontal,

						Children =
									{
									  img,
									  label,

									  button1
									}
					}

				};


				// StackLayout1.Children.Add(frame);
				st.Children.Add(frame);
				st.Children.Add(boxview);
			}

		}

		void OnEditButtonClicked(object sender, EventArgs args)
		{
			Button button = (Button)sender;
			button.IsVisible = false;

			Leavegroup.IsVisible = false;
			Save.IsVisible = true;
			Cancel.IsVisible = true;
			//   button1.IsVisible = true;


		}
		void OnSaveButtonClicked(object sender, EventArgs args)
		{
			Button button = (Button)sender;
			button.IsVisible = false;

			Leavegroup.IsVisible = true;
			Edit.IsVisible = true;
			Cancel.IsVisible = false;
			//button1.IsVisible = false;
		}
		void OnCancelButtonClicked(object sender, EventArgs args)
		{
			Button button = (Button)sender;
			button.IsVisible = false;

			Leavegroup.IsVisible = true;
			Edit.IsVisible = true;
			Save.IsVisible = false;
			//button1.IsVisible = false;
		}
	}
}
