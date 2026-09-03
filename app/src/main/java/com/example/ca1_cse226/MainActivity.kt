package com.example.ca1_cse226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ActionCardPreview()
            }
        }
    }
}

@Composable
fun ActionCardSlot(
    isSelected: Boolean,
    onCardClick: () -> Unit,
    headerSlot: @Composable () -> Unit,
    bodySlot: @Composable () -> Unit
) {

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.outline
        },
        label = "Border Color"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onCardClick()
            },
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            headerSlot()

            Spacer(modifier = Modifier.height(12.dp))

            bodySlot()
        }
    }
}

@Composable
fun ActionCardPreview() {

    var selectedCard by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        ActionCardSlot(
            isSelected = selectedCard == 1,

            onCardClick = {
                selectedCard = 1
            },

            headerSlot = {

                Text(
                    text = "Analytics Report",
                    style = MaterialTheme.typography.titleLarge
                )
            },

            bodySlot = {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    AssistChip(
                        onClick = {},
                        label = {
                            Text("Sales")
                        }
                    )

                    AssistChip(
                        onClick = {},
                        label = {
                            Text("Finance")
                        }
                    )

                    AssistChip(
                        onClick = {},
                        label = {
                            Text("Growth")
                        }
                    )
                }
            }
        )

        ActionCardSlot(
            isSelected = selectedCard == 2,

            onCardClick = {
                selectedCard = 2
            },

            headerSlot = {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Information"
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = "Performance",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },

            bodySlot = {

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Text("CPU Usage")

                    Text("Memory")

                    Text("Network")
                }
            }
        )
    }
}