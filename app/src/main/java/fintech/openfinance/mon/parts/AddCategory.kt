package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import fintech.openfinance.mon.data.budgetArticleColors
import fintech.openfinance.mon.data.models.Category
import fintech.openfinance.mon.data.models.FinCategory
import fintech.openfinance.mon.ui.theme.ElgatoFinanceTheme


@Composable
fun AddCategory(
    category: Category = Category.INCOME,
    month: String,
    onDismiss: () -> Unit = {},
    onChoice: (FinCategory) -> Unit = {}
) {

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    var currentName by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "новая категория".uppercase(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00A651),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        LazyRow(

            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),

            ) {
            items(budgetArticleColors.size) { index ->

                val borderColor =
                    if (currentIndex == index) Color.White else budgetArticleColors[index]
                Box(modifier = Modifier
                    .clickable { currentIndex = index }
                    .size(60.dp)
                    .background(budgetArticleColors[index], CircleShape)
                    .border(width = 2.dp, color = borderColor, CircleShape)
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))



        OutlinedTextField(
            value = currentName, onValueChange = { currentName = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        AcceptButtons(
            onDismiss = onDismiss,
            onApply = {
                onChoice(
                    FinCategory(
                        id = 0,
                        color = budgetArticleColors[currentIndex].value.toLong(),
                        amount = 0,
                        description = currentName,
                        category = category,
                        month = month

                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))


    }
}


@Preview
@Composable
private fun Preview() {
    ElgatoFinanceTheme {
        AddCategory(month = "янв")
    }

}

