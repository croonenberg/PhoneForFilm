package com.example.phoneforfilm.utils

import android.app.Activity
import com.example.phoneforfilm.R

object ThemeManager {
    fun applyTheme(activity: Activity, key: String) {
        val themeRes = when (key) {
            "Greenroom"    -> R.style.Theme_Greenroom
            "BlueStage"    -> R.style.Theme_BlueStage
            "GreyCard"     -> R.style.Theme_GreyCard
            "NeutralLight" -> R.style.Theme_NeutralLight
            "Darkroom"     -> R.style.Theme_Darkroom
            else           -> R.style.Theme_NeutralLight
        }
        activity.setTheme(themeRes)
    }
}
