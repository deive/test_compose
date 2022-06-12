package uk.rigly.deive.testcompose.address

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.rigly.deive.testcompose.ui.theme.TestComposeTheme
import java.util.*

data class AddressItem(
    val id: Long,
    val uuid: UUID,
    override val latitude: Double,
    override val longitude: Double,

    val city: String,
    val state: String,
) : AddressImage

@Composable
fun AddressItemList(data: List<AddressItem>, onClick: (AddressItem) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data.size) { index ->
            AddressItem(data[index], onClick)
        }
    }
}

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
fun DefaultPreview() {
    TestComposeTheme {
        AddressItemList(
            testAddressList
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