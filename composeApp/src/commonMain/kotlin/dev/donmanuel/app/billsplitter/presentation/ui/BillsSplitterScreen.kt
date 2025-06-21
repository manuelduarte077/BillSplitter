package dev.donmanuel.app.billsplitter.presentation.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.add
import billsplitter.composeapp.generated.resources.refresh
import dev.donmanuel.app.billsplitter.di.AppModule
import dev.donmanuel.app.billsplitter.presentation.ui.composables.*
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillSplitScreen() {
    val viewModel = AppModule.billSplitterViewModel
    val uiState = viewModel.uiState
    var showAddItemDialog by remember { mutableStateOf(false) }
    
    // Add scroll state to track scrolling
    val scrollState = rememberLazyListState()
    
    // Track if the user is scrolling up or down
    val isScrollingUp = remember {
        derivedStateOf {
            // If the first visible item is at the top, or we're scrolling up
            scrollState.firstVisibleItemIndex == 0 || 
            scrollState.firstVisibleItemScrollOffset == 0 ||
            !scrollState.canScrollBackward
        }
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    MediumTopAppBar(
                        title = { 
                            Text(
                                "Bill Splitter",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            ) 
                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f),
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        actions = {
                            IconButton(onClick = { viewModel.resetBill() }) {
                                Icon(
                                    painterResource(Res.drawable.refresh),
                                    contentDescription = "Reset Bill",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                    )
                },
                floatingActionButton = {
                    if (uiState.billSplit.people.isNotEmpty()) {
                        AnimatedVisibility(
                            visible = isScrollingUp.value,
                            enter = fadeIn() + slideInVertically { it },
                            exit = fadeOut() + slideOutVertically { it }
                        ) {
                            FloatingActionButton(
                                onClick = { showAddItemDialog = true },
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ) {
                                Icon(
                                    painterResource(Res.drawable.add),
                                    contentDescription = "Add Item",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            ) { paddingValues ->
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        AddPeopleSection(
                            newPersonName = uiState.newPersonName,
                            onNameChange = viewModel::updateNewPersonName,
                            onAddPerson = viewModel::addPerson
                        )
                    }

                    if (uiState.billSplit.people.isNotEmpty()) {
                        item {
                            Text(
                                "People",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    items(uiState.billSplit.people) { person ->
                        PersonCard(
                            person = person,
                            amount = uiState.billSplit.getPersonAmount(person.id),
                            onRemove = { viewModel.removePerson(person.id) }
                        )
                    }

                    if (uiState.billSplit.items.isNotEmpty()) {
                        item {
                            Text(
                                "Items",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                        }
                    }

                    items(uiState.billSplit.items) { item ->
                        ItemCard(
                            item = item,
                            people = uiState.billSplit.people,
                            onRemove = { viewModel.removeItem(item.id) }
                        )
                    }

                    if (uiState.billSplit.items.isNotEmpty()) {
                        item {
                            TipAndTaxSection(
                                tipPercentage = uiState.tipPercentage,
                                taxAmount = uiState.taxAmount,
                                onTipChange = viewModel::updateTipPercentage,
                                onTaxChange = viewModel::updateTaxAmount
                            )
                        }

                        item {
                            BillSummary(billSplit = uiState.billSplit)
                        }
                    }
                }

                if (showAddItemDialog) {
                    AddItemDialog(
                        description = uiState.newItemDescription,
                        amount = uiState.newItemAmount,
                        people = uiState.billSplit.people,
                        selectedPeople = uiState.selectedPeople,
                        onDescriptionChange = viewModel::updateNewItemDescription,
                        onAmountChange = viewModel::updateNewItemAmount,
                        onPersonToggle = viewModel::togglePersonSelection,
                        onAddItem = {
                            viewModel.addItem()
                            showAddItemDialog = false
                        },
                        onDismiss = { showAddItemDialog = false }
                    )
                }
            }
        }
    }
}
