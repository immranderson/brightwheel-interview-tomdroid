package com.tomdroid.interview.brightwheelinterview

import androidx.lifecycle.ViewModel
import com.tomdroid.interview.brightwheel.common_data.remote.RepoService
import com.tomdroid.interview.brightwheel.common_data.remote.SearchService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    val repoService: RepoService,
    val searchService: SearchService
): ViewModel() {

}