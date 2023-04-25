package com.example.mycontacts.ContactUi

import android.annotation.SuppressLint
import android.app.Application
import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData.DetailContactListEvent
import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.ContactEditViewModel
import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.EditContactFactory
//import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.EditContactFactory
import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.EditContactListEvent
import com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer.EditContactUiEvent
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactMainViewModel
//import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainContactFactory
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditContactScreen(PopBackStack:(EditContactUiEvent.AlwaysPopUPToMain)->Unit) {


    val model: ContactEditViewModel = viewModel(
        factory = EditContactFactory(LocalContext.current.applicationContext as Application))

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        model.editContactUiEvent.collect { event ->
            when (event) {

                is EditContactUiEvent.ShowToastMessage -> {
                  //  scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                // is EditContactUiEvent.PopUpToMainOrToDetail -> {PopBackStack()}
                is EditContactUiEvent.AlwaysPopUPToMain -> {
                    PopBackStack(event)
                }
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        val scroll = rememberScrollState()
        Column(Modifier.verticalScroll(scroll)){
            val listOfColors = listOf(
                // Predefined colors like
                //        Color.Green,
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
                backgroundColor = Color(12, 154, 224, 255),
               // backgroundColor = listOfColors.random()


            ) {
                Column(
                    //  verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = {
                                model.editEvents(EditContactListEvent.PopToMainScreenFromEditScreen)
                            },
                        )
                        {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.size(width = 150.dp, height = 5.dp))
                        val cont = LocalContext.current.applicationContext
                        Spacer(modifier = Modifier.size(width = 5.dp, height = 5.dp))
                        Text(text = "Create new contact", fontWeight = FontWeight.ExtraBold,color = Color.White)
                        IconButton(onClick = {
                            model.editEvents(EditContactListEvent.OnSaveContact)
                            if (model.number.isBlank()) {
                                Toast.makeText(cont, "Number can't be empty", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(cont, "Contact saved", Toast.LENGTH_SHORT).show()
                            }
                        })
                        {
//                            Icon(imageVector = Icons.Default.Done, contentDescription = "",
//                                tint = Color.White
//                            )
                            Text(text = "Save", fontWeight = FontWeight.ExtraBold,color = Color.White)
                        }

                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_person_outline_24),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .size(width = 150.dp, height = 150.dp)
                            .background(Color.White)

                    )

                }

            }
            Spacer(modifier = Modifier.size(5.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    // .size(height = 250.dp, width = 0.dp)
                    .padding(5.dp),
                shape = RectangleShape,
                elevation = 2.dp,
                //  backgroundColor = listOfColors.random()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TextField(
                        value = model.name,
                        onValueChange = {
                            model.editEvents(event = EditContactListEvent.OnNameChange(it))
                        },
                        label = { Text(text = "FirstName") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_person_24),
                                contentDescription = "",
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                            leadingIconColor = listOfColors.random())
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    TextField(
                        value = model.surname,
                        onValueChange = {
                            model.editEvents(event = EditContactListEvent.OnSurnameChange(it))
                        },
                        label = { Text(text = "LastName") },
//                        leadingIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.phone),
//                                contentDescription = "",
//                            )
//                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                            leadingIconColor = listOfColors.random())
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    val maxLength = 15
                    TextField(
                        value = model.number,
                        onValueChange = {
                            if (it.length <= maxLength) model.editEvents(event = EditContactListEvent.OnNumberChange(
                                it))
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(text = "Number") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "",
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                            leadingIconColor = listOfColors.random())
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    TextField(
                        value = model.place,
                        onValueChange = {
                            model.editEvents(event = EditContactListEvent.OnPlaceChange(it))
                        },
                        label = { Text(text = "Place") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_place_24),
                                contentDescription = "",
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                            leadingIconColor = listOfColors.random())
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    TextField(
                        value = model.email,
                        onValueChange = {
                            model.editEvents(event = EditContactListEvent.OnEmailChange(it))
                        },
                        label = { Text(text = "Email") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = "",
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                            leadingIconColor = listOfColors.random())
                    )
                }
            }

        }

    }


}


