package com.freshly.meal.ui.common.navigation

/* ktlint-disable no-wildcard-imports */

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movieguideapp.R
import com.example.movieguideapp.ui.viewmodel.NavigationCommand
import com.example.movieguideapp.ui.viewmodel.NavigationCommand.*
import com.example.movieguideapp.ui.viewmodel.NavigationViewModel


class Navigator {

	private lateinit var fragmentManager: FragmentManager
	var navigationViewModel: NavigationViewModel? = null
	private var hostActivity: FragmentActivity? = null
	private var parentFragment: Fragment? = null
	private var fragmentContainer: Int = R.id.container

	fun init(
		activity: FragmentActivity?,
		fragmentManager: FragmentManager,
		lifecycleOwner: LifecycleOwner,
		navigationViewModel: NavigationViewModel,
		fragment: Fragment? = null,
		fragmentContainer: Int = R.id.container
	) {
		this.fragmentManager = fragmentManager
		this.navigationViewModel = navigationViewModel
		this.hostActivity = activity
		this.parentFragment = fragment
		this.fragmentContainer = fragmentContainer

		registerToNavigationStream(navigationViewModel, lifecycleOwner)
	}

	private fun registerToNavigationStream(
		navigationViewModel: NavigationViewModel,
		lifecycleOwner: LifecycleOwner
	) {
		navigationViewModel.getNavigationCommandStream().observe(
			lifecycleOwner,
			Observer { command ->
				if (command == null) return@Observer

				handleCommand(command)

				navigationViewModel.clearNavigationCommand()
			})
	}

	private fun handleCommand(command: NavigationCommand) {

		when (command) {
			is Batch -> batch(command)
			is StartActivity -> startNewActivity(command)
			is ShowFragment -> showFragment(command)
			is ShowTopLevelFragment -> showTopLevelFragment(command)
			is PopBackStackInGraph -> popBackStackInGraph(command)
			is BackPress -> backPress()
			is FinishCurrentActivity -> finishCurrentActivity(command)
			is OpenUrl -> openUrl(command)
			is SendEmail -> sendEmail(command)
			is MakeDial -> makeDial(command)
			is Share -> share(command)
		}
	}

	private fun batch(command: Batch) {
		command.innerCommands.forEach { handleCommand(it) }
	}


	private fun startNewActivity(command: StartActivity) {
		val intent = Intent(hostActivity, command.activityClass.java)
		val bundle = Bundle()//buildActivityAnimation(command.animation).toBundle()
		command.arguments?.let {
			intent.putExtras(it)
		}
		command.flags?.let {
			intent.setFlags(it)
		}
		if (command.requestCode != null) {
			if (parentFragment != null) {
				parentFragment?.startActivityForResult(intent, command.requestCode, bundle)
			} else {
				hostActivity?.startActivityForResult(intent, command.requestCode, bundle)
			}
		} else {
			hostActivity?.startActivity(intent, bundle)
		}
		if (command.finishCurrentOne) {
			hostActivity?.finish()
		}
	}

	private fun showFragment(command: ShowFragment) {
		parentFragment?.findNavController()?.navigate(command.actionId, command.args)
	}

	private fun showTopLevelFragment(command: ShowTopLevelFragment) {
		val navController = hostActivity?.findNavController(fragmentContainer) ?: return
		if (command.resId == navController.currentDestination?.id) return

		val navOptionsBuilder = NavOptions.Builder()
			.setLaunchSingleTop(true)
			.setEnterAnim(R.anim.nav_default_enter_anim)
			.setExitAnim(R.anim.nav_default_exit_anim)
			.setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
			.setPopExitAnim(R.anim.nav_default_pop_exit_anim)
			.setPopUpTo(navController.graph.startDestination, false)

		navController.navigate(command.resId, command.args, navOptionsBuilder.build())
	}

	private fun popBackStackInGraph(command: PopBackStackInGraph) {
		when {
			fragmentManager.backStackEntryCount == 0 && command.finishActivityOnLastBackStackEntry -> {
				hostActivity?.finish()
			}

			command.destinationId != null -> {
				parentFragment?.findNavController()
					?.popBackStack(command.destinationId, command.inclusive)
			}

			else -> parentFragment?.findNavController()?.popBackStack()
		}
	}

	private fun backPress() {
		hostActivity?.onBackPressed()
	}

	private fun finishCurrentActivity(command: FinishCurrentActivity) {
		command.result?.let { result ->
			val data = command.arguments?.let { args ->
				Intent().apply { putExtras(args) }
			}
			hostActivity?.setResult(result, data)
		}
		hostActivity?.finish()
	}

	private fun openUrl(command: OpenUrl) {
		/*if (DeepLinkHandler.canBeRedirectedToWeb(command.url)) {
			hostActivity?.openUrlOutsideApp(command.url) // exclude app from intent handlers to avoid infinite loop
		} else {
			hostActivity?.browse(command.url)
		}*/
	}

	private fun sendEmail(command: SendEmail) {
		//hostActivity?.email(command.email, command.subject, command.text)
	}

	private fun makeDial(command: MakeDial) {
		//hostActivity?.makeDial(command.number)
	}

	private fun share(command: Share) {
		//hostActivity?.share(command.text)
	}
}
