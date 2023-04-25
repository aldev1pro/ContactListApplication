package com.example.mycontacts.ContactUi


import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.EditContactListEvent
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactMainViewModel
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainContactFactory
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainContactListEvent
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainUiEvent
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@kotlin.OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContactScreen(onNavigate: (MainUiEvent.NavigateToAnyScreen)-> Unit) {


    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }


        val model: ContactMainViewModel = viewModel(
            factory = MainContactFactory(LocalContext.current.applicationContext as Application))

        val myData by model.displayContact.collectAsState(initial = emptyList())

        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(key1 = true) {
            model.mainContactUiEvent.collect { contact ->
                // MainUiEvent.NavigateToAnyScreen(contact.toString())
                when (contact) {
                    is MainUiEvent.NavigateToAnyScreen -> onNavigate(contact)
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Contact List",
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp

                            )
                        }
                    })

            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        model.mainEvents(MainContactListEvent.OnAddContact)
                    },
                    backgroundColor = Color.Blue,
                ) {
                    Icon(imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        tint = Color.White)
                }
            }
        ) {
            var name by remember { mutableStateOf("") }
            val hello: List<Contact> = myData.sortedBy { it.firstName + it.lastName }
            // var contact = hello
            var filteredContact: List<Contact>
            val newList: MutableList<Contact> = mutableListOf()
            val searchedName = name

            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Spacer(modifier = Modifier.size(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30.dp)),
                        label = {
                            Text(text = "Search contact")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        },
                        trailingIcon = {
                            if (name != "") {
                                IconButton(onClick = {
                                    name = ""
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "")
                                }
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.LightGray,
                            leadingIconColor = Color.Blue,
                            unfocusedIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White

                        )
                    )
                }

                filteredContact = if (searchedName.isEmpty()) {
                    hello
                } else {
                    for (cont in hello) {
                        if (cont.firstName.plus(cont.lastName).contains(name, ignoreCase = true)) {
                        //  val a: Unit =  Text("$cont", fontWeight = FontWeight.ExtraBold)
                            newList.add(cont)
                        }
                    }
                    newList
                }

                Spacer(modifier = Modifier.size(10.dp))

                LazyColumn(contentPadding = PaddingValues(5.dp)) {
                    val sorted = filteredContact.sortedBy { it.firstName }
                    val sticky = sorted.groupBy {
                        it.firstName?.get(0)
                    }
                    sticky.forEach { (initial, contact) ->
                        stickyHeader {
                            Divider()
                            Text(
                                text = initial.toString(),
                                fontWeight = FontWeight.Light,
                                fontSize = 25.sp,
                                color = Color.Blue,
                                textAlign = TextAlign.Center,
                            )


                        }
                        items(contact) { contacts ->
                            SingleContactDesign(
                                contact = contacts,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        model.mainEvents(MainContactListEvent.OnViewDetailContact(
                                            contacts))
                                    }

                            )
                        }
                    }

                }

            }
        }

}

