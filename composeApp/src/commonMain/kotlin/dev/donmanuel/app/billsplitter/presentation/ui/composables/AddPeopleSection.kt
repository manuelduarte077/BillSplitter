package dev.donmanuel.app.billsplitter.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import billsplitter.composeapp.generated.resources.Res
import billsplitter.composeapp.generated.resources.add
import billsplitter.composeapp.generated.resources.person
import dev.donmanuel.app.billsplitter.presentation.ui.composables.common.BillSplitterCard
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPeopleSection(
    newPersonName: String,
    onNameChange: (String) -> Unit,
    onAddPerson: () -> Unit,
    modifier: Modifier = Modifier
) {
    BillSplitterCard(modifier = modifier) {
        Text(
            text = "Add People",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        var isError by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        
        LaunchedEffect(newPersonName) {
            if (newPersonName.isEmpty()) {
                focusRequester.requestFocus()
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newPersonName,
                onValueChange = { 
                    onNameChange(it)
                    isError = false
                },
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                label = { Text("Name") },
                placeholder = { Text("Enter name") },
                leadingIcon = {
                    Icon(
                        painterResource(Res.drawable.person),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                isError = isError,
                supportingText = if (isError) {
                    { Text("Name cannot be empty") }
                } else null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (newPersonName.isNotBlank()) {
                            onAddPerson()
                        } else {
                            isError = true
                        }
                    }
                ),
                shape = RoundedCornerShape(12.dp),
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = {
                    if (newPersonName.isNotBlank()) {
                        onAddPerson()
                    } else {
                        isError = true
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    painterResource(Res.drawable.add),
                    contentDescription = "Add person",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add")
            }
        }
    }
}
