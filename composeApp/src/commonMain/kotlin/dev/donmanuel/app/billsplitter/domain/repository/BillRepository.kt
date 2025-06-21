package dev.donmanuel.app.billsplitter.domain.repository

import dev.donmanuel.app.billsplitter.domain.model.BillItem
import dev.donmanuel.app.billsplitter.domain.model.BillSplit
import dev.donmanuel.app.billsplitter.domain.model.Person
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for bill splitting operations
 */
interface BillRepository {
    /**
     * Get the current bill state as a Flow
     */
    fun getBillSplit(): Flow<BillSplit>

    /**
     * Add a person to the bill
     */
    suspend fun addPerson(person: Person)

    /**
     * Remove a person from the bill
     */
    suspend fun removePerson(personId: String)

    /**
     * Add an item to the bill
     */
    suspend fun addItem(item: BillItem)

    /**
     * Remove an item from the bill
     */
    suspend fun removeItem(itemId: String)

    /**
     * Update the tip percentage
     */
    suspend fun updateTipPercentage(percentage: Double)

    /**
     * Update the tax amount
     */
    suspend fun updateTaxAmount(amount: Double)

    /**
     * Reset the bill to initial state
     */
    suspend fun resetBill()
}
