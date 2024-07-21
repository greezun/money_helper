package fintech.openfinance.mon.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fintech.openfinance.mon.data.models.Category
import fintech.openfinance.mon.data.models.FinCategory

@Dao
interface FinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(finCategory: FinCategory)

    @Query("SELECT * FROM fin_category WHERE month = :month AND category = :category")
    suspend fun getCategoriesByMonthAndCategory(month: String, category: Category): List<FinCategory>
}