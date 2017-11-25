using Xamarin.Forms;

using AppServiceHelpers;
using AppServiceHelpers.Abstractions;
using AppServiceHelpers.Utils;

namespace StudyBuddy.Views
{
	public partial class MainPage : TabbedPage
	{
		public MainPage(EasyMobileServiceClient client)
		{
			InitializeComponent();
			this.BindingContext = new ViewModels.MainPageViewModel(client);

		}
	}
}
