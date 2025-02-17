package org.odk.collect.utilities

import android.os.Build
import java.io.File

object RootDetection {
    fun isDeviceRooted(): Boolean {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3()
    }

    private fun checkRootMethod1(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/system/xbin/su",
            "/system/bin/su",
            "/system/sbin/su",
            "/sbin/su",
            "/vendor/bin/su"
        )
        return paths.any { File(it).exists() }
    }

    private fun checkRootMethod2(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod3(): Boolean {
        return try {
            Runtime.getRuntime().exec("which su").inputStream.bufferedReader().readLine() != null
        } catch (e: Exception) {
            false
        }
    }
}
