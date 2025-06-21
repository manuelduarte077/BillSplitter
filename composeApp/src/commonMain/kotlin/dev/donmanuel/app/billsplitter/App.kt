package dev.donmanuel.app.billsplitter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.refresh
import dev.donmanuel.app.billsplitter.composables.AddItemSection
import dev.donmanuel.app.billsplitter.composables.AddPeopleSection
import dev.donmanuel.app.billsplitter.composables.BillSummary
import dev.donmanuel.app.billsplitter.composables.ItemCard
import dev.donmanuel.app.billsplitter.composables.PersonCard
import dev.donmanuel.app.billsplitter.composables.TipAndTaxSection
import dev.donmanuel.app.billsplitter.viewmodels.BillSplitterViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val viewModel = remember { BillSplitterViewModel() }

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
                        newPersonName = viewModel.newPersonName,
                        onNameChange = viewModel::updateNewPersonName,
                        onAddPerson = viewModel::addPerson
                    )
                }

                // People List
                items(viewModel.billSplit.people) { person ->
                    PersonCard(
                        person = person,
                        amount = viewModel.billSplit.getPersonAmount(person.id),
                        onRemove = { viewModel.removePerson(person.id) }
                    )
                }

                // Add Items Section
                if (viewModel.billSplit.people.isNotEmpty()) {
                    item {
                        AddItemSection(
                            description = viewModel.newItemDescription,
                            amount = viewModel.newItemAmount,
                            people = viewModel.billSplit.people,
                            selectedPeople = viewModel.selectedPeople,
                            onDescriptionChange = viewModel::updateNewItemDescription,
                            onAmountChange = viewModel::updateNewItemAmount,
                            onPersonToggle = viewModel::togglePersonSelection,
                            onAddItem = viewModel::addItem
                        )
                    }
                }

                // Items List
                items(viewModel.billSplit.items) { item ->
                    ItemCard(
                        item = item,
                        people = viewModel.billSplit.people,
                        onRemove = { viewModel.removeItem(item.id) }
                    )
                }

                // Tip and Tax Section
                if (viewModel.billSplit.items.isNotEmpty()) {
                    item {
                        TipAndTaxSection(
                            tipPercentage = viewModel.tipPercentage,
                            taxAmount = viewModel.taxAmount,
                            onTipChange = viewModel::updateTipPercentage,
                            onTaxChange = viewModel::updateTaxAmount
                        )
                    }

                    // Summary
                    item {
                        BillSummary(billSplit = viewModel.billSplit)
                    }
                }
            }
        }
    }
}