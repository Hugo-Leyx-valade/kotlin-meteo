package com.example.tp1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.tappableElementIgnoringVisibility
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tp1.data.model.City
import org.koin.androidx.compose.koinViewModel
import com.example.tp1.ui.viewmodel.WeatherViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun WeatherScreen(
navController: NavHostController,
viewModel: WeatherViewModel = koinViewModel(),
city: City
) {

val temperature = viewModel.temperature.value

LaunchedEffect(Unit) {
    viewModel.loadWeather(city)
}
Column() {

    Button(onClick = { navController.popBackStack() }) { Text("Retour à la liste des villes")}
    Spacer(modifier = Modifier.width(40.dp))
    Surface(modifier = Modifier.padding(15.dp)) {
        Row() {
            Text(
                text = city.name,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.width(200.dp))
            Text(
                text = temperature?.let { "$it °C" }
                    ?: "Chargement...",
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 30.sp,
            )
        }
    }
}

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityList(
navController: NavHostController,
cityUP: City,
onChange: (City) -> Unit,
cities: List<City>
) {




var query by rememberSaveable { mutableStateOf("") }

val filteredCities = cities.filter {
    it.name.contains(query, ignoreCase = true)
}
    Box(modifier= Modifier.fillMaxSize()) {
        Column() {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(19.dp),
                placeholder = { Text("Rechercher une ville") },
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            )


            LazyColumn(
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {

                item() {
                    Text("Liste des villes :")
                }

                items(filteredCities) { city ->

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.LightGray
                    ) {

                        Row(modifier = Modifier.padding(14.dp)) {

                            Column {

                                Text(
                                    city.name,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 30.sp
                                )

                                Row {
                                    Text(
                                        DecimalFormat("#.##")
                                            .apply { roundingMode = RoundingMode.FLOOR }
                                            .format(city.latitude),
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontSize = 20.sp
                                    )

                                    Spacer(Modifier.width(10.dp))
                                    Text("/", fontSize = 20.sp)
                                    Spacer(Modifier.width(10.dp))

                                    Text(
                                        DecimalFormat("#.##")
                                            .apply { roundingMode = RoundingMode.FLOOR }
                                            .format(city.longitude),
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontSize = 20.sp
                                    )
                                }

                                Button(
                                    onClick = {
                                        onChange(city)
                                        navController.navigate("weather")
                                    }
                                ) {
                                    Text("Voir la météo de ${city.name}")
                                }
                            }
                        }
                    }

                }
            }

        }
        Button(onClick = {navController.navigate("newCity")},
            modifier = Modifier.align(Alignment.BottomEnd).padding(30.dp)
        ) {
            Text("+")
        }
    }
}

