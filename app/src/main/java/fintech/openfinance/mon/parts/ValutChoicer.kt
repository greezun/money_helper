package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.data.currencyList
import fintech.openfinance.mon.ui.theme.ElgatoFinanceTheme
import fintech.openfinance.mon.ui.theme.MainGreen1

@Composable
fun CurrencyChoicer(
    current: String = "USD",
    onDismiss: () -> Unit = {},
    onChoice: (String) -> Unit = {}
) {

    var currentCurrency by remember {
        mutableStateOf(current)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ВАЛЮТА",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00A651),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currencyList.size) { index ->

                TextButton(
                    onClick = { currentCurrency = currencyList[index] },
                    shape = CircleShape,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (currentCurrency == currencyList[index]) MainGreen1 else
                            MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = currencyList[index].uppercase(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,

                        )
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        AcceptButtons(onDismiss = onDismiss) {
            onChoice(currentCurrency)
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}


@Preview
@Composable
private fun Preview() {
    ElgatoFinanceTheme {
        CurrencyChoicer()
    }


}