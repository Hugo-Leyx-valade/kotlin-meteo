    package com.example.tp1
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.tp1.data.model.City
    import com.example.tp1.ui.screens.CityList
    import com.example.tp1.ui.screens.WeatherScreen
    import com.example.tp1.ui.screens.GeoScreen
    import com.example.tp1.ui.theme.Tp1Theme

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                Tp1Theme {
                    AppNavigation()
                }
            }
        }
    }
/*
    @Composable
    fun GreetingCard(
        name: String,
        onChangeName: () -> Unit
    ) {
        Surface(
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            color = Color.LightGray
        ) {
            Row(modifier = Modifier.padding(14.dp)) {

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    Text("Salut,", style = MaterialTheme.typography.labelSmall)
                    Text(name, style = MaterialTheme.typography.headlineMedium)

                    Button(onClick = onChangeName) {
                        Text("Changer le nom")
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(navController: NavController) {

        Button(onClick = {
            navController.navigate("details")
        }) {
            Text("Aller aux détails")
        }
        Button(onClick = {
            navController.navigate("contactList")
        }) {
            Text("Aller à la liste de contact")
        }
    }

    @Composable
    fun DetailsScreen(navController: NavController) {
        var username by remember { mutableStateOf("Triks") }
        Column {

            GreetingCard(
                name = username,
                onChangeName = {
                    username = "Android Master"
                })

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Retour")
            }
            Button(onClick = {
                navController.navigate("contactList")
            }) {
                Text("Aller à la liste de contact")
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MyApp() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {

            composable("home") {
                HomeScreen(navController)
            }

            composable("details") {
                DetailsScreen(navController)
            }

            composable("contactList") {
                ContactListScreen(navController)
            }
        }
    }



    @Composable
    fun ContactListScreen(navController: NavController) {

        val contacts = listOf(
            Contact(1, "Hugo", Color.Red),
            Contact(2, "Lucas", Color.Blue),
            Contact(3, "Emma", Color.Green)
        )

        Column() {

            LazyColumn {
                items(contacts) { contact ->
                    GreetingCard(
                        name = contact.name,
                        onChangeName = {}
                    )
                }
            }

            Row() {
                    Button(onClick = {
                        navController.navigate("details")
                    }) {
                        Text("Aller aux détails")
                    }

                    Spacer(modifier = Modifier.padding(14.dp))

                    Button(onClick = {
                        navController.navigate("contactList")
                    }) {
                        Text("Aller à la liste de contact")
                    }
                }
            }
        }
*/

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    var city by remember { mutableStateOf(City("Paris",2.333333,48.866667)) }
    val cities = listOf(
        City("Paris", 48.866667, 2.333333),
        City("Lyon", 45.7578137, 4.8320114),
        City("Rennes", 48.112, -1.6743),
        City("Bordeaux", 44.841225, -0.5800364),
        City("Marmande", 44.503594, 0.1655),
        City("Casteljaloux", 44.503594, 0.1655),
    )
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {

        composable("weather") {
            WeatherScreen(navController,city = city)
        }

        composable("list") {
            CityList(
                navController = navController,
                cityUP = city,
                onChange = { city = it },
                cities = cities
            )
        }

        composable ("newCity"){
            GeoScreen(
                navController = navController,
                cities = cities
            )
        }
    }
}

