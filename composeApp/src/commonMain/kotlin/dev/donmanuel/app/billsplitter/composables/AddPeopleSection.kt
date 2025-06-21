package dev.donmanuel.app.billsplitter.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.add
import billsplitter.composeapp.generated.resources.person
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPeopleSection(
    newPersonName: String,
    onNameChange: (String) -> Unit,
    onAddPerson: () -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Add People",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = newPersonName,
                    onValueChange = onNameChange,
                    label = { Text("Person name") },
                    modifier = Modifier.weight(1f)
                )
                FilledTonalButton(
                    onClick = onAddPerson,
                    enabled = newPersonName.isNotBlank()
                ) {
                    Icon(
                        painterResource(Res.drawable.add),
                        contentDescription = "Add",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}