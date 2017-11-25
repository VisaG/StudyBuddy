using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

using StudyBuddy.Models;

namespace StudyBuddy.Views
{
	public partial class GroupsPage : ContentPage
	{

		public GroupsPage()
		{
			InitializeComponent();
			GroupPreviewLayout();
		}
	

		void GroupPreviewLayout()
		{
			List<Group> myGrpList = new List<Group>();

			for (int i = 0; i < myGrpList.Count; i++)
			{
				Label label = new Label
				{
					Text = myGrpList.ElementAt(i).Name,
					FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label))
				};
				Button button = new Button
				{
					ClassId = i + "",
					Text = "x",
					FontSize = 10,
					FontAttributes = FontAttributes.Bold,
					BackgroundColor = Color.White,
					HorizontalOptions = LayoutOptions.EndAndExpand,

					// HeightRequest=10,
					// WidthRequest=10,

					BorderColor = Color.Black
					//  HorizontalOptions = LayoutOptions.End

				};
				Frame frame = new Frame
				{
					ClassId = myGrpList[i].GroupId + "",
					OutlineColor = Color.Black,
					BackgroundColor = Color.Silver,
					WidthRequest = 100,
					HeightRequest = 30,
					Content = new StackLayout
					{
						Orientation = StackOrientation.Horizontal,
						//Spacing = 100,
						Children =
									{
									  label,
									button
									}
					}

				};


				StackLayout1.Children.Add(frame);
				button.Clicked += OnRemoveButtonClicked;

				TapGestureRecognizer tg = new TapGestureRecognizer();
				tg.Tapped += OnFrameTapped;
				frame.GestureRecognizers.Add(tg);

			}

		}
		void OnFrameTapped(object sender, EventArgs args)
		{

			Frame frame = (Frame)sender;
			int grpid = Convert.ToInt32(frame.ClassId);
			//Group grp = new Group();
			//List<Group> grpdetailslist = DataUtil.getGroupAllDetails();
			//List<Student> studlist = new List<Student>();
			//for (int i = 0; i < grpdetailslist.Count; i++)
			//{
			//	if (grpdetailslist[i].GroupId == grpid)

			//	{
			//		grp.GroupId = grpdetailslist[i].GroupId;
			//		grp.Name = grpdetailslist[i].Name;
			//		grp.image = grpdetailslist[i].image;
			//		grp.Description = grpdetailslist[i].Description;
			//		studlist = DataUtil.getStudbygrpid(grpid);



			//	}

			//}
			Navigation.PushAsync(new GroupDetailPage(grpid));

		}

		void OnRemoveButtonClicked(object sender, EventArgs args)
		{
		}

	}
}
