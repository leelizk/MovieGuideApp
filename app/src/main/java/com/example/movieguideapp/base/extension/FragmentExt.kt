package com.example.movieguideapp.base.extension

import androidx.fragment.app.Fragment
import com.example.movieguideapp.ui.viewmodel.NavigationViewModel
import com.freshly.meal.ui.common.navigation.Navigator


fun Fragment.initNavigator(navigator: Navigator, navigationViewModel: NavigationViewModel) {
	navigator.init(
			activity = requireActivity(),
			fragmentManager = parentFragmentManager,
			lifecycleOwner = viewLifecycleOwner,
			navigationViewModel = navigationViewModel,
			fragment = this
	)
}
