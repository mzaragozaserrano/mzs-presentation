package com.mzaragozaserrano.data.datasources.local

import androidx.annotation.StringRes

interface ResourcesDataSource {

    fun getStringFromResource(@StringRes resId: Int): String

    fun getStringOrResource(str: String?, @StringRes resId: Int): String

}