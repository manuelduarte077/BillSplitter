package dev.donmanuel.app.billsplitter.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.billsplitter.common.extensions.formatCurrency
import dev.donmanuel.app.billsplitter.domain.model.BillSplit
import dev.donmanuel.app.billsplitter.presentation.ui.composables.common.BillSplitterHighlightCard

@Composable
fun BillSummary(
    billSplit: BillSplit,
    modifier: Modifier = Modifier
) {
    BillSplitterHighlightCard(modifier = modifier) {
        Text(
            text = "Bill Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        
        SummaryRow(
            label = "Subtotal",
            amount = billSplit.subtotal
        )
        
        SummaryRow(
            label = "Tip (${billSplit.tipPercentage}%)",
            amount = billSplit.tipAmount
        )
        
        SummaryRow(
            label = "Tax",
            amount = billSplit.taxAmount
        )
        
        SummaryRow(
            label = "Total",
            amount = billSplit.totalWithTipAndTax,
            isTotal = true
        )
    }
}

@Composable
private fun SummaryRow(
    label: String,
    amount: Double,
    isTotal: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        
        Text(
            text = "$${amount.formatCurrency()}",
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
