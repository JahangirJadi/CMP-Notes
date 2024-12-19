package org.jj.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.jj.notes.data.database.DatabaseFactory
import org.jj.notes.data.database.NoteRepositoryImpl
import org.jj.notes.data.database.NotesDatabase
import org.jj.notes.domain.NotesRepository
import org.jj.notes.presentation.NotesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module


val sharedModules = module {

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<NotesDatabase>().notesDao
    }

    singleOf(::NoteRepositoryImpl).bind<NotesRepository>()
    viewModelOf(::NotesViewModel)
}