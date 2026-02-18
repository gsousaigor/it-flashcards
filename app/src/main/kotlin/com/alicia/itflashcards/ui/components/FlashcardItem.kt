package com.alicia.itflashcards.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alicia.itflashcards.model.Flashcard

@Composable
fun FlashcardItem(
    flashcard: Flashcard,
    onStatusChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "rotation"
    )

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable { rotated = !rotated },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (rotated) MaterialTheme.colorScheme.secondaryContainer 
                                else if (flashcard.isKnown) Color(0xFF2E7D32) // Verde se souber
                                else MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (rotation <= 90f) {
                    // Frente do Card
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = flashcard.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = if (flashcard.isKnown) Color.White else MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = flashcard.question,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            color = if (flashcard.isKnown) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                } else {
                    // Verso do Card
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { rotationY = 180f },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = flashcard.answer,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Button(
                                    onClick = { onStatusChange(false) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text("Errei")
                                }
                                Button(
                                    onClick = { onStatusChange(true) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                                ) {
                                    Text("Acertei")
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Botão de deletar pequeno abaixo do card
        Text(
            text = "Remover Card",
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 4.dp, end = 8.dp)
                .clickable { onDelete() }
        )
    }
}
