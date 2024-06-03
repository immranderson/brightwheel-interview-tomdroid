package com.tomdroid.interview.brightwheel.common_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepoAccordionComponent(
    fullRepoName: String,
    topContributor: String? = null,
    onFirstExpansion: () -> Unit
) {

    val firstExpansion: MutableState<Boolean> =
        remember {
            mutableStateOf(false)
        }

    val isExpanded: MutableState<Boolean> =
        remember {
            mutableStateOf(false)
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {

            if (!firstExpansion.value) {
                onFirstExpansion.invoke()
                firstExpansion.value = true
            }

            isExpanded.value = !isExpanded.value
        }

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp),
                text = fullRepoName,
                textAlign = TextAlign.Center
            )

            AnimatedVisibility(visible = isExpanded.value) {

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    if (topContributor == null) {
                        CircularProgressIndicator(
                            modifier = Modifier.height(56.dp).width(56.dp)
                                .wrapContentHeight(Alignment.CenterVertically)
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .height(56.dp)
                                .wrapContentHeight(Alignment.CenterVertically)
                                .fillMaxWidth(),
                            text = topContributor,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }
        }
    }
}