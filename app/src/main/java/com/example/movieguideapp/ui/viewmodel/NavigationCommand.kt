package com.example.movieguideapp.ui.viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import com.example.movieguideapp.base.common.KClassParceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.TypeParceler
import kotlin.reflect.KClass

sealed class NavigationCommand : Parcelable {

    @Parcelize
    @TypeParceler<KClass<out Activity>, KClassParceler>()
    data class StartActivity(
        val activityClass: KClass<out Activity>,
        val finishCurrentOne: Boolean = false,
        val arguments: Bundle? = null,
        val flags: Int? = null,
        val requestCode: Int? = null,
        val animation: Animation = Animation.SLIDE
    ) : NavigationCommand() {

        companion object {
            const val FLAG_NEW_CLEAR_TASK =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        enum class Animation {
            SLIDE,
            FADE
        }
    }

    @Parcelize
    data class ShowFragment(
        @IdRes val actionId: Int,
        val args: Bundle? = null
    ) : NavigationCommand() {
        constructor(directions: NavDirections) : this(directions.actionId, directions.arguments)
    }

    @Parcelize
    data class ShowTopLevelFragment(
        @IdRes val resId: Int,
        val args: Bundle? = null
    ) : NavigationCommand()

    @Parcelize
    data class Batch(
        val innerCommands: List<NavigationCommand>
    ) : NavigationCommand()

    @Parcelize
    data class PopBackStackInGraph(
        @IdRes val destinationId: Int? = null,
        val inclusive: Boolean = false,
        val finishActivityOnLastBackStackEntry: Boolean = true
    ) : NavigationCommand()

    @Parcelize
    object BackPress : NavigationCommand()

    @Parcelize
    data class FinishCurrentActivity(
        val result: Int? = null,
        val arguments: Bundle? = null
    ) : NavigationCommand()

    @Parcelize
    data class SendEmail(val email: String, val subject: String = "", val text: String = "") :
        NavigationCommand()

    @Parcelize
    data class OpenUrl(val url: String) : NavigationCommand()

    @Parcelize
    data class MakeDial(val number: String) : NavigationCommand()

    @Parcelize
    data class Share(val text: String) : NavigationCommand()
}
