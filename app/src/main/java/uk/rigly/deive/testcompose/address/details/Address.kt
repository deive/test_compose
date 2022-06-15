@file:UseSerializers(UUIDAsStringSerializer::class)
package uk.rigly.deive.testcompose.address.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseSerializers
import uk.rigly.deive.testcompose.UUIDAsStringSerializer
import uk.rigly.deive.testcompose.address.AddressImage
import java.util.*

@Entity
@Serializable
data class Address(
    @Transient
    @PrimaryKey
    val id: Long? = null,
    @SerialName("uid")
    val uuid: UUID,
    override val latitude: Double,
    override val longitude: Double,

    val secondaryAddress: String? = null,
    val buildingNumber: String? = null,
    val streetName: String? = null,
    val streetAddress: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postcode: String? = null,

    val mailBox: String? = null,
    val community: String? = null,
    val zipCode: String? = null,
    val zip: String? = null,
    val timeZone: String? = null,
    val streetSuffix: String? = null,
    val citySuffix: String? = null,
    val cityPrefix: String? = null,
    val stateAbbr: String? = null,
    val countryCode: String? = null,
    val fullAddress: String? = null,
) : AddressImage

@Composable
fun Address(item: Address) {
    val viewWidth = 300.dp
    val viewHeight = 200.dp
    AddressImage(item, viewWidth, viewHeight)
}
