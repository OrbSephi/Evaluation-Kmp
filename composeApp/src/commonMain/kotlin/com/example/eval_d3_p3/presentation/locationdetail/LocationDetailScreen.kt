package com.example.eval_d3_p3.presentation.locationdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eval_d3_p3.domain.model.Location
import org.koin.core.context.GlobalContext

@Composable
fun LocationDetailRoute(
    locationId: Int,
    onBack: (() -> Unit)? = null,
    viewModel: LocationDetailViewModel = remember {
        GlobalContext.get().get<LocationDetailViewModel>()
    },
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.loadLocation(locationId)
    }

    LocationDetailScreen(
        state = state,
        onIntent = viewModel::onIntent,
        onBack = onBack,
    )
}

@Composable
fun LocationDetailScreen(
    state: LocationDetailUiState,
    onIntent: (LocationDetailIntent) -> Unit,
    onBack: (() -> Unit)? = null,
) {
    // UI is fully driven by state to keep it stateless.
    when {
        state.isLoading -> {
            DetailLoadingState()
        }

        state.errorMessage != null -> {
            DetailErrorState(
                message = state.errorMessage,
                onRetry = { onIntent(LocationDetailIntent.Retry) },
            )
        }

        state.location != null -> {
            LocationDetailContent(
                location = state.location,
                onRefresh = { onIntent(LocationDetailIntent.Refresh) },
                onBack = onBack,
            )
        }

        else -> {
            DetailEmptyState()
        }
    }
}

@Composable
private fun DetailLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DetailErrorState(
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
        Button(onClick = onRetry, modifier = Modifier.padding(top = 12.dp)) {
            Text("Retry")
        }
    }
}

@Composable
private fun DetailEmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Select a location", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun LocationDetailContent(
    location: Location,
    onRefresh: () -> Unit,
    onBack: (() -> Unit)? = null,
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (onBack != null) {
            Button(onClick = onBack) {
                Text("Back")
            }
        }
        Card(modifier = Modifier.padding(top = 12.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = location.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "Type: ${location.type}")
                Text(text = "Dimension: ${location.dimension}")
                Text(text = "Residents: ${location.residentCount}")
            }
        }
        Button(onClick = onRefresh, modifier = Modifier.padding(top = 12.dp)) {
            Text("Refresh")
        }
    }
}
