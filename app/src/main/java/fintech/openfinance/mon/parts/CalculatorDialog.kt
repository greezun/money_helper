package fintech.openfinance.mon.parts

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import fintech.openfinance.mon.data.models.FinCategory

@Composable

fun CalculatorDialog(
    item: FinCategory,
    onDismiss: () -> Unit = {},
    onResult: (FinCategory) -> Unit = {}

) {
    Dialog(
        onDismissRequest = onDismiss,

        ) {
        Calc(
            item,
            onDismiss
        ) { result ->
            onResult(result)
            onDismiss()
        }


    }
}