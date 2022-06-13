@file:UseSerializers(UUIDAsStringSerializer::class)
package uk.rigly.deive.testcompose.address.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import uk.rigly.deive.testcompose.UUIDAsStringSerializer
import uk.rigly.deive.testcompose.address.AddressImage
import java.util.*

@Serializable
data class Address(
    val id: Long,
    val uuid: UUID,
    override val latitude: Double,
    override val longitude: Double,

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
) : AddressImage

@Composable
fun Address(item: Address) {
    val viewWidth = 300.dp
    val viewHeight = 200.dp
    AddressImage(item, viewWidth, viewHeight)
}
