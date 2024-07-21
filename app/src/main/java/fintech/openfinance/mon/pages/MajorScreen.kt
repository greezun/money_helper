package fintech.openfinance.mon.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fintech.openfinance.mon.data.getCurrentMonthShort
import fintech.openfinance.mon.data.getPreference
import fintech.openfinance.mon.data.models.Category
import fintech.openfinance.mon.data.models.FinCategory
import fintech.openfinance.mon.data.months
import fintech.openfinance.mon.data.savePreference
import fintech.openfinance.mon.parts.AddCategoryDialog
import fintech.openfinance.mon.parts.CalculatorDialog
import fintech.openfinance.mon.parts.CategoryView
import fintech.openfinance.mon.parts.CurrencyDialog
import fintech.openfinance.mon.parts.Dialogs
import fintech.openfinance.mon.parts.MonthChoiceDialog


@Composable
fun MajorScreen(
    modifier: Modifier = Modifier,
    viewModel: MajorViewModel = hiltViewModel()

) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var currentDialog by remember { mutableStateOf(Dialogs.CALC) }
    var currentCategory by remember { mutableStateOf(Category.INCOME) }
    var currentItem by remember {
        mutableStateOf<FinCategory?>(null)
    }

    var currentCurrency by remember { mutableStateOf(getPreference(context, "key") ?: "usd") }
    var currentMonth by remember { mutableStateOf(getCurrentMonthShort()) }

    val categoryLists by viewModel.categoriesList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init(currentMonth)

    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,

        ) {

        Column(modifier = Modifier.fillMaxSize(),

        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(modifier = Modifier.width(60.dp))

                Button(
                    onClick = {
                        currentDialog = Dialogs.MONTH
                        showDialog = true

                    },
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A651))
                ) {
                    Text(
                        text = months.getValue(currentMonth).uppercase(),
                        color = Color.White,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 18.sp
                    )
                }

                TextButton(onClick = {
                    currentDialog = Dialogs.CURRENCY
                    showDialog = true
                }) {
                    Text(
                        currentCurrency.uppercase(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    item {
                        CategoryView(
                            name = "ПРИБЫЛЬ",
                            categoryList = if (categoryLists.isNotEmpty()) categoryLists[0] else emptyList(),
                            onClick = {
                                currentItem = it
                                currentDialog = Dialogs.CALC
                                showDialog = true
                            }
                        ) {
                            currentCategory = Category.INCOME
                            currentDialog = Dialogs.ADD
                            showDialog = true
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }


                    item {
                        CategoryView(
                            name = "РАСХОДЫ",
                            categoryList = if (categoryLists.isNotEmpty()) categoryLists[1] else emptyList(),
                            onClick = {
                                currentItem = it
                                currentDialog = Dialogs.CALC
                                showDialog = true
                            }
                        ) {
                            currentCategory = Category.EXPENSE
                            currentDialog = Dialogs.ADD
                            showDialog = true
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                }
            }
        }
    }


    if (showDialog) {
        when (currentDialog) {
            Dialogs.CALC -> CalculatorDialog(
                currentItem!!,
                onDismiss = { showDialog = false }
            ) {
                viewModel.add(it)
            }

            Dialogs.CURRENCY -> CurrencyDialog(
                item = currentCurrency,
                onDismiss = { showDialog = false },
                onResult = {
                    currentCurrency = it
                    savePreference(context, "key", it)
                }
            )

            Dialogs.MONTH -> MonthChoiceDialog(currentKey = currentMonth,
                onDismiss = { showDialog = false }) {
                currentMonth = it
                viewModel.init(it)

            }

            Dialogs.ADD -> {
                AddCategoryDialog(
                    category = currentCategory,
                    month = currentMonth,
                    onDismiss = { showDialog = false }
                ) {
                    viewModel.add(it)
                }
            }
        }
    }
}


