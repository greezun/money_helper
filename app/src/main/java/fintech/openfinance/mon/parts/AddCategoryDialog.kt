package fintech.openfinance.mon.parts

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import fintech.openfinance.mon.data.models.Category
import fintech.openfinance.mon.data.models.FinCategory

@Composable
fun AddCategoryDialog(
    category: Category,
    month: String,
    onDismiss: () -> Unit = {},
    onResult: (FinCategory) -> Unit = {}
) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {

        AddCategory(
            category,
            month,
            onDismiss
        ) { result ->
            onResult(result)
            onDismiss()
        }


    }

}