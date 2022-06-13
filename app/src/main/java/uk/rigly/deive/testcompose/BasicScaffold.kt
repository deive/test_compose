package uk.rigly.deive.testcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import uk.rigly.deive.testcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(title: String, onUp: () -> Unit, content: @Composable BoxScope.() -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onUp) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = "back",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                        )
                    }
                },
                title = { Text(text = title) },
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            content()
        }
    }
}
