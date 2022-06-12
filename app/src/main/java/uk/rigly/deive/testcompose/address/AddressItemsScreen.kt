package uk.rigly.deive.testcompose.address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.rigly.deive.testcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressItemsScreen(onClick: (AddressItem) -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)) {
            AddressItemList(testAddressList, onClick)
        }
    }
}