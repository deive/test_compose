package uk.rigly.deive.testcompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.AndroidViewModel
import uk.rigly.deive.testcompose.address.list.AddressItemsList
import uk.rigly.deive.testcompose.address.list.testAddressList
import uk.rigly.deive.testcompose.theme.TestComposeTheme

/** Single activity for compose app. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

/** ViewModel to link app to compose. */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val db by lazy { (application as AndroidApp).db }
    val httpClient by lazy { (application as AndroidApp).httpClient }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        AddressItemsList(
            testAddressList
        ) {}
    }
}
