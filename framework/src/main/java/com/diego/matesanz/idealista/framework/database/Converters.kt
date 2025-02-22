package com.diego.matesanz.idealista.framework.database

import android.util.Log
import androidx.room.TypeConverter
import com.diego.matesanz.idealista.framework.database.entities.ProductItemEntity
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromPriceInfo(value: ProductItemEntity.PriceInfo): String = Gson().toJson(value)

    @TypeConverter
    fun toPriceInfo(value: String): ProductItemEntity.PriceInfo {
        return try {
            Gson().fromJson<ProductItemEntity.PriceInfo>(value)
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Error converting string to ProductItemEntity.PriceInfo", e)
            ProductItemEntity.PriceInfo(
                price = ProductItemEntity.PriceInfo.Price(
                    amount = 0.0,
                    currencySuffix = ""
                )
            )
        }
    }

    @TypeConverter
    fun fromMultimedia(value: ProductItemEntity.Multimedia): String = Gson().toJson(value)

    @TypeConverter
    fun toMultimedia(value: String): ProductItemEntity.Multimedia {
        return try {
            Gson().fromJson<ProductItemEntity.Multimedia>(value)
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Error converting string to ProductItemEntity.PriceInfo", e)
            ProductItemEntity.Multimedia(
                images = emptyList()
            )
        }
    }

    @TypeConverter
    fun fromFeatures(value: ProductItemEntity.Features): String = Gson().toJson(value)

    @TypeConverter
    fun toFeatures(value: String): ProductItemEntity.Features {
        return try {
            Gson().fromJson<ProductItemEntity.Features>(value)
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Error converting string to ProductItemEntity.PriceInfo", e)
            ProductItemEntity.Features(
                hasAirConditioning = false,
                hasBoxRoom = false,
                hasSwimmingPool = false,
                hasTerrace = false,
                hasGarden = false
            )
        }
    }

    @TypeConverter
    fun fromParkingSpace(value: ProductItemEntity.ParkingSpace): String = Gson().toJson(value)

    @TypeConverter
    fun toParkingSpace(value: String): ProductItemEntity.ParkingSpace {
        return try {
            Gson().fromJson<ProductItemEntity.ParkingSpace>(value)
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Error converting string to ProductItemEntity.PriceInfo", e)
            ProductItemEntity.ParkingSpace(
                hasParkingSpace = false,
                isParkingSpaceIncludedInPrice = false
            )
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)
}
