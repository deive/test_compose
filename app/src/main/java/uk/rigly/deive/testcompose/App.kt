package uk.rigly.deive.testcompose

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import uk.rigly.deive.testcompose.address.details.AddressScreen
import uk.rigly.deive.testcompose.address.list.AddressItemsScreen
import uk.rigly.deive.testcompose.theme.TestComposeTheme
import java.nio.ByteBuffer
import java.util.*

/** Main App - main navigation wrapped in the theme. */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    TestComposeTheme {
        val navController = rememberAnimatedNavController()
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
                AddressItemsScreen {
                    navController.navigate("detail/${it.id}")
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

/** Allows UUID typed navigation. */
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
