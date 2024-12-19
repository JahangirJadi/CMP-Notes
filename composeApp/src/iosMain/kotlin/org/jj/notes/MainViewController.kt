package org.jj.notes

import androidx.compose.ui.window.ComposeUIViewController
import org.jj.di.initKoin
import org.jj.notes.app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }