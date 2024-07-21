package fintech.openfinance.mon.parts

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun CurrencyDialog(
    item: String,
    onDismiss: () -> Unit = {},
    onResult: (String) -> Unit = {}

) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        CurrencyChoicer(
            item,
            onDismiss
        ) { result ->
            onResult(result)
            onDismiss()
        }
    }


}