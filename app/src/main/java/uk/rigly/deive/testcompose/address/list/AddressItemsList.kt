package uk.rigly.deive.testcompose.address.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.rigly.deive.testcompose.theme.TestComposeTheme

@Composable
fun AddressItemsList(data: List<AddressItem>, onClick: (AddressItem) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data.size) { index ->
            AddressItem(data[index], onClick)
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun AddressItemsListPreview() {
    TestComposeTheme {
        AddressItemsList(
            testAddressList
        ) {}
    }
}
