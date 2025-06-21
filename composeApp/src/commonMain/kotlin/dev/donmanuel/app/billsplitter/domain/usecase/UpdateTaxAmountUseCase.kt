package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class UpdateTaxAmountUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(taxAmount: String) {
        val amount = taxAmount.toDoubleOrNull() ?: 0.0
        repository.updateTaxAmount(amount)
    }
}
