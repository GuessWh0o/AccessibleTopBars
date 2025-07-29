package com.maxim.accessibletopbars

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.view.accessibility.AccessibilityManager

/**
 * https://stackoverflow.com/a/59950182/5801511
 * combined with
 * https://stackoverflow.com/a/12362545/5801511
 */
internal fun Context.isScreenReaderOn(): Boolean {
    (getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager)?.run {
        if (isEnabled && isTouchExplorationEnabled) {
            val serviceInfoList =
                getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
            return serviceInfoList.isNotEmpty()
        }
    }

    return false
}