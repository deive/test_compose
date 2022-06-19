package uk.rigly.deive.testcompose.address

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uk.rigly.deive.testcompose.R

/** Provides enough information about an address to draw an AddressImage() */
interface AddressImage {
    val latitude: Double
    val longitude: Double
}

/** An image (from geoapify.com) for an address. */
@Composable
fun AddressImage(address: AddressImage, viewWidth: Dp, viewHeight: Dp) {
    val (imageWidth, imageHeight) = with(LocalDensity.current) {
        Pair(
            viewWidth.toPx().toInt(),
            viewHeight.toPx().toInt()
        )
    }
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://maps.geoapify.com/v1/staticmap?style=osm-carto&width=$imageWidth&height=$imageHeight&center=lonlat:${address.longitude},${address.latitude}&zoom=14&marker=lonlat:${address.longitude},${address.latitude};color:%23ff0000;size:medium&apiKey=45f4fbe35eef4b038a7169478e4932f2")
            .error(drawableResId = R.drawable.ic_baseline_error_outline_24)
            .placeholder(drawableResId = R.drawable.ic_baseline_downloading_24)
            .size(imageWidth, imageHeight)
            .build(),
        contentDescription = null,
        modifier = Modifier.size(viewWidth, viewHeight),
    )
}
