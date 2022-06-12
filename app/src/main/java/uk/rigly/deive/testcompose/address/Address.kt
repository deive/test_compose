@file:UseSerializers(UUIDAsStringSerializer::class)
package uk.rigly.deive.testcompose.address

import java.util.*
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.Serializable
import uk.rigly.deive.testcompose.UUIDAsStringSerializer

@Serializable
data class Address(
    val id: Long,
    val uid: UUID,
    val latitude: Double,
    val longitude: Double,

    val secondaryAddress: String,
    val buildingNumber: String,
    val streetName: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,

    val mailBox: String,
    val community: String,
    val zipCode: String,
    val zip: String,
    val timeZone: String,
    val streetSuffix: String,
    val citySuffix: String,
    val cityPrefix: String,
    val stateAbbr: String,
    val countryCode: String,
    val fullAddress: String,
)