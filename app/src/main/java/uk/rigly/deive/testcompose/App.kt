package uk.rigly.deive.testcompose

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.rigly.deive.testcompose.address.list.AddressItemsScreen
import uk.rigly.deive.testcompose.address.details.AddressScreen
import uk.rigly.deive.testcompose.theme.TestComposeTheme
import java.nio.ByteBuffer
import java.util.*

@Composable
fun App() {
    TestComposeTheme {
        val navController = rememberNavController()
        NavHost(
            navController,
            "list") {
            composable("list") {
                AddressItemsScreen {
                    navController.navigate("detail/${it.uuid}")
                }
            }
            composable("detail/{id}") {  entry ->
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
