package dev.donmanuel.app.billsplitter.presentation.model

import dev.donmanuel.app.billsplitter.domain.model.BillSplit
import dev.donmanuel.app.billsplitter.domain.model.Person

data class BillSplitterUiState(
    val billSplit: BillSplit = BillSplit(
        totalAmount = 0.0,
        people = emptyList(),
        items = emptyList()
    ),
    val newPersonName: String = "",
    val newItemDescription: String = "",
    val newItemAmount: String = "",
    val selectedPeople: Set<String> = emptySet(),
    val tipPercentage: String = "15",
    val taxAmount: String = "0"
)
