package uk.rigly.deive.testcompose.address.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.rigly.deive.testcompose.address.AddressImage
import uk.rigly.deive.testcompose.theme.TestComposeTheme
import java.util.*

data class AddressItem(
    val id: Long,
    val uuid: UUID,
    override val latitude: Double,
    override val longitude: Double,

    val city: String,
    val state: String,
) : AddressImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressItem(item: AddressItem, onClick: (AddressItem) -> Unit) {
    val viewWidth = 100.dp
    val viewHeight = 100.dp
    Card(
        modifier = Modifier.clickable { onClick(item) }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .weight(1f)
                .height(viewHeight)
                .padding(horizontal = 10.dp),
                contentAlignment = Alignment.CenterStart) {
                Column {
                    Text(text = item.city)
                    Text(text = item.state)
                }
            }
            AddressImage(item, viewWidth, viewHeight)
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun AddressItemPreview() {
    TestComposeTheme {
        AddressItem(
            testAddressList.first()
        ) {}
    }
}

// From https://random-data-api.com/api/address/random_address
val testAddressList = listOf(
    AddressItem(1, UUID.randomUUID(),
        47.52466, -122.31339,
        "South Edmondbury",
        "New Mexico",
    ),
    AddressItem(2, UUID.randomUUID(),
        -17.214461752228942, -169.19780435633243,
        "South Edmondbury",
        "New Mexico",
    ),
    AddressItem(3, UUID.randomUUID(),
        31.459245777705362, 108.48339700069033,
        "Lanechester",
        "Texas",
    ),
    AddressItem(4, UUID.randomUUID(),
        0.0, 0.0,
        "North Olimpia",
        "Kansas",
    ),
    AddressItem(5, UUID.randomUUID(),
        47.52466, 122.31339,
        "East Elliott",
        "Delaware",
    ),
    AddressItem(6, UUID.randomUUID(),
        122.31339, 47.52466,
        "South Collinfurt",
        "Colorado",
    ),
)
