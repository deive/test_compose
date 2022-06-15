package uk.rigly.deive.testcompose

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.ktor.client.call.*
import io.ktor.client.request.*
import uk.rigly.deive.testcompose.address.details.Address
import uk.rigly.deive.testcompose.address.details.AddressScreen
import uk.rigly.deive.testcompose.address.list.AddressItemsScreen
import uk.rigly.deive.testcompose.theme.TestComposeTheme
import java.nio.ByteBuffer
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    TestComposeTheme {
        val navController = rememberAnimatedNavController()
        val viewModel: MainViewModel = viewModel()
        val addresses by viewModel.db.addressDao().getAll().collectAsState(initial = emptyList())
        LaunchedEffect(true) {
            if (addresses.isEmpty()) {
                val response = viewModel.httpClient
                    .get("https://random-data-api.com/api/address/random_address?size=20")
                    .body<List<Address>>()
                viewModel.db.addressDao().insertAll(response)
            }
        }
        AnimatedNavHost(
            navController,
            "list") {
            composable("list",
                enterTransition = {
                    when (initialState.destination.route) {
                        "detail/{id}" ->
                            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                        else -> EnterTransition.None
                    }
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                }) {
                AddressItemsScreen(addresses) {
                    navController.navigate("detail/${it.uuid}")
                }
            }
            composable("detail/{id}",
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }) {  entry ->
                argument("id") {
                    type = UUIDType
                }
                val uuid = UUID.fromString(entry.arguments?.get("id") as String)
                AddressScreen(uuid) {
                    navController.popBackStack()
                }
            }
        }
    }
}

val UUIDType: NavType<UUID?> = object : NavType<UUID?>(true) {
    override val name: String
        get() = "UUID"

    override fun put(bundle: Bundle, key: String, value: UUID?) {
        bundle.putByteArray(key, value?.let {
            ByteBuffer.allocate(16)
                .putLong(it.mostSignificantBits)
                .putLong(it.leastSignificantBits)
                .array()
        })
    }

    @Suppress("DEPRECATION")
    override fun get(bundle: Bundle, key: String): UUID? {
        val bytes = bundle.getByteArray(key) ?: return null
        val buf = ByteBuffer.wrap(bytes)
        return UUID(buf.long, buf.long)
    }

    override fun parseValue(value: String): UUID {
        return UUID.fromString(value)
    }
}
