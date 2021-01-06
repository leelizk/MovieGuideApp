package com.example.movieguideapp.base.common

import android.os.Parcel
import kotlinx.android.parcel.Parceler
import org.koin.ext.getFullName
import kotlin.reflect.KClass

object KClassParceler : Parceler<KClass<*>> {

    override fun create(parcel: Parcel): KClass<*> = Class.forName(parcel.readString()!!).kotlin

    override fun KClass<*>.write(parcel: Parcel, flags: Int) {
        parcel.writeString(getFullName())
    }
}
