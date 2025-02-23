package com.diego.matesanz.idealista.framework.remote.mappers

import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.framework.remote.models.ProductDetailResponse

internal fun ProductDetailResponse.toDomainModel(): ProductDetail =
    ProductDetail(
        adid = adid,
        price = price,
        priceInfo = priceInfo.toDomainModel(),
        operation = operation,
        propertyType = propertyType,
        extendedPropertyType = extendedPropertyType,
        homeType = homeType,
        state = state,
        multimedia = multimedia.toDomainModel(),
        propertyComment = propertyComment,
        ubication = ubication.toDomainModel(),
        country = country,
        moreCharacteristics = moreCharacteristics.toDomainModel(),
        energyCertification = energyCertification.toDomainModel(),
    )

private fun ProductDetailResponse.PriceInfo.toDomainModel(): ProductDetail.PriceInfo =
    ProductDetail.PriceInfo(
        amount = amount,
        currencySuffix = currencySuffix,
    )

private fun ProductDetailResponse.Multimedia.toDomainModel(): ProductDetail.Multimedia =
    ProductDetail.Multimedia(
        images = images.map { it.toDomainModel() },
    )

private fun ProductDetailResponse.Multimedia.Image.toDomainModel(): ProductDetail.Multimedia.Image =
    ProductDetail.Multimedia.Image(
        url = url,
        tag = tag,
        localizedName = localizedName,
        multimediaId = multimediaId,
    )

private fun ProductDetailResponse.Ubication.toDomainModel(): ProductDetail.Ubication =
    ProductDetail.Ubication(
        latitude = latitude,
        longitude = longitude,
    )

private fun ProductDetailResponse.MoreCharacteristics.toDomainModel(): ProductDetail.MoreCharacteristics =
    ProductDetail.MoreCharacteristics(
        communityCosts = communityCosts,
        roomNumber = roomNumber,
        bathNumber = bathNumber,
        exterior = exterior,
        housingFurnitures = housingFurnitures,
        agencyIsABank = agencyIsABank,
        energyCertificationType = energyCertificationType,
        flatLocation = flatLocation,
        modificationDate = modificationDate,
        constructedArea = constructedArea,
        lift = lift,
        boxroom = boxroom,
        isDuplex = isDuplex,
        floor = floor,
        status = status,
    )

private fun ProductDetailResponse.EnergyCertification.toDomainModel(): ProductDetail.EnergyCertification =
    ProductDetail.EnergyCertification(
        title = title,
        energyConsumption = energyConsumption.toDomainModel(),
        emissions = emissions.toDomainModel(),
    )

private fun ProductDetailResponse.EnergyCertification.EnergyConsumption.toDomainModel(): ProductDetail.EnergyCertification.EnergyConsumption =
    ProductDetail.EnergyCertification.EnergyConsumption(
        type = type,
    )

private fun ProductDetailResponse.EnergyCertification.Emissions.toDomainModel(): ProductDetail.EnergyCertification.Emissions =
    ProductDetail.EnergyCertification.Emissions(
        type = type,
    )
