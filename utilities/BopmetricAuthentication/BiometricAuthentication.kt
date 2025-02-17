package org.odk.collect.utilities

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager

fun showBiometricPrompt(
    context: Context,
    onAuthenticationSuccess: () -> Unit,
    onAuthenticationFailed: () -> Unit
) {
    val executor = ContextCompat.getMainExecutor(context)

    val biometricPrompt = androidx.biometric.BiometricPrompt(
        (context as AppCompatActivity),
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onAuthenticationSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onAuthenticationFailed()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onAuthenticationFailed()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Authentication")
        .setSubtitle("Authenticate to continue")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt.authenticate(promptInfo)
}


fun isBiometricAvailable(context: Context): Boolean {
    val biometricManager = BiometricManager.from(context)
    return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
        BiometricManager.BIOMETRIC_SUCCESS -> true
        else -> false
    }
}

class BiometricAuthentication{
    companion object{
        var requireBiometric = true;
    }
}

