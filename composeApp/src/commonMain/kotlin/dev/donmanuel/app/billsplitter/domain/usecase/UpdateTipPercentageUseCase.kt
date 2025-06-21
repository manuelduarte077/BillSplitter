package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class UpdateTipPercentageUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(tipPercentage: String) {
        val percentage = tipPercentage.toDoubleOrNull() ?: 0.0
        repository.updateTipPercentage(percentage)
    }
}
