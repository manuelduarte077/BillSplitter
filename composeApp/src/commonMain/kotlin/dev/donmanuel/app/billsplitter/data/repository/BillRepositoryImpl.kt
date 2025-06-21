package dev.donmanuel.app.billsplitter.data.repository

import dev.donmanuel.app.billsplitter.domain.model.BillItem
import dev.donmanuel.app.billsplitter.domain.model.BillSplit
import dev.donmanuel.app.billsplitter.domain.model.Person
import dev.donmanuel.app.billsplitter.domain.repository.BillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillRepositoryImpl : BillRepository {

    private val _billSplit = MutableStateFlow(
        BillSplit(
            totalAmount = 0.0,
            people = emptyList(),
            items = emptyList()
        )
    )

    override fun getBillSplit(): Flow<BillSplit> {
        return _billSplit.asStateFlow()
    }

    override suspend fun addPerson(person: Person) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            people = currentBill.people + person
        )
    }

    override suspend fun removePerson(personId: String) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            people = currentBill.people.filter { it.id != personId },
            items = currentBill.items.map { item ->
                item.copy(sharedBy = item.sharedBy.filter { it != personId })
            }
        )
    }

    override suspend fun addItem(item: BillItem) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            items = currentBill.items + item
        )
    }

    override suspend fun removeItem(itemId: String) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            items = currentBill.items.filter { it.id != itemId }
        )
    }

    override suspend fun updateTipPercentage(percentage: Double) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            tipPercentage = percentage
        )
    }

    override suspend fun updateTaxAmount(amount: Double) {
        val currentBill = _billSplit.value
        _billSplit.value = currentBill.copy(
            taxAmount = amount
        )
    }

    override suspend fun resetBill() {
        _billSplit.value = BillSplit(
            totalAmount = 0.0,
            people = emptyList(),
            items = emptyList()
        )
    }
}
