package com.example.movieguideapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieguideapp.ui.MainActivity

open class NavigationViewModel : ViewModel() {

    protected val navigationCommandStream: MutableLiveData<NavigationCommand> = MutableLiveData()

    fun onBackPressed() {
        navigationCommandStream.value = NavigationCommand.BackPress
    }

    fun getNavigationCommandStream(): LiveData<NavigationCommand> {
        return navigationCommandStream
    }

    fun handleNavigationCommand(navigationCommand: NavigationCommand?) {
        navigationCommand?.let {
            navigationCommandStream.value = navigationCommand
        }
    }

    fun clearNavigationCommand() {
        navigationCommandStream.value = null
    }

    override fun onCleared() {
        navigationCommandStream.value = null
    }

    fun logout() {
        navigationCommandStream.value = NavigationCommand.StartActivity(
            activityClass = MainActivity::class,
            finishCurrentOne = true,
            flags = NavigationCommand.StartActivity.FLAG_NEW_CLEAR_TASK
        )
    }
}
