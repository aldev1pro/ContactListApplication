package com.example.mycontacts.ContactUi

import android.annotation.SuppressLint
import android.app.Application
import android.provider.ContactsContract
import android.util.Log.d
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.ContactDetailViewModel
import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.DetailContactListEvent
import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.DetailContactUiEvent
import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.DetailFactory
//import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.DetailFactory
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainUiEvent
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.ContactsData.Contact.*
import com.example.mycontacts.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailContactScreen(PopUP: (DetailContactUiEvent.NavigateToEditContactScreenWithData) -> Unit) {

    val model: ContactDetailViewModel = viewModel(
        factory = DetailFactory(
            LocalContext.current.applicationContext as Application)
    )

    val scaffold = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        model.detailContactUiEvent.collect { event ->
            when (event) {
                is DetailContactUiEvent.ShowSnackbar -> {
                    val result = scaffold.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        model.detailEvents(DetailContactListEvent.OnUndoContact)
                    }
                }
                is DetailContactUiEvent.NavigateToEditContactScreenWithData -> {
                    PopUP(event)
                }
                else -> Unit
            }
        }
    }

    Scaffold(scaffoldState = scaffold) {
        Column {
            val listOfColors = listOf(
                // Predefined colors like
                Color.Green,
     //           Color.Black,
                Color.Red,
     //           Color.Yellow,
                //Custom color hex:
                Color(0xFFF0670A),
                //Custom color RGB
                Color(12, 154, 224, 255),
                Color(241, 7, 230, 255),
                Color(146, 130, 116, 255),
            //    Color(0, 255, 179, 255))
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 250.dp, width = 0.dp)
                    .padding(5.dp),
                shape = RectangleShape,
                elevation = 2.dp,
                backgroundColor = Color(12, 154, 224, 255)
                // backgroundColor = listOfColors.random()


            ) {
                Column(
                    //  verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = {
                            model.detailEvents(DetailContactListEvent.OnBackPressToMainContact)
                        }, )
                        {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.size(width = 150.dp, height = 5.dp))
                        IconButton(onClick = {
                            model.restoreContact?.let { it1 ->
                                DetailContactListEvent.OnEditScreenNavigate(it1)
                            }?.let { it2 -> model.detailEvents(it2) }
                        })
                        {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.size(width = 5.dp, height = 5.dp))
                        IconButton(onClick = {
                            model.restoreContact?.let { it1 ->
                                DetailContactListEvent.OnDeleteContact(contact = it1)
                            }
                                ?.let { it2 -> model.detailEvents(it2) }
                        })
                        {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "",
                                tint = Color.White
                            )
                        }

                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_person_outline_24),
                        contentDescription = "",
                        modifier = Modifier.clip(RoundedCornerShape(50.dp)).size(width = 150.dp, height = 150.dp)
                            .background(Color.White)

                    )

                    Text(text = model.firstname + " " + model.surname,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color.White
                    )

                }

            }
            Spacer(modifier = Modifier.size(5.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 0.dp)
                    .padding(15.dp),
                elevation = 3.dp

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Icon(painter = painterResource(id = R.drawable.phone), contentDescription = "",
                        tint = listOfColors.random()

                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Column {
                        Text(text = model.number)
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = "Mobile", color = Color.Gray)

                    }

                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 0.dp)
                    .padding(15.dp),
                elevation = 3.dp

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_outline_place_24),
                        contentDescription = "",
                        tint = listOfColors.random()
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Column {
                        Text(text = model.place)
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = "Place", color = Color.Gray)

                    }

                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 0.dp)
                    .padding(15.dp),
                elevation = 3.dp

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Icon(painter = painterResource(id = R.drawable.email), contentDescription = "",
                        tint = listOfColors.random()
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Column {
                        Text(text = model.email)
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = "Email", color = Color.Gray)

                    }

                }
            }

//            Button(onClick = {
//                model.restoreContact?.let { it1 -> DetailContactListEvent.OnDeleteContact(contact = it1) }
//                    ?.let { it2 -> model.detailEvents(it2) }
//            }) {
//                Text(text = "Delete Contact")
//            }
//            Button(onClick = {
//                model.restoreContact?.let { it1 ->
//                    DetailContactListEvent.OnEditScreenNavigate(it1)
//                }?.let { it2 -> model.detailEvents(it2) }
//            }) {
//                Text(text = "Edit Contact")
//            }
//        }


        }
    }
}
