package fintech.openfinance.mon.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintech.openfinance.mon.data.models.FinCategory
import fintech.openfinance.mon.ui.theme.ElgatoFinanceTheme
import fintech.openfinance.mon.ui.theme.MainYellow

@Composable
fun CategoryView(
    name: String = "name",
    categoryList: List<FinCategory> = emptyList(),
    onClick:(FinCategory)->Unit={},
    onPlus:(String) ->Unit = {}
) {
    val amount = categoryList.sumOf { it.amount }

    Card (
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {


        Row ( modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            ,
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                ,
                text = name.uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                ,
                text = amount.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MainYellow,

                )


        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categoryList.size) { index ->
                CategoryItem(item = categoryList[index]){
                    onClick(categoryList[index])
                }
            }
            item {
                CategoryItemPlus{
                    onPlus(name)
                }
            }
        }

    }


}

@Preview
@Composable
private fun PrevCategory() {
    ElgatoFinanceTheme {
        CategoryView()
    }
}