package dev.donmanuel.app.billsplitter.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.close
import billsplitter.composeapp.generated.resources.receipt
import dev.donmanuel.app.billsplitter.common.extensions.formatCurrency
import dev.donmanuel.app.billsplitter.domain.model.BillItem
import dev.donmanuel.app.billsplitter.domain.model.Person
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemCard(
    item: BillItem,
    people: List<Person>,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(Res.drawable.receipt),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "$${item.amount.formatCurrency()}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                IconButton(onClick = onRemove) {
                    Icon(
                        painterResource(Res.drawable.close),
                        contentDescription = "Remove item"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Shared by:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = people
                    .filter { person -> item.sharedBy.contains(person.id) }
                    .joinToString(", ") { it.name },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
