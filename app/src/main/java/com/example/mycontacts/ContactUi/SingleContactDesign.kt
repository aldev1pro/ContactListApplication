package com.example.mycontacts.ContactUi

//import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainContactFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.R
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt


@Composable
fun SingleContactDesign(contact: Contact,modifier:Modifier = Modifier){

    Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(start = 50.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically

    ) {


       val listOfColors = listOf(
        // Predefined colors like
        Color.Green,
      //  Color.Black,
        Color.Red,
        Color.Yellow,
        //Custom color hex:
        Color(0xFFF0670A),
        //Custom color RGB
        Color(12, 154, 224, 255),
        Color(241, 7, 230, 255),
        Color(146, 130, 116, 255),
        Color(0, 255, 179, 255)
    )

        IconButton(onClick = { }, enabled = false, modifier = Modifier.background(
            color = listOfColors.random(),
            CircleShape) ) {
            Text(text = "${contact.firstName?.get(0)}",fontWeight = FontWeight.ExtraBold)
          //  Text(text = "$contact")
        }
        Spacer(modifier = Modifier.size(15.dp))

         Text(text = contact.firstName + " " + contact.lastName )

      }

}
