package dev.donmanuel.app.billsplitter.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.donmanuel.app.billsplitter.model.BillItem
import dev.donmanuel.app.billsplitter.model.BillSplit
import dev.donmanuel.app.billsplitter.model.Person
import kotlin.random.Random

class BillSplitterViewModel {
    var billSplit by mutableStateOf(
        BillSplit(
            totalAmount = 0.0,
            people = emptyList(),
            items = emptyList()
        )
    )
        private set

    var newPersonName by mutableStateOf("")
        private set

    var newItemDescription by mutableStateOf("")
        private set

    var newItemAmount by mutableStateOf("")
        private set

    var selectedPeople by mutableStateOf(setOf<String>())
        private set

    var tipPercentage by mutableStateOf("15")
        private set

    var taxAmount by mutableStateOf("0")
        private set

    private fun generateId(): String {
        return "${Random.nextLong()}"
    }

    fun updateNewPersonName(name: String) {
        newPersonName = name
    }

    fun addPerson() {
        if (newPersonName.isNotBlank()) {
            val person = Person(
                id = generateId(),
                name = newPersonName.trim()
            )
            billSplit = billSplit.copy(
                people = billSplit.people + person
            )
            newPersonName = ""
        }
    }

    fun removePerson(personId: String) {
        billSplit = billSplit.copy(
            people = billSplit.people.filter { it.id != personId },
            items = billSplit.items.map { item ->
                item.copy(sharedBy = item.sharedBy.filter { it != personId })
            }
        )
    }

    fun updateNewItemDescription(description: String) {
        newItemDescription = description
    }

    fun updateNewItemAmount(amount: String) {
        newItemAmount = amount
    }

    fun togglePersonSelection(personId: String) {
        selectedPeople = if (selectedPeople.contains(personId)) {
            selectedPeople - personId
        } else {
            selectedPeople + personId
        }
    }

    fun addItem() {
        if (newItemDescription.isNotBlank() &&
            newItemAmount.isNotBlank() &&
            selectedPeople.isNotEmpty()
        ) {

            val amount = newItemAmount.toDoubleOrNull() ?: 0.0
            val item = BillItem(
                id = generateId(),
                description = newItemDescription.trim(),
                amount = amount,
                sharedBy = selectedPeople.toList()
            )

            billSplit = billSplit.copy(
                items = billSplit.items + item
            )

            // Reset form
            newItemDescription = ""
            newItemAmount = ""
            selectedPeople = emptySet()
        }
    }

    fun removeItem(itemId: String) {
        billSplit = billSplit.copy(
            items = billSplit.items.filter { it.id != itemId }
        )
    }

    fun updateTipPercentage(tip: String) {
        tipPercentage = tip
        val tipValue = tip.toDoubleOrNull() ?: 0.0
        billSplit = billSplit.copy(tipPercentage = tipValue)
    }

    fun updateTaxAmount(tax: String) {
        taxAmount = tax
        val taxValue = tax.toDoubleOrNull() ?: 0.0
        billSplit = billSplit.copy(taxAmount = taxValue)
    }

    fun resetBill() {
        billSplit = BillSplit(
            totalAmount = 0.0,
            people = emptyList(),
            items = emptyList()
        )
        newPersonName = ""
        newItemDescription = ""
        newItemAmount = ""
        selectedPeople = emptySet()
        tipPercentage = "15"
        taxAmount = "0"
    }
}