package uk.rigly.deive.testcompose.address.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.call.*
import io.ktor.client.request.*
import uk.rigly.deive.testcompose.MainViewModel
import uk.rigly.deive.testcompose.address.details.Address
import uk.rigly.deive.testcompose.theme.TestComposeTheme

@Composable
fun AddressItemsStateHolder(
    viewModel: MainViewModel = viewModel(),
    content: @Composable (addresses: List<AddressItem>) -> Unit
) {
    val addresses by viewModel.db.addressDao().getAll().collectAsState(initial = emptyList())
    LaunchedEffect(true) {
        if (addresses.isEmpty()) {
            val response = viewModel.httpClient
                .get("https://random-data-api.com/api/address/random_address?size=20")
                .body<List<Address>>()
            viewModel.db.addressDao().insertAll(response)
        }
    }
    content(addresses)
}

@Preview(showBackground = true)
@Composable
fun AddressItemsStateHolderPreview() {
    TestComposeTheme {
        AddressItemsList(
            testAddressList
        ) {}
    }
}
