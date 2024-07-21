package fintech.openfinance.mon.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fin_category")
data class FinCategory(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val color:Long,
    val amount: Int,
    val description: String,
    val month:String = "январь",
    val category: Category = Category.INCOME
)
