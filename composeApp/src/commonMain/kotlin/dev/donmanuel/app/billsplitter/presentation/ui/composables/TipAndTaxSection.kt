package dev.donmanuel.app.billsplitter.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.percent
import billsplitter.composeapp.generated.resources.receipt
import dev.donmanuel.app.billsplitter.presentation.ui.composables.common.BillSplitterCard
import org.jetbrains.compose.resources.painterResource

@Composable
fun TipAndTaxSection(
    tipPercentage: String,
    taxAmount: String,
    onTipChange: (String) -> Unit,
    onTaxChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BillSplitterCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tip & Tax",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = tipPercentage,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                            onTipChange(newValue)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    label = { Text("Tip %") },
                    placeholder = { Text("0") },
                    leadingIcon = {
                        Icon(
                            painterResource(Res.drawable.percent),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(12.dp),
                )
                
                OutlinedTextField(
                    value = taxAmount,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                            onTaxChange(newValue)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    label = { Text("Tax $") },
                    placeholder = { Text("0.00") },
                    leadingIcon = {
                        Icon(
                            painterResource(Res.drawable.receipt),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(12.dp),
                )
            }
        }
    }
}
