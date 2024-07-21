package fintech.openfinance.mon.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PageManager(modifier: Modifier = Modifier) {
    val pageManager = rememberNavController()
    NavHost(navController = pageManager, startDestination = "splash"){
        composable("splash"){
            SplashScreen(modifier, pageManager)
        }

        composable("major"){
            MajorScreen(modifier)
        }

    }
}