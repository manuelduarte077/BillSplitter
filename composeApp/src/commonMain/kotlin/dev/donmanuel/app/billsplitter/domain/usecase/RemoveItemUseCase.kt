package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class RemoveItemUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(itemId: String) {
        repository.removeItem(itemId)
    }
}
