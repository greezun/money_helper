package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fintech.openfinance.mon.ui.theme.MainGreen1
import fintech.openfinance.mon.ui.theme.MainRed

@Composable
fun AcceptButtons(
    onDismiss: () -> Unit,
    onApply: () -> Unit,
) {
    Row {


        Box(modifier = Modifier
            .clickable { onDismiss() }
            .size(60.dp)
            .background(MainRed, CircleShape)
            .border(width = 2.dp, color = Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(40.dp))

        Box(modifier = Modifier
            .clickable {
                onApply()
            }
            .size(60.dp)
            .background(MainGreen1, CircleShape)
            .border(width = 2.dp, color = Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Done,
                contentDescription = null,
                tint = Color.White
            )
        }

    }
}