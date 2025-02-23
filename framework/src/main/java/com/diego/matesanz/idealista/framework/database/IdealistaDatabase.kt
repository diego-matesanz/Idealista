package com.diego.matesanz.idealista.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diego.matesanz.idealista.framework.database.dao.ProductItemDao
import com.diego.matesanz.idealista.framework.database.entities.ProductItemEntity

@Database(
    entities = [ProductItemEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
internal abstract class IdealistaDatabase: RoomDatabase() {
    abstract fun productItemDao(): ProductItemDao
}
