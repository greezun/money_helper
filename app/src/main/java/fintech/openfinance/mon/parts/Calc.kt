package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.data.models.FinCategory

@Composable
fun Calc(
    item:FinCategory,
    onDismiss:()->Unit  ={},
    onResult: (FinCategory) -> Unit = {}
) {
    var credit by remember { mutableStateOf("") }
    var action by remember { mutableStateOf("+") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "КРЕДИТ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        Text(
            text = buildAnnotatedString {
                append("${item.amount}")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" ")
                    append(action)
                    append(" ")
                    append(credit)

                }
            },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
                .fillMaxWidth()

        )

        Text(
            text = buildAnnotatedString {
                append("в ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(item.description)
                }
            },
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        NumberPad { value ->
            if (value == "+" || value == "-") {
                action = value
            } else {
                if (credit == "0") {
                    credit = value
                } else {
                    credit += value
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AcceptButtons(onDismiss = onDismiss ) {
            onResult(
                if(credit.isNotBlank()) {
                    when (action) {
                        "+" -> item.copy(amount = item.amount + credit.toInt())
                        else -> item.copy(amount = item.amount - credit.toInt())
                    }
                } else {
                    item
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun NumberPad(onNumberClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.weight(3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttons = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9")
            )

            for (row in buttons) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (button in row) {
                        NumberButton(button) { onNumberClick(it) }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val sideButtons = listOf("+", "-", "0")

            for (button in sideButtons) {
                NumberButton(button) { onNumberClick(it) }
            }
        }
    }
}

@Composable
fun NumberButton(number: String, onClick: (String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp)
            .clickable { onClick(number) }
            .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
    ) {
        Text(text = number, fontSize = 28.sp, color = Color.White)
    }
}


