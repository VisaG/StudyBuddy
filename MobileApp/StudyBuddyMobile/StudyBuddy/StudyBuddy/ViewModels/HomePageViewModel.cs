using System;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Collections.ObjectModel;
using System.Linq;

using StudyBuddy.Models;
using StudyBuddy;

namespace StudyBuddy.ViewModels
{




	public class HomePageViewModel: INotifyPropertyChanged
	{
		public event PropertyChangedEventHandler PropertyChanged;

		public bool isRamya { get; set; }
		public bool isVisa { get; set; }

		public ObservableCollection<Group> Groups { get; set; }

		public ICommand AcceptCommand { protected set; get; }

		public HomePageViewModel()
		{



			if (Settings.Default.currentUser.firstName == "Ramya")
			{
				isRamya = true;
				isVisa = false;
			}
			else
			{
				isRamya = false;
				isVisa = true;
			}




			Groups = new ObservableCollection<Group>(DataUtil.getGroupAllDetails());

		}

	}
}
