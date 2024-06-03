package com.tomdroid.interview.brightwheelinterview

import SearchRepositoriesResponseEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomdroid.interview.brightwheel.common_data.remote.ContributorListResponseEntityItem
import com.tomdroid.interview.brightwheel.common_data.remote.RepoService
import com.tomdroid.interview.brightwheel.common_data.remote.SearchService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val repoService: RepoService,
    private val searchService: SearchService
): ViewModel() {

    private val _viewStateFlow = MutableStateFlow(
        ViewState(
            loading = true,
            content = emptyList()
        )
    )
    fun viewStateFlow(): StateFlow<ViewState> = _viewStateFlow.asStateFlow()

    init {
        fetchTopRepositoriesToDisplay()
    }

    private fun fetchTopRepositoriesToDisplay() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                fetchTopRepositoriesFlow()
                    .collect { response ->

                        val displayContent = response.items.map {
                            RepoDisplayItem(
                                repoName = it.name,
                                repoOwner = it.owner.login
                            )
                        }

                        _viewStateFlow.value = ViewState(
                            loading = false,
                            content = displayContent
                        )
                    }

            }
        }
    }
    private fun fetchTopRepositoriesFlow(): Flow<SearchRepositoriesResponseEntity> {
        return flow {
            emit(
                searchService.searchRepositories(
                    query = "stars:>0"
                )
            )
        }
    }

    private fun fetchContributorListFlow(
        ownerId: String,
        repoId: String
    ): Flow<List<ContributorListResponseEntityItem>> {
        return flow {
            val contributorsList = repoService.getContributorsList(
                ownerId = ownerId,
                repoId = repoId
            )

            emit(contributorsList)
        }
    }

    fun onItemExpanded(
        ownerId: String,
        repoId: String
    ) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                fetchContributorListFlow(
                    ownerId = ownerId,
                    repoId = repoId
                ).collect { contributorListResponse ->
                    val oldViewState = _viewStateFlow.value
                    val oldContentState = oldViewState.content

                    val newContentState = oldContentState.map { oldRepoDisplayItem ->
                        if (oldRepoDisplayItem.repoOwner == ownerId && oldRepoDisplayItem.repoName == repoId) {
                            RepoDisplayItem(
                                repoName = oldRepoDisplayItem.repoName,
                                repoOwner = oldRepoDisplayItem.repoOwner,
                                topContributor = contributorListResponse.first().login ?: "No Top Contributor"
                            )
                        } else {
                            oldRepoDisplayItem
                        }
                    }

                    val newViewState = ViewState(
                        content = newContentState
                    )

                    _viewStateFlow.value = newViewState

                }

            }
        }
    }

    fun onSearchPressed() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val searchRepositoriesResponse: SearchRepositoriesResponseEntity = searchService.searchRepositories(
                    query = "stars:>0"
                )

                println(searchRepositoriesResponse)
            }
        }
    }

    data class ViewState(
        val error: String? = null,
        val empty: Boolean = false,
        val loading: Boolean = false,
        val content: List<RepoDisplayItem>
    )


    data class RepoDisplayItem (
        val repoName: String,
        val repoOwner: String,
        val topContributor: String? = null
    )

}