package uk.rigly.deive.testcompose.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uk.rigly.deive.testcompose.R
import uk.rigly.deive.testcompose.ui.theme.TestComposeTheme

data class AddressItem(
    val id: Long,
    val latitude: Double,
    val longitude: Double,

    val city: String,
    val state: String,
)

@Composable
fun AddressItemList(data: List<AddressItem>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data.size) { index ->
            AddressItem(data[index])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressItem(item: AddressItem) {
    val viewWidth = 100.dp
    val viewHeight = 100.dp
    val (imageWidth, imageHeight) = with(LocalDensity.current) { Pair(
        viewWidth.toPx().toInt(),
        viewHeight.toPx().toInt()
    )}
    Card {
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://maps.geoapify.com/v1/staticmap?style=osm-carto&width=$imageWidth&height=$imageHeight&center=lonlat:${item.longitude},${item.latitude}&zoom=14&marker=lonlat:${item.longitude},${item.latitude};color:%23ff0000;size:medium&apiKey=45f4fbe35eef4b038a7169478e4932f2")
                    .error(drawableResId = R.drawable.ic_baseline_error_outline_24)
                    .placeholder(drawableResId = R.drawable.ic_baseline_downloading_24)
                    .size(imageWidth, imageHeight)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(viewWidth, viewHeight),
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        AddressItemList(
            testAddressList()
        )
    }
}

// From https://random-data-api.com/api/address/random_address
fun testAddressList() = listOf(
    AddressItem(1, 47.52466, -122.31339,
        "South Edmondbury",
        "New Mexico",
    ),
    AddressItem(2, -17.214461752228942, -169.19780435633243,
        "South Edmondbury",
        "New Mexico",
    ),
    AddressItem(2, 31.459245777705362, 108.48339700069033,
        "Lanechester",
        "Texas",
    ),
    AddressItem(2, 0.0, 0.0,
        "North Olimpia",
        "Kansas",
    ),
    AddressItem(1, 47.52466, 122.31339,
        "East Elliott",
        "Delaware",
    ),
    AddressItem(2, 122.31339, 47.52466,
        "South Collinfurt",
        "Colorado",
    ),
)