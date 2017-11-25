using System;
using System.Collections.Generic;

using Xamarin.Forms;


using StudyBuddy.Models;


namespace StudyBuddy.Views
{
	public partial class ProfilePage : ContentPage
	{

		List<Interest> currInterest;
		List<CourseId> currCourse;

		String fileInterestImage = "star.jpg";
		String fileCourseImage = "course.jpg";
		Button courseAddButton = new Button();
		Button courseDeleteButton = new Button();


		public ProfilePage()
		{
			InitializeComponent();

			Student currStudent = LoginPage.getCurrentStudent();

			DataInterestUtil.studentId = currStudent.studentId;
			DataCourseUtil.studentId = currStudent.studentId;

			currInterest = DataInterestUtil.getStudentInterestById();
			currCourse = DataCourseUtil.getCourseByCourseId();

			profilePic.Source = ImageSource.FromFile(currStudent.fileImage);
			StudentName.Text = currStudent.firstName + " " + currStudent.lastName;
			studyLevel.Text = currStudent.studyLevel;



			for (int i = 0; i < currInterest.Count; i++)
			{
				createInterestStack(currInterest[i].interest, fileInterestImage, i);
			}

			BoxView boxview = new BoxView
			{
				Color = Color.Silver,
				HeightRequest = 10,
				HorizontalOptions = LayoutOptions.Fill

			};



			Button addInterestButton = new Button
			{
				Text = "+",
				BorderColor = Color.Black,
				BorderWidth = 1,
				WidthRequest = 30,
				HeightRequest = 20,
				HorizontalOptions = LayoutOptions.End


			};

			addInterestButton.Clicked += addIntersetButtonClicked;

			Entry addInterestText = new Entry
			{
				Placeholder = "Interest",
				WidthRequest = 250,
				HorizontalOptions = LayoutOptions.StartAndExpand
			};



			StackLayout addbuttontoInterestStack = new StackLayout
			{

				Orientation = StackOrientation.Horizontal,
				Spacing = 1,
				Children = {


					addInterestText,
					addInterestButton,

					}


			};


			interestCourseStack.Children.Add(addbuttontoInterestStack);



			Label label = new Label
			{
				Text = "My Courses",
				VerticalOptions = LayoutOptions.CenterAndExpand,
				FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label)),
				HorizontalOptions = LayoutOptions.StartAndExpand

			};

		

			interestCourseStack.Children.Add(label);



			for (int i = 0; i < currCourse.Count; i++)
			{
				createInterestStack(currCourse[i].courseDesc, fileCourseImage, i);
			}

			Button courseAddButton = new Button
			{
				Text = "+",

				HeightRequest = 15,
				BorderColor = Color.Black,
				BorderWidth = 1,
			
			

			};

			courseAddButton.Clicked += addCourseButtonClicked;

			Entry addCourseText = new Entry
			{
				Placeholder = "Course Taken",
				WidthRequest = 250,
					HorizontalOptions = LayoutOptions.StartAndExpand
			};



			StackLayout addbuttontoCourseStack = new StackLayout
			{

				Orientation = StackOrientation.Horizontal,
				Spacing = 1,
				Children = {


					addCourseText,
					courseAddButton

					}


			};


			StackLayout courseStack = new StackLayout
			{
				Orientation = StackOrientation.Vertical,
				Children = {
					addbuttontoCourseStack,
					boxview
					}

			};

			interestCourseStack.Children.Add(courseStack);





		}

		void onDaySelected(object sender, EventArgs e)
		{
		}

		void OnTimeFromPickerPropertyChanged(object sender, EventArgs e)
		{
		}
		void OnTimeToPickerPropertyChanged(object sender, EventArgs e)
		{
		}


		void onEditButtonClicked(object sender, EventArgs e)
		{
			save.IsVisible = true;
			cancel.IsVisible = true;


			courseAddButton.IsVisible = true;
			courseDeleteButton.IsVisible = true;

			edit.IsVisible = false;


		}

		void onSaveButtonClicked(object sender, EventArgs e)
		{
			save.IsVisible = false;
			cancel.IsVisible = false;



			courseAddButton.IsVisible = false;
			courseDeleteButton.IsVisible = false;

			edit.IsVisible = true;

		}
		void onCancelButtonClicked(object sender, EventArgs e)
		{
			save.IsVisible = false;
			cancel.IsVisible = false;


			courseAddButton.IsVisible = false;
			courseDeleteButton.IsVisible = false;

			edit.IsVisible = true;

		}




		void addIntersetButtonClicked(object sender, EventArgs e)
		{




		}



		void addCourseButtonClicked(object sender, EventArgs e)
		{



		}

		void deleteButtonClicked(object sender, EventArgs e)
		{




		}









		void createInterestStack(String textInterest, String fileImage, int index)
		{
			Image addImage = new Image
			{
				Source = new FileImageSource
				{
					File = fileImage
				},

				HeightRequest = 30

			};

			Label label = new Label
			{
				Text = textInterest,
				FontSize = Device.GetNamedSize(NamedSize.Small, typeof(Label)),
			
				HorizontalOptions = LayoutOptions.StartAndExpand
			};

			Button deleteButton = new Button
			{
				Text = "-",
				ClassId = index + "",

				HeightRequest = 15,

				BorderColor = Color.Black,
				BorderWidth = 1,
			};
			deleteButton.Clicked += deleteButtonClicked;



			StackLayout stack = new StackLayout
			{
				Orientation = StackOrientation.Horizontal,
				Children = {
						addImage,
						label,
					deleteButton
					}
			};


			interestCourseStack.Children.Add(stack);


		}




	}
}
