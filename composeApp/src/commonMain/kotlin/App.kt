import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import detail.DetailScreen
import home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class Screen {
    Home, Detail
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var currentScreen by remember { mutableStateOf(Screen.Home) }
            var selectedId by remember { mutableStateOf("") }
            when (currentScreen) {
                Screen.Home -> {
                    HomeScreen {
                        currentScreen = Screen.Detail
                        selectedId = it.idMeal
                    }
                }

                Screen.Detail -> {
                    DetailScreen(
                        id = selectedId,
                        navigateBack = {
                            currentScreen = Screen.Home
                            selectedId = ""
                        }
                    )
                }

            }
        }
    }
}