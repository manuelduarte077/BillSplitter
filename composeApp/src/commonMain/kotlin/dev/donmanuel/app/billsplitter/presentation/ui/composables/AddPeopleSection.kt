package dev.donmanuel.app.billsplitter.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.add
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPeopleSection(
    newPersonName: String,
    onNameChange: (String) -> Unit,
    onAddPerson: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
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
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = newPersonName,
                    onValueChange = onNameChange,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Person name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { onAddPerson() })
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Button(
                    onClick = onAddPerson,
                    enabled = newPersonName.isNotBlank()
                ) {
                    Icon(
                        painterResource(Res.drawable.add),
                        contentDescription = "Add person"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add")
                }
            }
        }
    }
}
