package com.example.practice.NestedNavigation

import android.app.Application
import android.util.Log
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class Destination(val route:String){
    object bread:Destination(route = "bread")
    object sugar:Destination(route = "sugar")
    object verify:Destination(route = "verify")
}
sealed class ContainList{
    object ToBreadScreen:ContainList()
    object ToSugarScreen:ContainList()
    object ToVerifyScreen:ContainList()
}
sealed class ContainUiEvent{
    data class BreadEvent(val route:String):ContainUiEvent()
    data class SugarEvent(val route:String):ContainUiEvent()
    data class VerifyEvent(val route:String):ContainUiEvent()

    //Not In use for now....
    data class NavigateToAny(val route:String):ContainUiEvent()
}
class ContainViewModel(appli:Application,  savedState: SavedStateHandle) :ViewModel(){

    var data:String? by mutableStateOf(null)
        private set
    var name by mutableStateOf(savedState.get("mydata")?:"Nothing")
        private set


//    init {
//        val getData = savedState.get<String>("mydata")
//        getData.let { action ->
//            if (action != null) {
//                name = action
//                this@ContainViewModel.name = action
//            }
//
//        }
//    }


   private var _myChannel = Channel<ContainUiEvent>()
    val myChannel = _myChannel.receiveAsFlow()

    var hold by mutableStateOf("")
    private set
    fun holdData(data:String):String{
         hold = data
        return data
    }


    fun Testing(event:ContainList){

        when(event){
            is ContainList.ToBreadScreen->{
                sendToChannel(ContainUiEvent.BreadEvent(route = Destination.bread.route+"?op=$hold"))
            }
            is ContainList.ToSugarScreen->{
                sendToChannel(ContainUiEvent.SugarEvent(route = Destination.sugar.route))
            }
            else -> {Unit}
        }
    }
    private fun sendToChannel(UI:ContainUiEvent){
        viewModelScope.launch {
            _myChannel.send(UI)
        }
    }

}

class VerifyViewModel(val appli:Application, val savedState: SavedStateHandle):ViewModel(){

   // var hello by mutableStateOf(savedState.get("Mike")?:"ok")


    var data by mutableStateOf("")
    private set

    var hello by mutableStateOf(data)
        private set

    private var _myChannel = Channel<ContainUiEvent>()
    val myChannel = _myChannel.receiveAsFlow()

    fun verify(event:ContainList){
        when(event){
            is ContainList.ToVerifyScreen ->{
                sendToChannel(ContainUiEvent.VerifyEvent(route = Destination.bread.route + "?mydata=$data"))
            }
            else -> {}
        }
    }


    private fun sendToChannel(UI: ContainUiEvent){
        viewModelScope.launch {
            _myChannel.send(UI)
        }
    }

    fun toBreadData(a:String):String{
        data = a
        return data
    }

   var number by mutableStateOf(0)
    fun ADD(){
        savedState.set(key = "love",number)
        number++
    }

}
class ContainViewModelFactory(private val appli: Application):AbstractSavedStateViewModelFactory(){
//class ContainViewModelFactory(private val savedState:SavedStateHandle):ViewModelProvider.NewInstanceFactory(){

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return ContainViewModel(savedState = savedState) as T
//    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
       // TODO("Not yet implemented")
        return ContainViewModel(appli = Application() , handle) as T
    }
}
class VerifyFactory(private val appli: Application):AbstractSavedStateViewModelFactory(){
// class VerifyFactory(private val savedState:SavedStateHandle):ViewModelProvider.Factory {

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return VerifyViewModel(savedState = savedState) as T
//    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
       // TODO("Not yet implemented")
        return VerifyViewModel(appli = Application(), handle) as T
    }
}

@Composable
fun Contain(nav:NavHostController) {
       // NavHost(navController = nav, startDestination = Destination.bread.route + "?op={mydata}", builder = {
                 NavHost(navController = nav, startDestination = Destination.verify.route, builder = {

                composable(route = Destination.bread.route + "?mydata={mydata}",
                    arguments = listOf(navArgument("mydata") {
                        NavType.StringType
                        defaultValue = "Rock Star Coder"
                    })
                ) {
                    // Bread(nav,it.arguments?.getString("mydata"),)
                    //  Bread(nav)
                    Bread(nav = { res ->
                        Log.d("verify", "$res")
                        // NavigateToAny(route=sugar)
                        nav.navigate(res.route)
                    },
                        it.arguments?.getString("mydata")
                    )
                }
                composable(route = Destination.sugar.route) {
                    //  Sugar(nav)
                    Sugar(nav = { res ->
                        nav.navigate(res.route)
                    })
                }
                composable(route = Destination.verify.route) {
                    Verify(nav = {res->
                        nav.navigate(res.route)
                    })
                }
            })
    }

@Composable
//fun Bread(nav: NavHostController, hel: String?){
//fun Bread(nav:NavHostController){
    fun Bread(nav: (ContainUiEvent.SugarEvent) -> Unit, hel: String?) {

       // val model:ContainViewModel = viewModel()
        val model: ContainViewModel = viewModel(factory = ContainViewModelFactory(appli = LocalContext.current.applicationContext as Application))
        LaunchedEffect(key1 = true) {
            model.myChannel.collect { event ->
                when (event) {
                    is ContainUiEvent.SugarEvent -> {
                        nav(event)
                    }

                    else -> {}
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)

        ) {

            //model.data?.let { Text(text = it) }
            Text(text = model.name)
            Divider()
            Text(text = "Bread $hel",
                modifier = Modifier.clickable {
                    //  nav.navigate(Destination.sugar.route)
                    model.Testing(ContainList.ToSugarScreen)
                    Log.d("hello", "Clicked to SugarScreen")
                    //model.name?.let { Log.d("favorite", it) }
                }
            )
        }
    }

@Composable
//fun Sugar(nav:NavHostController){
    fun Sugar(nav: (ContainUiEvent.BreadEvent) -> Unit) {
        var data by remember { mutableStateOf("") }
        val model: ContainViewModel = viewModel(factory = ContainViewModelFactory(LocalContext.current.applicationContext as Application))
        LaunchedEffect(key1 = true) {
            model.myChannel.collect { event ->
                when (event) {
                    is ContainUiEvent.BreadEvent -> {
                        nav(event)
                    }
//                is ContainUiEvent.BreadEvent->{
//                    nav(ContainUiEvent.BreadEvent(event.route))
//                }
                    else -> {}
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        ) {

            Text(text = "Sugar",
                modifier = Modifier.clickable {
                    // nav.navigate(Destination.bread.route)
                    model.Testing(ContainList.ToBreadScreen)

                }
            )
            model.holdData(data = data)
            TextField(value = data, onValueChange = { data = it })
        }

    }

    @Composable
    fun Verify(nav: (ContainUiEvent.VerifyEvent) -> Unit) {

        val model: VerifyViewModel = viewModel(factory = VerifyFactory(LocalContext.current.applicationContext as Application))
       //val  model:VerifyViewModel = viewModel()

        var name by remember{ mutableStateOf("")}

        LaunchedEffect(key1 = true) {
            model.myChannel.collect { event ->
                when (event) {
                    is ContainUiEvent.VerifyEvent -> {
                        nav(event)
                    }

                    else -> {}
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Text(text = "Verifying data",
                modifier = Modifier.clickable {
                    // nav.navigate("bread?op=programmer")
                    // nav.navigate(model.toBread())
                    model.verify(ContainList.ToVerifyScreen)

                })
            Spacer(modifier = Modifier.size(8.dp))
            TextField(value = name, onValueChange = {name = it})
            Button(onClick = { model.ADD() }) {
                Text(text = "ADD ${model.number}")
            }
            model.toBreadData(name)




        }


}