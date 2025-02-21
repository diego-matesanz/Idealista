package com.diego.matesanz.idealista.domain.models

data class ProductDetail(
    val adid: Int,
    val price: Int,
    val priceInfo: PriceInfo,
    val operation: String,
    val propertyType: String,
    val extendedPropertyType: String,
    val homeType: String,
    val state: String,
    val multimedia: Multimedia,
    val propertyComment: String,
    val ubication: Ubication,
    val country: String,
    val moreCharacteristics: MoreCharacteristics,
    val energyCertification: EnergyCertification
) {
    data class PriceInfo(
        val amount: Int,
        val currencySuffix: String
    )

    data class Multimedia(
        val images: List<Image>
    ) {
        data class Image(
            val url: String,
            val tag: String,
            val localizedName: String,
            val multimediaId: Int
        )
    }

    data class Ubication(
        val latitude: Double,
        val longitude: Double
    )

    data class MoreCharacteristics(
        val communityCosts: Int,
        val roomNumber: Int,
        val bathNumber: Int,
        val exterior: Boolean,
        val housingFurnitures: String,
        val agencyIsABank: Boolean,
        val energyCertificationType: String,
        val flatLocation: String,
        val modificationDate: Long,
        val constructedArea: Int,
        val lift: Boolean,
        val boxroom: Boolean,
        val isDuplex: Boolean,
        val floor: String,
        val status: String
    )

    data class EnergyCertification(
        val title: String,
        val energyConsumption: EnergyConsumption,
        val emissions: Emissions
    ) {
        data class EnergyConsumption(
            val type: String
        )

        data class Emissions(
            val type: String
        )
    }
}
