package fintech.openfinance.mon.parts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.data.models.FinCategory
import fintech.openfinance.mon.ui.theme.MainRed

@Composable
fun CategoryItem(
    item:FinCategory,
    onClick:(FinCategory) -> Unit = {}
) {

    Log.d("appTAG", "CategoryItem: $item")

    Column (modifier = Modifier.clickable { onClick(item) },
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Box (modifier = Modifier
            .size(60.dp)
            .background(Color(item.color.toULong()), CircleShape)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(text = item.amount.toString(),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(2.dp))

        Spacer(modifier = Modifier.height(2.dp))
        Text(text = item.description,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 11.sp,
            textAlign = TextAlign.Center
        )

    }

}

@Preview
@Composable
private fun Preview() {
    CategoryItem(item = FinCategory (
        id = 0,
        color = MainRed.value.toLong(),
        amount = 1000, description = "category"

    ))
}