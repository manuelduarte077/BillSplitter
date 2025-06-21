package dev.donmanuel.app.billsplitter.di

import dev.donmanuel.app.billsplitter.data.repository.BillRepositoryImpl
import dev.donmanuel.app.billsplitter.domain.repository.BillRepository
import dev.donmanuel.app.billsplitter.domain.usecase.*
import dev.donmanuel.app.billsplitter.presentation.viewmodel.BillSplitterViewModel

/**
 * Simple dependency injection container for the app
 */
object AppModule {

    // Repository
    private val billRepository: BillRepository by lazy {
        BillRepositoryImpl()
    }

    // Use cases
    private val getBillSplitUseCase by lazy {
        GetBillSplitUseCase(billRepository)
    }

    private val addPersonUseCase by lazy {
        AddPersonUseCase(billRepository)
    }

    private val removePersonUseCase by lazy {
        RemovePersonUseCase(billRepository)
    }

    private val addItemUseCase by lazy {
        AddItemUseCase(billRepository)
    }

    private val removeItemUseCase by lazy {
        RemoveItemUseCase(billRepository)
    }

    private val updateTipPercentageUseCase by lazy {
        UpdateTipPercentageUseCase(billRepository)
    }

    private val updateTaxAmountUseCase by lazy {
        UpdateTaxAmountUseCase(billRepository)
    }

    private val resetBillUseCase by lazy {
        ResetBillUseCase(billRepository)
    }

    // Combined use cases
    private val billSplitterUseCases by lazy {
        BillSplitterUseCases(
            getBillSplit = getBillSplitUseCase,
            addPerson = addPersonUseCase,
            removePerson = removePersonUseCase,
            addItem = addItemUseCase,
            removeItem = removeItemUseCase,
            updateTipPercentage = updateTipPercentageUseCase,
            updateTaxAmount = updateTaxAmountUseCase,
            resetBill = resetBillUseCase
        )
    }

    // ViewModel
    val billSplitterViewModel by lazy {
        BillSplitterViewModel(billSplitterUseCases)
    }
}
