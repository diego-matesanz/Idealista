package com.diego.matesanz.idealista.framework.database.mappers

import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.database.entities.ProductItemEntity

internal fun ProductItemEntity.toDomainModel(): ProductItem =
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

private fun ProductItemEntity.PriceInfo.toDomainModel(): ProductItem.PriceInfo =
    ProductItem.PriceInfo(
        price = price.toDomainModel(),
    )

private fun ProductItemEntity.PriceInfo.Price.toDomainModel(): ProductItem.PriceInfo.Price =
    ProductItem.PriceInfo.Price(
        amount = amount,
        currencySuffix = currencySuffix,
    )

private fun ProductItemEntity.Multimedia.toDomainModel(): ProductItem.Multimedia =
    ProductItem.Multimedia(
        images = images.map { it.toDomainModel() },
    )

private fun ProductItemEntity.Multimedia.Image.toDomainModel(): ProductItem.Multimedia.Image =
    ProductItem.Multimedia.Image(
        url = url,
        tag = tag,
    )

private fun ProductItemEntity.Features.toDomainModel(): ProductItem.Features =
    ProductItem.Features(
        hasAirConditioning = hasAirConditioning,
        hasBoxRoom = hasBoxRoom,
        hasSwimmingPool = hasSwimmingPool == true,
        hasTerrace = hasTerrace == true,
        hasGarden = hasGarden == true,
    )

private fun ProductItemEntity.ParkingSpace?.toDomainModel(): ProductItem.ParkingSpace =
    ProductItem.ParkingSpace(
        hasParkingSpace = this?.hasParkingSpace == true,
        isParkingSpaceIncludedInPrice = this?.isParkingSpaceIncludedInPrice == true,
    )

internal fun ProductItem.toEntity(): ProductItemEntity =
    ProductItemEntity(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo.toEntity(),
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
        multimedia = multimedia.toEntity(),
        features = features.toEntity(),
        parkingSpace = parkingSpace.toEntity(),
    )

private fun ProductItem.PriceInfo.toEntity(): ProductItemEntity.PriceInfo =
    ProductItemEntity.PriceInfo(
        price = price.toEntity(),
    )

private fun ProductItem.PriceInfo.Price.toEntity(): ProductItemEntity.PriceInfo.Price =
    ProductItemEntity.PriceInfo.Price(
        amount = amount,
        currencySuffix = currencySuffix,
    )

private fun ProductItem.Multimedia.toEntity(): ProductItemEntity.Multimedia =
    ProductItemEntity.Multimedia(
        images = images.map { it.toEntity() },
    )

private fun ProductItem.Multimedia.Image.toEntity(): ProductItemEntity.Multimedia.Image =
    ProductItemEntity.Multimedia.Image(
        url = url,
        tag = tag,
    )

private fun ProductItem.Features.toEntity(): ProductItemEntity.Features =
    ProductItemEntity.Features(
        hasAirConditioning = hasAirConditioning,
        hasBoxRoom = hasBoxRoom,
        hasSwimmingPool = hasSwimmingPool == true,
        hasTerrace = hasTerrace == true,
        hasGarden = hasGarden == true,
    )

private fun ProductItem.ParkingSpace?.toEntity(): ProductItemEntity.ParkingSpace =
    ProductItemEntity.ParkingSpace(
        hasParkingSpace = this?.hasParkingSpace == true,
        isParkingSpaceIncludedInPrice = this?.isParkingSpaceIncludedInPrice == true,
    )
