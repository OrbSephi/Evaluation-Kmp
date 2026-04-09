package com.example.eval_d3_p3

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eval_d3_p3.extensions.showShortToast
import com.example.eval_d3_p3.platform.PlatformContext
import com.example.eval_d3_p3.platform.SoundManager
import com.example.eval_d3_p3.presentation.locationdetail.LocationDetailRoute
import com.example.eval_d3_p3.presentation.locationlist.LocationListRoute
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

@Composable
fun AndroidApp() {
    MaterialTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        val soundManager: SoundManager = remember(context) {
            // Pass the Android Context through PlatformContext for expect/actual APIs.
            GlobalContext.get().get { parametersOf(PlatformContext(context)) }
        }

        NavHost(
            navController = navController,
            startDestination = "location_list",
        ) {
            composable("location_list") {
                LocationListRoute(
                    onLocationClick = { id ->
                        soundManager.playClick()
                        context.showShortToast("Location #$id")
                        navController.navigate("location_detail/$id")
                    },
                )
            }
            composable(
                route = "location_detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                LocationDetailRoute(
                    locationId = id,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
