package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.model.BillItem
import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class AddItemUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(description: String, amount: String, sharedBy: List<String>) {
        if (description.isBlank() || amount.isBlank() || sharedBy.isEmpty()) return
        
        val amountValue = amount.toDoubleOrNull() ?: 0.0
        if (amountValue <= 0) return
        
        val item = BillItem(
            id = generateId(),
            description = description.trim(),
            amount = amountValue,
            sharedBy = sharedBy
        )
        
        repository.addItem(item)
    }
    
    private fun generateId(): String {
        return "${kotlin.random.Random.nextLong()}"
    }
}
