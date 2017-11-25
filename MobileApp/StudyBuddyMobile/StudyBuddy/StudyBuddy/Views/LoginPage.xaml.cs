using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using StudyBuddy.Models;
using System.Threading;


using Xamarin.Forms;



namespace StudyBuddy.Views
{
	public partial class LoginPage : ContentPage
	{
		
		static Student currentStudent = null;


		public LoginPage()
		{
			InitializeComponent();

			// Start the animation going.
			AnimationLoop();
		}

		public async Task DelayAsync(int delay)
		{
			await Task.Delay(delay);

		}


		async void OnSubmitButtonClicked(object sender, EventArgs args)
		{
			String email = emailEntry.Text;
			String password = passwordEntry.Text;


			infoLabel.Text = "Logging in...";

			await Task.Delay(300);

			if (email != null && password != null)
			{
				if (!email.EndsWith("@scu.edu", StringComparison.Ordinal))
				{
					infoLabel.Text ="Please enter valid email ";
					emailEntry.Text = "";
				}
				else
				{

					DataStudentUtil.email = email;
					currentStudent = DataStudentUtil.getStudentByEmailId();
					DataUtil.stuId = currentStudent.studentId;

				}
				if (currentStudent != null)
				{
					if (password == currentStudent.password)
					{
						infoLabel.Text = "Downloading the data...";

						await Task.Delay(100);

							Settings.Default.currentUser = currentStudent;
		
						Application.Current.MainPage = new MainPage();
					}else
						infoLabel.Text = "Incorrect username or password";
				}
				else
				{

					infoLabel.Text ="No student found ";
					emailEntry.Text = "";
				}
			}
			else
			{
				infoLabel.Text ="Please enter valid username and Password ";
				emailEntry.Text = "";
				passwordEntry.Text = "";
			}
		}

		public static Student getCurrentStudent()
		{
			return currentStudent;
		}


		async void AnimationLoop()
		{
			while (true)
			{
				await Task.WhenAll(img1.FadeTo(0, 5000, Easing.Linear),
								   img2.FadeTo(1, 5000, Easing.Linear));


				await Task.WhenAll(img1.FadeTo(1, 5000, Easing.Linear),
								   img2.FadeTo(0, 5000, Easing.Linear));
			}
		}
	} }


