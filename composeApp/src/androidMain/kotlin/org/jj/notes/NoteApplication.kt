package org.jj.notes

import android.app.Application
import org.jj.di.initKoin
import org.koin.android.ext.koin.androidContext

class NoteApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidContext(this@NoteApplication)
        }

    }
}