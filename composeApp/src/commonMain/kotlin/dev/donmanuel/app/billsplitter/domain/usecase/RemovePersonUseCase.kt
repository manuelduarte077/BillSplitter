package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class RemovePersonUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(personId: String) {
        repository.removePerson(personId)
    }
}
