package dev.donmanuel.app.billsplitter.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.donmanuel.app.billsplitter.domain.usecase.BillSplitterUseCases
import dev.donmanuel.app.billsplitter.presentation.model.BillSplitterUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BillSplitterViewModel(
    private val useCases: BillSplitterUseCases
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    var uiState by mutableStateOf(BillSplitterUiState())
        private set

    init {
        observeBillSplit()
    }

    private fun observeBillSplit() {
        useCases.getBillSplit()
            .onEach { billSplit ->
                uiState = uiState.copy(billSplit = billSplit)
            }
            .launchIn(viewModelScope)
    }

    fun updateNewPersonName(name: String) {
        uiState = uiState.copy(newPersonName = name)
    }

    fun addPerson() {
        viewModelScope.launch {
            useCases.addPerson(uiState.newPersonName)
            uiState = uiState.copy(newPersonName = "")
        }
    }

    fun removePerson(personId: String) {
        viewModelScope.launch {
            useCases.removePerson(personId)
        }
    }

    fun updateNewItemDescription(description: String) {
        uiState = uiState.copy(newItemDescription = description)
    }

    fun updateNewItemAmount(amount: String) {
        uiState = uiState.copy(newItemAmount = amount)
    }

    fun togglePersonSelection(personId: String) {
        val selectedPeople = if (uiState.selectedPeople.contains(personId)) {
            uiState.selectedPeople - personId
        } else {
            uiState.selectedPeople + personId
        }
        uiState = uiState.copy(selectedPeople = selectedPeople)
    }

    fun addItem() {
        viewModelScope.launch {
            useCases.addItem(
                uiState.newItemDescription,
                uiState.newItemAmount,
                uiState.selectedPeople.toList()
            )
            uiState = uiState.copy(
                newItemDescription = "",
                newItemAmount = "",
                selectedPeople = emptySet()
            )
        }
    }

    fun removeItem(itemId: String) {
        viewModelScope.launch {
            useCases.removeItem(itemId)
        }
    }

    fun updateTipPercentage(tip: String) {
        uiState = uiState.copy(tipPercentage = tip)
        viewModelScope.launch {
            useCases.updateTipPercentage(tip)
        }
    }

    fun updateTaxAmount(tax: String) {
        uiState = uiState.copy(taxAmount = tax)
        viewModelScope.launch {
            useCases.updateTaxAmount(tax)
        }
    }

    fun resetBill() {
        viewModelScope.launch {
            useCases.resetBill()
            uiState = uiState.copy(
                newPersonName = "",
                newItemDescription = "",
                newItemAmount = "",
                selectedPeople = emptySet(),
                tipPercentage = "15",
                taxAmount = "0"
            )
        }
    }
}
