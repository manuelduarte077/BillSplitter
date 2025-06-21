package dev.donmanuel.app.billsplitter.domain.usecase

import dev.donmanuel.app.billsplitter.domain.model.Person
import dev.donmanuel.app.billsplitter.domain.repository.BillRepository

class AddPersonUseCase(private val repository: BillRepository) {
    suspend operator fun invoke(name: String) {
        if (name.isBlank()) return

        val person = Person(
            id = generateId(),
            name = name.trim()
        )
        repository.addPerson(person)
    }

    private fun generateId(): String {
        return "${kotlin.random.Random.nextLong()}"
    }
}
