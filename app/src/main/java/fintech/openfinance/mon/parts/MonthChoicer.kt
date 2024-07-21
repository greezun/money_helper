package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.data.months
import fintech.openfinance.mon.ui.theme.MainYellow

@Composable
fun MonthChoicer(
    currentKey:String,
    onDismiss:()->Unit,
    onChoice:(String) ->Unit

) {

    val monthKeys = remember { months.keys.toList() }
    var currentChoice by remember {
        mutableStateOf(currentKey)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(monthKeys.size) { index ->

                TextButton(onClick = {
                    onChoice(monthKeys[index])
                    currentChoice = monthKeys[index]
                    onDismiss()
                },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if(currentKey == monthKeys[index]) MainYellow else Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RectangleShape
                ) {
                    Text(
                        text = monthKeys[index].uppercase(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,


                    )
                }
            }
        }
    }
}