package dev.donmanuel.app.billsplitter.domain.model

data class BillItem(
    val id: String,
    val description: String,
    val amount: Double,
    val sharedBy: List<String> = emptyList(),
)
