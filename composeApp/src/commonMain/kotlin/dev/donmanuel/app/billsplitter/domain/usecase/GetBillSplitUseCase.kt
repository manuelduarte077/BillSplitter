package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.model.BillSplit
import dev.donmanuel.app.billsplitter.domain.repository.BillRepository
import kotlinx.coroutines.flow.Flow

class GetBillSplitUseCase(private val repository: BillRepository) {
    operator fun invoke(): Flow<BillSplit> {
        return repository.getBillSplit()
    }
}
