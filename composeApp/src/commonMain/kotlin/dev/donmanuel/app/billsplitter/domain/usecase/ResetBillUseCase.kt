package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class ResetBillUseCase(private val repository: BillRepository) {
    suspend operator fun invoke() {
        repository.resetBill()
    }
}
