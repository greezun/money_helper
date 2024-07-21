package fintech.openfinance.mon.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fintech.openfinance.mon.data.room.AppDatabase
import fintech.openfinance.mon.data.room.FinDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "fin_category_db"
        ).build()
    }

    @Provides
    fun provideFinCategoryDao(database: AppDatabase): FinDao {
        return database.finCategoryDao()
    }
}