package com.diego.matesanz.idealista.framework.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailResponse(
    @SerialName("adid") val adid: Int,
    @SerialName("price") val price: Double,
    @SerialName("priceInfo") val priceInfo: PriceInfo,
    @SerialName("operation") val operation: String,
    @SerialName("propertyType") val propertyType: String,
    @SerialName("extendedPropertyType") val extendedPropertyType: String,
    @SerialName("homeType") val homeType: String,
    @SerialName("state") val state: String,
    @SerialName("multimedia") val multimedia: Multimedia,
    @SerialName("propertyComment") val propertyComment: String,
    @SerialName("ubication") val ubication: Ubication,
    @SerialName("country") val country: String,
    @SerialName("moreCharacteristics") val moreCharacteristics: MoreCharacteristics,
    @SerialName("energyCertification") val energyCertification: EnergyCertification
) {
    @Serializable
    data class PriceInfo(
        @SerialName("amount") val amount: Double,
        @SerialName("currencySuffix") val currencySuffix: String
    )

    @Serializable
    data class Multimedia(
        @SerialName("images") val images: List<Image>
    ) {
        @Serializable
        data class Image(
            @SerialName("url") val url: String,
            @SerialName("tag") val tag: String,
            @SerialName("localizedName") val localizedName: String,
            @SerialName("multimediaId") val multimediaId: Int
        )
    }

    @Serializable
    data class Ubication(
        @SerialName("latitude") val latitude: Double,
        @SerialName("longitude") val longitude: Double
    )

    @Serializable
    data class MoreCharacteristics(
        @SerialName("communityCosts") val communityCosts: Double,
        @SerialName("roomNumber") val roomNumber: Int,
        @SerialName("bathNumber") val bathNumber: Int,
        @SerialName("exterior") val exterior: Boolean,
        @SerialName("housingFurnitures") val housingFurnitures: String,
        @SerialName("agencyIsABank") val agencyIsABank: Boolean,
        @SerialName("energyCertificationType") val energyCertificationType: String,
        @SerialName("flatLocation") val flatLocation: String,
        @SerialName("modificationDate") val modificationDate: Long,
        @SerialName("constructedArea") val constructedArea: Int,
        @SerialName("lift") val lift: Boolean,
        @SerialName("boxroom") val boxroom: Boolean,
        @SerialName("isDuplex") val isDuplex: Boolean,
        @SerialName("floor") val floor: String,
        @SerialName("status") val status: String
    )

    @Serializable
    data class EnergyCertification(
        @SerialName("title") val title: String,
        @SerialName("energyConsumption") val energyConsumption: EnergyConsumption,
        @SerialName("emissions") val emissions: Emissions
    ) {
        @Serializable
        data class EnergyConsumption(
            @SerialName("type") val type: String
        )

        @Serializable
        data class Emissions(
            @SerialName("type") val type: String
        )
    }
}
