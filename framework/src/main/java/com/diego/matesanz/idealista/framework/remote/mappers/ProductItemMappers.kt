package com.diego.matesanz.idealista.framework.remote.mappers

import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.remote.models.ProductItemResponse

internal fun ProductItemResponse.toDomainModel(): ProductItem =
    ProductItem(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo.toDomainModel(),
        propertyType = propertyType,
        operation = operation,
        size = size,
        exterior = exterior,
        rooms = rooms,
        bathrooms = bathrooms,
        address = address,
        province = province,
        municipality = municipality,
        district = district,
        country = country,
        neighborhood = neighborhood,
        latitude = latitude,
        longitude = longitude,
        description = description,
        multimedia = multimedia.toDomainModel(),
        features = features.toDomainModel(),
        parkingSpace = parkingSpace.toDomainModel(),
    )

private fun ProductItemResponse.PriceInfo.toDomainModel(): ProductItem.PriceInfo =
    ProductItem.PriceInfo(
        price = price.toDomainModel(),
    )

private fun ProductItemResponse.PriceInfo.Price.toDomainModel(): ProductItem.PriceInfo.Price =
    ProductItem.PriceInfo.Price(
        amount = amount,
        currencySuffix = currencySuffix,
    )

private fun ProductItemResponse.Multimedia.toDomainModel(): ProductItem.Multimedia =
    ProductItem.Multimedia(
        images = images.map { it.toDomainModel() },
    )

private fun ProductItemResponse.Multimedia.Image.toDomainModel(): ProductItem.Multimedia.Image =
    ProductItem.Multimedia.Image(
        url = url,
        tag = tag,
    )

private fun ProductItemResponse.Features.toDomainModel(): ProductItem.Features =
    ProductItem.Features(
        hasAirConditioning = hasAirConditioning,
        hasBoxRoom = hasBoxRoom,
        hasSwimmingPool = hasSwimmingPool,
        hasTerrace = hasTerrace,
        hasGarden = hasGarden,
    )

private fun ProductItemResponse.ParkingSpace.toDomainModel(): ProductItem.ParkingSpace =
    ProductItem.ParkingSpace(
        hasParkingSpace = hasParkingSpace,
        isParkingSpaceIncludedInPrice = isParkingSpaceIncludedInPrice,
    )
