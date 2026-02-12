package com.example.tp1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Surface(modifier = Modifier.padding(15.dp)) {

            Text(
                text = temperature?.let { "Température : $it °C" }
                    ?: "Chargement..."
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        Button(onClick = { navController.popBackStack() }) { Text("Retour à la liste des villes") }
    }

}

@Composable
fun CityList(navController: NavHostController, cityUP: City,onChange: (City)-> Unit) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "Liste des villes :")
        val cities = listOf(City("Paris",2.333333,48.866667), City("Lyon",4.8320114,45.7578137), City("Renne",6.33333,51.23333), City("Bordeaux",-0.5800364,44.841225))
        cities.forEach { city ->
            Surface(
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(20.dp),
                color = Color.LightGray
            ) {
                Row(modifier = Modifier.padding(14.dp)) {

                    Column {
                        Text(city.name, style = MaterialTheme.typography.labelSmall, fontSize = 30.sp)
                        Row() {
                            Text(DecimalFormat("#.##")
                                    .apply { roundingMode = RoundingMode.FLOOR }
                                    .format(city.latitude)
                                ,style = MaterialTheme.typography.headlineMedium, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(DecimalFormat("#.##")
                                .apply { roundingMode = RoundingMode.FLOOR }
                                .format(city.longitude), style = MaterialTheme.typography.headlineMedium, fontSize = 20.sp)
                        }

                        Button(onClick = {
                            navController.navigate("weather");
                            onChange(city)
                        }) {
                            Text("Voir la météo de "+city.name)
                        }
                    }
                }
            }
        }
    }
}

