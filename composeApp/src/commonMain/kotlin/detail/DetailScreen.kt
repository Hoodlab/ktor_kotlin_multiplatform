package detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import ktoryttutorial.composeapp.generated.resources.Res
import ktoryttutorial.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    val detailState by detailViewModel.detailState.collectAsState()
    LaunchedEffect(true) {
        detailViewModel.fetchMealById(id)
    }
    Column(modifier) {
        IconButton(onClick = navigateBack) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, null)
        }
        if (detailState.isLoading) {
            CircularProgressIndicator()
        }
        if (detailState.error != null) {
            Text(detailState.error.orEmpty())
        }
        LazyColumn {
            item {
                detailState.meals?.let { meal ->
                    AsyncImage(
                        model = meal.strMealThumb,
                        modifier = Modifier.fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        error = painterResource(Res.drawable.compose_multiplatform),
                        onError = { state -> state.result.throwable.printStackTrace() }
                    )
                    Spacer(Modifier.height(16.dp))
                    Surface(
                        color = MaterialTheme.colors.surface.copy(
                            .5f
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = meal.strMeal,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(meal.strInstructions, style = MaterialTheme.typography.body1)
                    Spacer(Modifier.height(4.dp))
                    Text(meal.strArea, style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }

}