package dev.donmanuel.app.billsplitter.domain.usecase

class BillSplitterUseCases(
    val getBillSplit: GetBillSplitUseCase,
    val addPerson: AddPersonUseCase,
    val removePerson: RemovePersonUseCase,
    val addItem: AddItemUseCase,
    val removeItem: RemoveItemUseCase,
    val updateTipPercentage: UpdateTipPercentageUseCase,
    val updateTaxAmount: UpdateTaxAmountUseCase,
    val resetBill: ResetBillUseCase
)
