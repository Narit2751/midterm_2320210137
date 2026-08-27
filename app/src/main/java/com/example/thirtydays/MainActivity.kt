package com.example.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.thirtydays.data.Ritual
import com.example.thirtydays.data.rituals
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.example.thirtydays.ui.theme.ThirtydaysTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ThirtydaysTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThirtyDaysApp()
                }
            }
        }
    }
}


@Composable
fun ThirtyDaysApp() {

    var expandedDay by remember {
        mutableStateOf<Int?>(null)
    }

    var completedDays by remember {
        mutableStateOf(setOf<Int>())
    }

    var selectedFilter by remember {
        mutableStateOf(R.string.filter_all)
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            ThirtyDaysTopAppBar()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Progress
            ProgressHeader(
                completedCount = completedDays.size,
                totalCount = rituals.size
            )

            // Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                label = {
                    Text(
                        text = stringResource(
                            R.string.search_training_days
                        )
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp
                    )
            )

            // Filter buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 6.dp
                    ),
                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                FilterButton(
                    text = stringResource(
                        R.string.filter_all
                    ),
                    isSelected =
                        selectedFilter ==
                                R.string.filter_all,
                    onClick = {
                        selectedFilter =
                            R.string.filter_all
                    }
                )

                FilterButton(
                    text = stringResource(
                        R.string.filter_completed
                    ),
                    isSelected =
                        selectedFilter ==
                                R.string.filter_completed,
                    onClick = {
                        selectedFilter =
                            R.string.filter_completed
                    }
                )

                FilterButton(
                    text = stringResource(
                        R.string.filter_incomplete
                    ),
                    isSelected =
                        selectedFilter ==
                                R.string.filter_incomplete,
                    onClick = {
                        selectedFilter =
                            R.string.filter_incomplete
                    }
                )
            }

            // Filter status
            val statusFilteredDays =
                rituals.filter { day ->

                    val completed =
                        completedDays.contains(
                            day.dayNumber
                        )

                    when (selectedFilter) {

                        R.string.filter_completed ->
                            completed

                        R.string.filter_incomplete ->
                            !completed

                        else ->
                            true
                    }
                }

            // Search
            val filteredDays =
                statusFilteredDays.filter { day ->

                    val title =
                        stringResource(
                            day.title
                        )

                    val description =
                        stringResource(
                            day.description
                        )

                    val dayText =
                        stringResource(
                            R.string.day_number,
                            day.dayNumber
                        )

                    searchQuery.isBlank() ||
                            title.contains(
                                searchQuery,
                                ignoreCase = true
                            ) ||
                            description.contains(
                                searchQuery,
                                ignoreCase = true
                            ) ||
                            dayText.contains(
                                searchQuery,
                                ignoreCase = true
                            )
                }

            // Training cards
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding =
                    androidx.compose.foundation.layout.PaddingValues(
                        bottom = 24.dp
                    )
            ) {

                items(
                    items = filteredDays,
                    key = {
                        it.dayNumber
                    }
                ) { day ->

                    val isExpanded =
                        expandedDay == day.dayNumber

                    val isCompleted =
                        completedDays.contains(
                            day.dayNumber
                        )

                    RitualDayItem(
                        trainingDay = day,
                        isExpanded = isExpanded,
                        isCompleted = isCompleted,

                        onCardClick = {

                            expandedDay =
                                if (isExpanded) {
                                    null
                                } else {
                                    day.dayNumber
                                }
                        },

                        onCheckedChange = { checked ->

                            completedDays =
                                if (checked) {

                                    completedDays +
                                            day.dayNumber

                                } else {

                                    completedDays -
                                            day.dayNumber
                                }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 7.dp
                            )
                    )
                }
            }
        }
    }
}
@Composable
fun RitualDayItem(
    trainingDay: Ritual,
    isExpanded: Boolean,
    isCompleted: Boolean,
    onCardClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = onCardClick,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            // Use the theme color that gives your previous light-pink card
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            // Day + Checkbox + Expand icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(
                        R.string.day_number,
                        trainingDay.dayNumber
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = isCompleted,
                        onCheckedChange = onCheckedChange,
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            checkmarkColor = MaterialTheme.colorScheme.onPrimary
                        )

                    )

                    Icon(
                        imageVector = if (isExpanded) {
                            Icons.Default.ExpandLess
                        } else {
                            Icons.Default.ExpandMore
                        },
                        contentDescription = stringResource(
                            if (isExpanded) {
                                R.string.collapse
                            } else {
                                R.string.expand
                            }
                        ),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            // Title
            Text(
                text = stringResource(trainingDay.title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Larger image
            Image(
                painter = painterResource(
                    id = trainingDay.imageResourceId
                ),
                contentDescription = stringResource(
                    trainingDay.title
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )


            // Description only when expanded
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column {

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = stringResource(
                            trainingDay.description
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysTopAppBar() {

    CenterAlignedTopAppBar(

        title = {

            Text(
                text = stringResource(
                    R.string.app_name
                ),

                style =
                    MaterialTheme.typography.displayLarge
            )
        }
    )
}


@Composable
fun ProgressHeader(
    completedCount: Int,
    totalCount: Int
) {

    val progress =
        if (totalCount > 0) {
            completedCount.toFloat() /
                    totalCount.toFloat()
        } else {
            0f
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(
                R.string.progress_completed,
                completedCount,
                totalCount
            ),

            style =
                MaterialTheme.typography.labelSmall
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LinearProgressIndicator(
            progress = {
                progress
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
        )
    }
}


@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    FilterChip(
        selected = isSelected,
        onClick = onClick,

        label = {
            Text(
                text = text,
                style =
                    MaterialTheme.typography.labelSmall
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ThirtyDaysPreview() {

    ThirtydaysTheme(
        darkTheme = false
    ) {
        ThirtyDaysApp()
    }
}


@Preview(showBackground = true)
@Composable
fun ThirtyDaysDarkThemePreview() {

    ThirtydaysTheme(
        darkTheme = true
    ) {
        ThirtyDaysApp()
    }
}