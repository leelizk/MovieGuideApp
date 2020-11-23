package com.example.movieguideapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.movieguideapp.base.common.error.ErrorHandler
import com.example.movieguideapp.ui.viewmodel.BaseViewModel

abstract class BaseFragment : Fragment() {
    protected fun initErrorHandler(viewModel: BaseViewModel) {
        ErrorHandler(requireActivity(), viewLifecycleOwner, viewModel.errorLiveData) {
            viewModel.clearError()
        }
    }
}