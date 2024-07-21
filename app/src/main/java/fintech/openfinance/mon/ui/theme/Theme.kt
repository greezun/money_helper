package fintech.openfinance.mon.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = MainDarkGray,
    secondary = MainLightGray,
    tertiary = MainGreen1,
    onPrimary = Color.White,
    surface = MainDarkGray,
    onSurface = Color.White,
    primaryContainer = MainLightGray,
    onPrimaryContainer = Color.White,
    background = MainDarkGray


)

@Composable
fun ElgatoFinanceTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}