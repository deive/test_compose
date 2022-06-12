package uk.rigly.deive.testcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import uk.rigly.deive.testcompose.address.AddressItemList
import uk.rigly.deive.testcompose.address.testAddressList
import uk.rigly.deive.testcompose.ui.theme.TestComposeTheme

@Composable
fun App() {
    TestComposeTheme {
        val navController = rememberNavController()
        NavHost(navController, Screens.AddressItemList.route) {
            addDestination(
                ComposeNavigator.Destination(provider[ComposeNavigator::class]) {
                    ListScreen()
                }.apply { route = Screens.AddressItemList.route }
            )
        }
    }
}

enum class Screens(val route: String) {
    AddressItemList("list")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)) {
            AddressItemList(
                testAddressList()
            )
        }
    }
}