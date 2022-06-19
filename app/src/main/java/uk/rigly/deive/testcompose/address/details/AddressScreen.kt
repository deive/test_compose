package uk.rigly.deive.testcompose.address.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uk.rigly.deive.testcompose.BasicScaffold
import uk.rigly.deive.testcompose.R
import java.util.*

/** Details screen for an address. */
@Composable
fun AddressScreen(uuid: UUID, onUp: () -> Unit) {
    BasicScaffold(stringResource(id = R.string.address_details_title), onUp) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            AddressStateHolder(uuid) { address ->
                if (Address.Empty == address) {
                    Text(text = "Loading")
                } else if (address == null) {
                    Text(text = "No address")
                } else {
                    Address(address)
                }
            }
        }
    }
}
