package dev.donmanuel.app.billsplitter.presentation.ui.composables.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * Un componente de tarjeta elevada reutilizable con estilo consistente
 * para evitar la duplicación de código en toda la aplicación.
 */
@Composable
fun BillSplitterCard(
    modifier: Modifier = Modifier,
    elevation: Int = 1,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardShape = MaterialTheme.shapes.large
    val backgroundColor = MaterialTheme.colorScheme.surface
    val borderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
    
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .shadow(
                elevation = 4.dp,
                shape = cardShape,
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
            )
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = cardShape
            ),
        shape = cardShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = elevation.dp,
            pressedElevation = (elevation + 2).dp,
            focusedElevation = (elevation + 1).dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(16.dp),
            content = content
        )
    }
}

/**
 * Variante de tarjeta para contenido destacado, usando el color del contenedor primario.
 */
@Composable
fun BillSplitterHighlightCard(
    modifier: Modifier = Modifier,
    elevation: Int = 2,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardShape = RoundedCornerShape(16.dp)
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .shadow(
                elevation = 6.dp,
                shape = cardShape,
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            )
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = cardShape
            ),
        shape = cardShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = elevation.dp,
            pressedElevation = (elevation + 2).dp,
            focusedElevation = (elevation + 1).dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(20.dp),
            content = content
        )
    }
}
