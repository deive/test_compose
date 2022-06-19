package uk.rigly.deive.testcompose.address.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.rigly.deive.testcompose.MainViewModel
import java.util.*

/** Holds and handles the data for a single address. */
@Composable
fun AddressStateHolder(
    id: UUID,
    viewModel: MainViewModel = viewModel(),
    content: @Composable (address: Address?) -> Unit) {

    val address by viewModel.db.addressDao().getById(id).collectAsState(initial = Address.Empty)
    content(address)
}
