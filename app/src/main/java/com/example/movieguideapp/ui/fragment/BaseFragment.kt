package com.example.movieguideapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.movieguideapp.base.common.error.ErrorHandler
import com.example.movieguideapp.base.extension.initNavigator
import com.example.movieguideapp.ui.viewmodel.BaseViewModel
import com.example.movieguideapp.ui.viewmodel.NavigationViewModel
import com.freshly.meal.ui.common.navigation.Navigator
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
    private val navigator: Navigator by inject()
    protected fun initErrorHandler(viewModel: BaseViewModel) {
        ErrorHandler(requireActivity(), viewLifecycleOwner, viewModel.errorLiveData) {
            viewModel.clearError()
        }
    }

    protected fun initNavigator(navigationViewModel: NavigationViewModel) {
        initNavigator(navigator, navigationViewModel)
    }
}