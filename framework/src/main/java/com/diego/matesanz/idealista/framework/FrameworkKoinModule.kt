package com.diego.matesanz.idealista.framework

import androidx.room.Room
import com.diego.matesanz.idealista.framework.database.IdealistaDatabase
import com.diego.matesanz.idealista.framework.remote.ProductsClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan
class FrameworkKoinModule

val frameworkKoinModule = module {
    single { Room.databaseBuilder(get(), IdealistaDatabase::class.java, "idealista-db").build() }
    factory { get<IdealistaDatabase>().productItemDao() }
    single { ProductsClient.instance }
}
