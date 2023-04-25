package com.example.mycontacts

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.compose.NavHost
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycontacts.ContactUi.ScreenNavigatorHost
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.Fitness.FitnessNavHost
//import com.example.mycontacts.ui.partA
//import com.example.mycontacts.ui.partB
import com.example.mycontacts.ui.theme.MyContactsTheme
import com.example.practice.NestedNavigation.Contain
import com.example.practice.NestedNavigation.SearchContact
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {

    lateinit var nav:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()

                   // SearchContact()
                    val textState = remember { mutableStateOf(TextFieldValue("")) }
                    val data = remember{ mutableStateOf(TextFieldValue())}
                   // SearchView(state = data)

                   // Contain(nav = navController)
                    ScreenNavigatorHost(nav = navController )
                   // Container(navController = navController)
                  //  AllInOne(nav = navController)


                }
            }
        }
    }
}




//sealed class NestedScreen(val route:String){
//    object one:NestedScreen(route = "book/{data}")
//    object two:NestedScreen(route = "pencil")
//    object three:NestedScreen(route = "phone")
//    object four:NestedScreen(route = "marker")
//    object sectionA:NestedScreen(route = "secA")
//    object sectionB:NestedScreen(route = "secB")
//}
//@Composable
//fun AllInOne(nav:NavHostController){
//    val mod:Riley = viewModel()
//    NavHost(navController = nav, startDestination = NestedScreen.sectionA.route, builder = {
//        partA(nav,mod = Riley())
//        partB(nav,mod = Riley())
//
//
//    } )
//}
//@Composable
//fun Book(nav: NavController,mod:Riley){
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val get = mod.pers
//        if (get != null) {
//            Log.d("hello",get.firstName)
//            Log.d("hello",get.lastName)
//        }
//        Text(
//            text = "Book Screen ",
//            color = Color.Green,
//            fontSize = 40.sp,
//            fontWeight = FontWeight.ExtraBold,
//            modifier = Modifier
//                .clickable { nav.navigate(NestedScreen.three.route) }
//        )
//    }
//}
//@Composable
//fun Pencil(nav:NavController){
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text(
//            text = "Pencil Screen",
//            color = Color.Red,
//            fontSize = 40.sp,
//            fontWeight = FontWeight.ExtraBold,
//            modifier = Modifier
//                .clickable { nav.navigate(NestedScreen.three.route)}
//        )
//    }
//}
//@Composable
//fun Phone(nav:NavController) {
//
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text(
//            text = "Phone Screen",
//            color = Color.Magenta,
//            fontSize = 40.sp,
//            fontWeight = FontWeight.ExtraBold,
//            modifier = Modifier
//
//                .clickable { //navOptions { popUpTo(NestedScreen.sectionA.route) }
//                    nav.navigate(NestedScreen.four.route)
//                }
//        )
//    }
//}
//@Composable
//fun Marker(nav:NavController,mod: Riley) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text(
//            text = "Marker Screen",
//            color = Color.Blue,
//            fontSize = 40.sp,
//            fontWeight = FontWeight.ExtraBold,
//            modifier = Modifier
//               // .clickable { nav.navigate(NestedScreen.one.route) }
//
//                .clickable {
//                    val nol = Person("Mike","RockStar")
//                   // nav.currentBackStackEntry?.savedStateHandle?.set(key = "son",value = nol)
//                    mod.getData(data = nol)
//                    nav.navigate("book/Riley Davis") }
//        )
//    }
//}
//class Riley:ViewModel(){
//    var pers by mutableStateOf<Person?>(null)
//    private set
//    fun getData(data:Person){
//        pers = data
//    }
//}
//@Parcelize
//data class Person(val firstName:String, val lastName:String):Parcelable
//
//sealed class Screen(val route:String){
//    object One:Screen(route = "first?dat={dat}")
//    object Two:Screen(route = "second"){
//        fun passId(data:String):String{
//            return "first?dat=$data"
//        }
//    }
//}
//@Composable
//fun Container(navController:NavHostController) {
//    //val uri = "https://www.example.com
//    // val website = "https://www.google.com"
//    androidx.navigation.compose.NavHost(navController = navController,
//        startDestination = Screen.One.route) {
//        composable(
//            route = Screen.One.route,
//            //deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
//            // deepLinks = listOf(navDeepLink { uriPattern = "$website/{dat}" })
//            arguments = listOf(navArgument("dat") {
//                type = NavType.StringType
//                 defaultValue = "Riley"
//            }),
//
//            ) { backStackEntry ->
//            val a = backStackEntry.arguments?.getString("dat")
//            //val b = backStackEntry.arguments?.getString("value")
//            //val c = backStackEntry.arguments?.getInt( "image")
//            //  val d = backStackEntry.arguments
//            Log.d("Args", backStackEntry.arguments?.getString(Screen.One.route).toString())
//            First(navController, a)
//        }
//        composable(route = Screen.Two.route) {
//            Second(navController)
//
//        }
//    }
//}
//    @Composable
//    fun First(navController: NavHostController, data: String? = "") {
//        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text(text = "")
//            Text(
//                text = "First Screen: \n$data",
//                color = Color.Green,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.ExtraBold,
//                modifier = Modifier
//                    .clickable { navController.navigate(Screen.Two.route) }
//            )
//        }
//    }
//
//    @Composable
//    fun Second(navController: NavHostController) {
//        var data by remember { mutableStateOf("") }
//        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text(
//                text = "Second Screen",
//                color = Color.Red,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.ExtraBold,
//                modifier = Modifier
//                    //  .clickable {navController.navigate("first/$data")})
//                    .clickable { navController.navigate(Screen.Two.passId(data = data)) })
//
//            TextField(
//                value = data,
//                onValueChange = { data = it })
//        }
//    }

//
//@Composable
//fun SearchBox(hello: Modifier = Modifier){
//    TextField(
//        value = "",
//        onValueChange = {},
//        modifier = hello
//    )
//
//}
//@Composable
//fun AlignYourBodyRow(
//    modifier: Modifier = Modifier
//) {
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        modifier = modifier
//    ) {
//        items(alignYourBodyData) { item->
//            AlignYourBodyElement(item.first,item.second)
//        }
//    }
//}
//@Composable
//fun SearchBar(modifier: Modifier = Modifier){
//
//    TextField(
//        value = "",
//        onValueChange = {},
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = null)
//        },
//        colors = TextFieldDefaults.textFieldColors(backgroundColor = colors.surface
//        ),
//        placeholder = {
//            Text(text = stringResource(id = R.string.place_holder))
//        },
//        modifier = modifier
//            .fillMaxWidth()
//            .heightIn(56.dp)
//
//    )
//
//}
//@Composable
//fun AlignYourBodyElement(
//    @DrawableRes image:Int,
//    @StringRes text:Int,
//    modifier: Modifier = Modifier
//){
//    Column(
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(image),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = modifier
//                .size(88.dp)
//                .clip(CircleShape)
//        )
//
//        Text(text = stringResource(text),
//            style = MaterialTheme.typography.h3,
//            modifier = modifier.paddingFromBaseline(
//                top = 24.dp, bottom = 8.dp
//            )
//        )
//    }
//}
//@Composable
//fun FavouriteCollectionCard(
//    @DrawableRes image:Int,
//    @StringRes text:Int,
//    modifier:Modifier = Modifier
//){
//    Surface(
//        shape = MaterialTheme.shapes.small,
//        modifier = modifier
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier.size(192.dp)
//        ){
//            Image(
//                painter = painterResource(id = image),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = modifier.size(56.dp),
//
//            )
//            Text(text = stringResource(id = text),
//                style = MaterialTheme.typography.h3,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//        }
//    }
//}
//
//private val alignYourBodyData = listOf(
//    R.drawable.yoga1 to "Inversion",
//    R.drawable.yoga2 to "Stretching",
//    R.drawable.yoga3 to "Tabatha",
//    R.drawable.yoga3 to "Quick Yoga",
//    R.drawable.yoga4 to "Hit",
//    R.drawable.yoga5 to "Stretching",
//    R.drawable.yoga6 to "Tabatha",
//    R.drawable.yoga7 to "Quick Yoga",
//    R.drawable.yoga8 to "Inversion"
//).map { DrawableStringPair(it.first,it.second.toInt()) }
//
//private val collection = listOf(
//    R.drawable.yoga1,
//    R.drawable.yoga2 ,
//    R.drawable.yoga3 ,
//    R.drawable.yoga3,
//    R.drawable.yoga4,
//    R.drawable.yoga5 ,
//    R.drawable.yoga6 ,
//    R.drawable.yoga7,
//    R.drawable.yoga8
//)
//private val collection1 = listOf(
//   "Inversion",
//   "Stretching",
//    "Tabatha",
//    "Quick Yoga",
//    "Hit",
//    "Stretching",
//    "Tabatha",
//   "Quick Yoga",
//   "Inversion"
//)
//
//
//data class Yogaa(
//    val image:Int,
//    val text:String
//)
//object YogaImplement{
//    private val first = Yogaa(R.drawable.yoga4,"Streching")
//    val yogaList = listOf(
//        first,
//        Yogaa(R.drawable.yoga1,"Inversion"),
//        Yogaa(R.drawable.yoga2,"Stretching"),
//        Yogaa(R.drawable.yoga3,"Tabatha"),
//        Yogaa(R.drawable.yoga3,"Quick Yoga"),
//        Yogaa(R.drawable.yoga4,"Hit"),
//        Yogaa(R.drawable.yoga5,"Stretching"),
//        Yogaa(R.drawable.yoga6,"Tabatha"),
//        Yogaa(R.drawable.yoga7,"Quick Yoga"),
//        Yogaa(R.drawable.yoga8,"Inversion")
//
//    )
//}
//
////@Preview(showBackground = true)
////@Composable
////fun Test(){
////    AlignYourBodyElement(
////        text = R.string.sec1,
////        image = R.drawable.yoga7,
////        modifier = Modifier.padding(8.dp)
////    )
////}
////@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
////@Composable
////fun FavoriteCollectionCardPreview() {
////    FavouriteCollectionCard(
////            text = R.string.dec1,
////            image = R.drawable.yoga3,
////            modifier = Modifier.padding(8.dp)
////        )
////}
//@Preview(showBackground = true)
//@Composable
//fun Test(){
//    AlignYourBodyRow()
//}
//
//
//
//
//
//
//
