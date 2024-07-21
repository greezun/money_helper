package fintech.openfinance.mon.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import fintech.openfinance.mon.data.models.FinCategory

@Database(entities = [FinCategory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun finCategoryDao(): FinDao
}