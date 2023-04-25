package com.example.practice.NestedNavigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycontacts.R
import androidx.compose.runtime.*
import androidx.compose.ui.text.intl.Locale
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchContact() {

    // Remember a SystemUiController
val systemUiController = rememberSystemUiController()
val useDarkIcons = !isSystemInDarkTheme()

DisposableEffect(systemUiController, useDarkIcons) {
    // Update all of the system bar colors to be transparent, and use
    // dark icons if we're in light theme
    systemUiController.setSystemBarsColor(
        color = Color.Red,
        darkIcons = useDarkIcons
    )
    onDispose{}

    // setStatusBarColor() and setNavigationBarColor() also exist

}


    Column {

        var name by remember { mutableStateOf("") }
        val myList = listOf(
            "Lamin", "Mike", "Michael", "Joe", "Henry", "Binta",
            "Isatou", "Ablie", "Gibreal", "Ambassador", "Maha", "Zaal",
            "Demba", "Kuyateh"
        )


        TextField(
            value = name,
            onValueChange = { name = it },
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
            }
        )
        val countries = myList
        var filteredCountries: List<String>
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            val searchedText = name
            filteredCountries = if (searchedText.isEmpty()) {
                countries
            } else {
                val resultList = mutableListOf<String>()
                for (country in countries) {
                    if (country.lowercase().contains(searchedText.lowercase())) {
                        resultList.add(country)
                    }
                }
                resultList
            }
            items(filteredCountries) { filteredCountry ->
                Text(text = filteredCountry)

            }
        }
    }
}

//        @Composable
//        fun SearchView(state: MutableState<String>) {
//            TextField(
//                value = state,
//                onValueChange = {
//                    state.value = it
//                },
//                modifier = Modifier
//                    .fillMaxWidth(),
//                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
//                leadingIcon = {
//                    Icon(
//                        Icons.Default.Search,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(15.dp)
//                            .size(24.dp)
//                    )
//                },
//                trailingIcon = {
//                    if (state.value != TextFieldValue("")) {
//                        IconButton(
//                            onClick = {
//                                state.value =
//                                    TextFieldValue("") // Remove text from TextField when you press the 'X' icon
//                            }
//                        ) {
//                            Icon(
//                                Icons.Default.Close,
//                                contentDescription = "",
//                                modifier = Modifier
//                                    .padding(15.dp)
//                                    .size(24.dp)
//                            )
//                        }
//                    }
//                },
//                singleLine = true,
//                shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
//                colors = TextFieldDefaults.textFieldColors(
//                    textColor = Color.White,
//                    cursorColor = Color.White,
//                    leadingIconColor = Color.White,
//                    trailingIconColor = Color.White,
//                    backgroundColor = colorResource(id = R.color.purple_200),
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                )
//            )
//
//    }
//@Preview(showBackground = true)
//@Composable
//fun SearchViewPreview() {
//    val textState = remember { mutableStateOf(TextFieldValue("")) }
//    SearchView(textState)
//}