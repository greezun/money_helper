package fintech.openfinance.mon.parts

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun MonthChoiceDialog(
    currentKey:String,
    onDismiss:()->Unit,
    onChoice:(String) ->Unit
) {

    Dialog(onDismissRequest = onDismiss) {
        MonthChoicer(currentKey = currentKey,
            onDismiss = onDismiss) {
            onChoice(it)
        }
    }

}