package fintech.openfinance.mon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import fintech.openfinance.mon.pages.PageManager
import fintech.openfinance.mon.ui.theme.ElgatoFinanceTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElgatoFinanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   PageManager(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

