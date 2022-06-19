package uk.rigly.deive.testcompose.address.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.rigly.deive.testcompose.BasicScaffold
import java.util.*

/** Details screen for an address. */
@Composable
fun AddressScreen(uuid: UUID, onUp: () -> Unit) {
    BasicScaffold("Address...", onUp) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = uuid.toString())
        }
    }
}
