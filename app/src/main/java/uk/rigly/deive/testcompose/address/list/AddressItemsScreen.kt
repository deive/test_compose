package uk.rigly.deive.testcompose.address.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.rigly.deive.testcompose.R
import uk.rigly.deive.testcompose.theme.TestComposeTheme

/** Main, list of addresses, screen. */
@Composable
fun AddressItemsScreen(onClick: (AddressItem) -> Unit) {
    AddressItemsScreenScaffold {
        AddressItemsStateHolder { addresses ->
            AddressItemsList(addresses, onClick)
        }
    }
}

/** Main, screen scaffolding. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddressItemsScreenScaffold(
    content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressItemsScreenPreview() {
    TestComposeTheme {
        AddressItemsScreenScaffold {
            AddressItemsList(testAddressList) {}
        }
    }
}
