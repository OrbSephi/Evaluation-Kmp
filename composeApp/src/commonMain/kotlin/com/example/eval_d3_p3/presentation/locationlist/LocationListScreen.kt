package com.example.eval_d3_p3.presentation.locationlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun LocationListRoute(
    onLocationClick: (Int) -> Unit = {},
    viewModel: LocationListViewModel = remember {
        GlobalContext.get().get<LocationListViewModel>()
    },
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLocations()
    }

    LocationListScreen(
        state = state,
        onIntent = viewModel::onIntent,
        onLocationClick = onLocationClick,
    )
}

@Composable
fun LocationListScreen(
    state: LocationListUiState,
    onIntent: (LocationListIntent) -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    // UI is fully driven by state to keep it stateless.
    when {
        state.isLoading -> {
            LoadingState()
        }

        state.errorMessage != null -> {
            ErrorState(
                message = state.errorMessage,
                onRetry = { onIntent(LocationListIntent.Retry) },
            )
        }

        else -> {
            LocationList(
                locations = state.locations,
                onRefresh = { onIntent(LocationListIntent.Refresh) },
                onLocationClick = onLocationClick,
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
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
private fun LocationList(
    locations: List<Location>,
    onRefresh: () -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onRefresh, modifier = Modifier.padding(16.dp)) {
            Text("Refresh")
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(locations, key = { it.id }) { location ->
                LocationListItem(
                    location = location,
                    onClick = { onLocationClick(location.id) },
                )
            }
        }
    }
}

@Composable
private fun LocationListItem(
    location: Location,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier.clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = location.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Type: ${location.type}")
            Text(text = "Dimension: ${location.dimension}")
            Text(text = "Residents: ${location.residentCount}")
        }
    }
}
