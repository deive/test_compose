package uk.rigly.deive.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uk.rigly.deive.testcompose.address.list.AddressItemList
import uk.rigly.deive.testcompose.address.list.testAddressList
import uk.rigly.deive.testcompose.theme.TestComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        AddressItemList(
            testAddressList
        ) {}
    }
}
