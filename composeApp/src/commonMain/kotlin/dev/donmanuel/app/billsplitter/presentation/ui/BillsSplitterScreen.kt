package dev.donmanuel.app.billsplitter.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.refresh
import dev.donmanuel.app.billsplitter.di.AppModule
import dev.donmanuel.app.billsplitter.presentation.ui.composables.*
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillSplitScreen() {
    val viewModel = AppModule.billSplitterViewModel
    val uiState = viewModel.uiState

    MaterialTheme {
        Scaffold(
            topBar = {
                MediumTopAppBar(
                    title = { Text("Bill Splitter") },
                    actions = {
                        IconButton(onClick = { viewModel.resetBill() }) {
                            Icon(
                                painterResource(Res.drawable.refresh),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Reset",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Add People Section
                item {
                    AddPeopleSection(
                        newPersonName = uiState.newPersonName,
                        onNameChange = viewModel::updateNewPersonName,
                        onAddPerson = viewModel::addPerson
                    )
                }

                // People List
                items(uiState.billSplit.people) { person ->
                    PersonCard(
                        person = person,
                        amount = uiState.billSplit.getPersonAmount(person.id),
                        onRemove = { viewModel.removePerson(person.id) }
                    )
                }

                // Add Items Section
                if (uiState.billSplit.people.isNotEmpty()) {
                    item {
                        AddItemSection(
                            description = uiState.newItemDescription,
                            amount = uiState.newItemAmount,
                            people = uiState.billSplit.people,
                            selectedPeople = uiState.selectedPeople,
                            onDescriptionChange = viewModel::updateNewItemDescription,
                            onAmountChange = viewModel::updateNewItemAmount,
                            onPersonToggle = viewModel::togglePersonSelection,
                            onAddItem = viewModel::addItem
                        )
                    }
                }

                // Items List
                items(uiState.billSplit.items) { item ->
                    ItemCard(
                        item = item,
                        people = uiState.billSplit.people,
                        onRemove = { viewModel.removeItem(item.id) }
                    )
                }

                // Tip and Tax Section
                if (uiState.billSplit.items.isNotEmpty()) {
                    item {
                        TipAndTaxSection(
                            tipPercentage = uiState.tipPercentage,
                            taxAmount = uiState.taxAmount,
                            onTipChange = viewModel::updateTipPercentage,
                            onTaxChange = viewModel::updateTaxAmount
                        )
                    }

                    // Summary
                    item {
                        BillSummary(billSplit = uiState.billSplit)
                    }
                }
            }
        }
    }
}
