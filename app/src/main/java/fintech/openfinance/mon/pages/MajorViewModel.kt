package fintech.openfinance.mon.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fintech.openfinance.mon.data.models.Category
import fintech.openfinance.mon.data.models.FinCategory
import fintech.openfinance.mon.data.room.FinDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MajorViewModel @Inject constructor(private val dao: FinDao):ViewModel() {


    private val _categoriesList = MutableStateFlow<List<List<FinCategory>>>(emptyList())
    val categoriesList = _categoriesList.asStateFlow()


    private fun update(month:String){
        viewModelScope.launch(Dispatchers.IO){
            val categories: MutableList<List<FinCategory>> = mutableListOf()
            for (category in Category.entries) {
                val list = dao.getCategoriesByMonthAndCategory(month, category)
                categories.add(list)
            }
            _categoriesList.emit(categories)
        }
    }

    fun init(month: String){
        update(month)
    }

    fun add(category: FinCategory){
        viewModelScope.launch (Dispatchers.IO){
            dao.insert(category)
            update(category.month)
        }
    }

}