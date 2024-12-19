package org.jj.notes

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jj.di.initKoin
import org.jj.notes.app.App

fun main()  {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Notes-App",
        ) {
            App()
        }
    }
}