package com.diego.matesanz.idealista.framework.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemResponse(
    @SerialName("propertyCode") val propertyCode: String,
    @SerialName("thumbnail") val thumbnail: String,
    @SerialName("floor") val floor: String,
    @SerialName("price") val price: Double,
    @SerialName("priceInfo") val priceInfo: PriceInfo,
    @SerialName("propertyType") val propertyType: String,
    @SerialName("operation") val operation: String,
    @SerialName("size") val size: Double,
    @SerialName("exterior") val exterior: Boolean,
    @SerialName("rooms") val rooms: Int,
    @SerialName("bathrooms") val bathrooms: Int,
    @SerialName("address") val address: String,
    @SerialName("province") val province: String,
    @SerialName("municipality") val municipality: String,
    @SerialName("district") val district: String,
    @SerialName("country") val country: String,
    @SerialName("neighborhood") val neighborhood: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("description") val description: String,
    @SerialName("multimedia") val multimedia: Multimedia,
    @SerialName("features") val features: Features,
    @SerialName("parkingSpace") val parkingSpace: ParkingSpace? = null,
) {
    @Serializable
    data class PriceInfo(
        @SerialName("price") val price: Price,
    ) {
        @Serializable
        data class Price(
            @SerialName("amount") val amount: Double,
            @SerialName("currencySuffix") val currencySuffix: String,
        )
    }

    @Serializable
    data class Multimedia(
        @SerialName("images") val images: List<Image>,
    ) {
        @Serializable
        data class Image(
            @SerialName("url") val url: String,
            @SerialName("tag") val tag: String,
        )
    }

    @Serializable
    data class Features(
        @SerialName("hasAirConditioning") val hasAirConditioning: Boolean,
        @SerialName("hasBoxRoom") val hasBoxRoom: Boolean,
        @SerialName("hasSwimmingPool") val hasSwimmingPool: Boolean? = null,
        @SerialName("hasTerrace") val hasTerrace: Boolean? = null,
        @SerialName("hasGarden") val hasGarden: Boolean? = null,
    )

    @Serializable
    data class ParkingSpace(
        @SerialName("hasParkingSpace") val hasParkingSpace: Boolean,
        @SerialName("isParkingSpaceIncludedInPrice") val isParkingSpaceIncludedInPrice: Boolean,
    )
}
