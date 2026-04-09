package com.example.eval_d3_p3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eval_d3_p3.platform.PlatformContext
import com.example.eval_d3_p3.platform.SoundManager
import com.example.eval_d3_p3.presentation.locationdetail.LocationDetailRoute
import com.example.eval_d3_p3.presentation.locationlist.LocationListRoute
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

@Composable
@Preview
fun DesktopApp() {
    MaterialTheme {
        var selectedLocationId by remember { mutableStateOf<Int?>(null) }
        val soundManager: SoundManager = remember {
            GlobalContext.get().get { parametersOf(PlatformContext(Unit)) }
        }

        Row(
            // Desktop uses a master-detail split for list/detail.
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxSize()) {
                LocationListRoute(
                    onLocationClick = { id ->
                        soundManager.playClick()
                        selectedLocationId = id
                    },
                )
            }

            Box(modifier = Modifier.weight(1f).fillMaxSize()) {
                if (selectedLocationId == null) {
                    Text(
                        text = "Select a location",
                        modifier = Modifier.align(Alignment.Center),
                    )
                } else {
                    LocationDetailRoute(locationId = selectedLocationId!!)
                }
            }
        }
    }
}