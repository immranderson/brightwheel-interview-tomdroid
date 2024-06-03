package com.tomdroid.interview.brightwheelinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tomdroid.interview.brightwheel.common_ui.components.GithubRepoAccordionComponent
import com.tomdroid.interview.brightwheel.common_ui.themes.BrightwheelInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainVM: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrightwheelInterviewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {

                        val viewState by mainVM.viewStateFlow().collectAsState()

                        if (viewState.loading) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                        } else {

                            val displayItems = viewState.content

                            LazyColumn {
                                items(displayItems) {
                                    GithubRepoAccordionComponent(
                                        fullRepoName = it.repoName,
                                        topContributor = it.topContributor,
                                        onFirstExpansion = {
                                            mainVM.onItemExpanded(
                                                ownerId = it.repoOwner,
                                                repoId = it.repoName
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


