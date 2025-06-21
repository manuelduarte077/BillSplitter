package dev.donmanuel.app.billsplitter.domain.model

data class BillSplit(
    val totalAmount: Double,
    val people: List<Person>,
    val items: List<BillItem>,
    val tipPercentage: Double = 15.0,
    val taxAmount: Double = 0.0
) {
    val subtotal: Double
        get() = items.sumOf { it.amount }

    val tipAmount: Double
        get() = subtotal * (tipPercentage / 100)

    val totalWithTipAndTax: Double
        get() = subtotal + tipAmount + taxAmount

    fun getPersonAmount(personId: String): Double {
        val itemsForPerson = items.filter { it.sharedBy.contains(personId) }
        val personalTotal = itemsForPerson.sumOf { item ->
            item.amount / item.sharedBy.size
        }

        val personalProportion = personalTotal / subtotal
        return personalTotal + (tipAmount * personalProportion) + (taxAmount * personalProportion)
    }
}
