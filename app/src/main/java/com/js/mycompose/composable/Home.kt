package com.js.mycompose.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.js.mycompose.Model.Product
import com.js.mycompose.Model.ProductDataFake
import com.js.mycompose.R
import com.js.mycompose.composable.util.MaskVisualTransformation
import com.js.mycompose.ui.theme.orangish

@Preview(showBackground = true)
@Composable
fun Home() {
    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                ResumeFilter()
            }
            Spacer(modifier = Modifier.padding(1.dp).background(color = Color.DarkGray))
            Row(modifier = Modifier.weight(1f)) {
                ResumeItems()
            }

            Spacer(modifier = Modifier.padding(1.dp).background(color = Color.DarkGray))
            Row() {
                ResumeValue()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeFilter() {
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Filter", fontSize = 16.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                var dateStart by remember { mutableStateOf("") }
                var dateEnd by remember { mutableStateOf("") }

                Column(modifier = Modifier.weight(5f)) {
                    OutlinedTextField(
                        value = dateStart,
                        onValueChange = { dateStart = it },
                        label = { Text(text = "Start") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = MaskVisualTransformation(NumberDefaults.DATEMASK)
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(modifier = Modifier.weight(5f)) {
                    OutlinedTextField(
                        value = dateEnd,
                        onValueChange = { if (it.length <= NumberDefaults.INPUT_LENGTH) dateEnd = it },
                        label = { Text(text = "End") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = MaskVisualTransformation(NumberDefaults.DATEMASK)
                    )
                }
            }
        }
    }
}

@Composable
fun ResumeItems() {
    Surface(color = orangish) {
        val products = remember { ProductDataFake.productList }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(
                items = products,
                itemContent = {
                    ProductListItem(product = it)
                }
            )
        }
    }
}

@Composable
fun ResumeValue() {
    Surface(color = Color.Cyan) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row() {
                Column() {
                    Text(text = stringResource(id = R.string.purchases).uppercase(), fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "00.00 R$", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column() {
                    Text(text = stringResource(id = R.string.sales).uppercase(), fontSize = 14.sp)
                    Text(text = "00.00 R$", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column() {
                    Text(text = stringResource(id = R.string.amount).uppercase(), fontSize = 14.sp)
                    Text(text = "00.00 R$", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListItem(product: Product) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        onClick = { expandedState = !expandedState }
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Top)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = product.title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(9f))
                    Text(
                        text = "00.00",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(2f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(0.4f)
                            .rotate(rotateState),
                        onClick = { expandedState = !expandedState }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop-Down Arrow")
                    }
                }
                if (expandedState) {
                    Row() {
                        ProductImage(product)
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = "test",
                                fontWeight = FontWeight.Normal,
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductImage(product: Product) {
    Image(
        painter = painterResource(id = product.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(84.dp)
    )
}

object NumberDefaults {
    const val DATEMASK = "##/##/####"
    const val INPUT_LENGTH = 8
}
