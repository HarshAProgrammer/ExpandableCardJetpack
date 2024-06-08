package com.smartherd.expandablecard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlin.math.exp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedCardShape(
    title: String,
    subtitle: String,
    titleFontSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    subtitleFontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    subtitleFontWeight: FontWeight = FontWeight.Light
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationDegree by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        onClick = { expandedState = !expandedState },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    modifier = Modifier.weight(.8f),
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = {
                        expandedState = !expandedState
                    }, modifier = Modifier
                        .alpha(
                            DefaultAlpha
                        )
                        .rotate(rotationDegree)
                        .weight(.2f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Text(
                    text = subtitle,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = subtitleFontSize,
                    fontWeight = subtitleFontWeight
                )
            }

        }
    }


}