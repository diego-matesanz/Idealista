package com.diego.matesanz.idealista.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diego.matesanz.idealista.framework.database.entities.ProductItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao {

    @Query("SELECT * FROM ProductItemEntity")
    fun getSavedProducts(): Flow<List<ProductItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductItemEntity)

    @Query("DELETE FROM ProductItemEntity WHERE propertyCode = :propertyCode")
    suspend fun deleteProduct(propertyCode: String)
}
