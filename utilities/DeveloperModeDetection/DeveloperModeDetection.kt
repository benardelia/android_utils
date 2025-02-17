package org.odk.collect.utilities

import android.content.Context
import android.provider.Settings


fun isDeveloperModeEnabled(context: Context): Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
    ) != 0
}
