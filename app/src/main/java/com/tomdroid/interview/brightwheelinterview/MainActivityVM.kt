package com.tomdroid.interview.brightwheelinterview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomdroid.interview.brightwheel.common_data.remote.RepoService
import com.tomdroid.interview.brightwheel.common_data.remote.SearchService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val repoService: RepoService,
    private val searchService: SearchService
): ViewModel() {

    fun onSearchPressed() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val searchRepositoriesResponse = searchService.searchRepositories(
                    query = "stars:>0"
                )

                println(searchRepositoriesResponse)
            }
        }
    }

}