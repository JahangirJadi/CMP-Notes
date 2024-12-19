package org.jj.di

import org.jj.notes.data.database.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single {
            DatabaseFactory(androidApplication())
        }
    }