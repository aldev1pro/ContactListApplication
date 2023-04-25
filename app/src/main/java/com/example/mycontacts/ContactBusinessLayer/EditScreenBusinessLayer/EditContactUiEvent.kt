package com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer

sealed class EditContactUiEvent{

    data class ShowToastMessage(val message:String):EditContactUiEvent()
    data class AlwaysPopUPToMain(val path:String):EditContactUiEvent()

}
