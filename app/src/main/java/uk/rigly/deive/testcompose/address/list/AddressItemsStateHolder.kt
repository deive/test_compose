package uk.rigly.deive.testcompose.address.list

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.map
import uk.rigly.deive.testcompose.MainViewModel
import uk.rigly.deive.testcompose.address.details.Address
import uk.rigly.deive.testcompose.theme.TestComposeTheme

/** Holds and handles address list data. */
@Composable
fun AddressItemsStateHolder(
    viewModel: MainViewModel = viewModel(),
    content: @Composable (addresses: List<AddressItem>) -> Unit
) {
    val firstLoadDoneKey = booleanPreferencesKey("AddressItems.firstLoad")
    val firstLoadDone = viewModel.preferences.data.map {
        it[firstLoadDoneKey] ?: false
    }.collectAsState(initial = true)
    val addresses by viewModel.db.addressDao().getAll().collectAsState(initial = emptyList())
    if (addresses.isEmpty()) {
        if (!firstLoadDone.value) {
            LaunchedEffect(true) {
                if (addresses.isEmpty()) {
                    val response = viewModel.httpClient
                        .get("https://random-data-api.com/api/address/random_address?size=20")
                        .body<List<Address>>()
                    viewModel.db.addressDao().insertAll(response)
                    viewModel.preferences.edit {
                        it[firstLoadDoneKey] = true
                    }
                }
            }
            Text(text = "First load of data from API!")
        } else {
            CircularProgressIndicator()
        }
    } else {
        content(addresses)
    }
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
