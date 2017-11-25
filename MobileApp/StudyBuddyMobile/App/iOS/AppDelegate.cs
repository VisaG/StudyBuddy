using System;
using System.Collections.Generic;
using System.Linq;
using SQLitePCL;
using Foundation;
using UIKit;

namespace StudyBuddy.iOS
{
	[Register("AppDelegate")]
	public partial class AppDelegate : global::Xamarin.Forms.Platform.iOS.FormsApplicationDelegate
	{
		public override bool FinishedLaunching(UIApplication app, NSDictionary options)
		{


			Microsoft.WindowsAzure.MobileServices.CurrentPlatform.Init();
			SQLitePCL.Batteries.Init();

			global::Xamarin.Forms.Forms.Init();

			LoadApplication(new App());

			return base.FinishedLaunching(app, options);
		}
	}
}
