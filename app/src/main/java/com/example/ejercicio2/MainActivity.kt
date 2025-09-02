package com.example.ejercicio2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio2.ui.theme.Ejercicio2Theme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    color = Color.LightGray
                ) {
                    PantallaEjercicio2()
                }
            }
        }
    }
}

@Composable
fun PantallaEjercicio2(modifier: Modifier = Modifier) {
    // Estados
    var precioLitroTxt by rememberSaveable { mutableStateOf("") }
    var litrosTxt by rememberSaveable { mutableStateOf("") }
    var propinaTxt by rememberSaveable { mutableStateOf("") }
    var incluirPropina by rememberSaveable { mutableStateOf(false) }

    // Cálculos seguros
    val precio = precioLitroTxt.toDoubleOrNull() ?: 0.0
    val litros = litrosTxt.toDoubleOrNull() ?: 0.0
    val propina = propinaTxt.toDoubleOrNull() ?: 0.0

    val subtotal = precio * litros
    val total = subtotal + if (incluirPropina) subtotal * (propina / 100) else 0.0
    val totalFormateado = NumberFormat.getCurrencyInstance(Locale("es", "MX")).format(total)

    // Layout centrado + fondo LightGray
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Calcular Monto de Gasolina",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = precioLitroTxt,
                onValueChange = { precioLitroTxt = it },
                label = { Text("Ingresa precio por litro de Gasolina") },
                leadingIcon = { Text("\uD83D\uDCB2") }, // sin dependencia de íconos
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Divider(Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = litrosTxt,
                onValueChange = { litrosTxt = it },
                label = { Text("Litros") },
                leadingIcon = { Text("⛽") }, // sin dependencia de íconos
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Divider(Modifier.padding(vertical = 8.dp))

            OutlinedTextField(
                value = propinaTxt,
                onValueChange = { propinaTxt = it },
                label = { Text("Propina") },
                leadingIcon = { Text("\uD83D\uDD22") }, // sin dependencia de íconos
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿Deseas agregar la propina?")
                Switch(
                    checked = incluirPropina,
                    onCheckedChange = { incluirPropina = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Monto Total: $totalFormateado",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaPreviaEj2() {
    Ejercicio2Theme {
        PantallaEjercicio2()
    }
}
