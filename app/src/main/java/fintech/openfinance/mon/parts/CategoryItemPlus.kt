package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.ui.theme.MainGreen1

@Composable
fun CategoryItemPlus(
    onClick:() -> Unit = {}
) {

    Column (modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box (modifier = Modifier
            .size(60.dp)
            .background(MainGreen1, CircleShape)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Add, contentDescription = null )
        }
        Spacer(modifier = Modifier.height(2.dp))

        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 11.sp,
            textAlign = TextAlign.Center
        )

    }

}

