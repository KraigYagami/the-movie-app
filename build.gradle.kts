// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
//    alias(libs.plugins.io.gitlab.arturbosch.detekt)
    alias(libs.plugins.hilt.android.plugin) apply false
    alias(libs.plugins.com.google.gms.google.services) apply false
}
true // Needed to make the Suppress annotation work for the plugins block
