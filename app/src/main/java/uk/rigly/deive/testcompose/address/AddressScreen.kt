package uk.rigly.deive.testcompose.address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(uuid: UUID) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Address...") },
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)) {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(text = uuid.toString())
            }
        }
    }
}