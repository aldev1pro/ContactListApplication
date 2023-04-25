package com.example.practice

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"
sealed class MyRoute(val route:String){
    object Home: MyRoute(route = "home")
//    object Detail:MyRoute(route = "detail/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}"){
//        //For passing only one argument
////        fun detailScreenPassId(id:Int):String{
////            //this works fine...
////           // return "detail/$id"
////            //improved version....
////            return this.route.replace(oldValue = DETAIL_ARGUMENT_KEY, newValue = id.toString())
////        }
//        //For passing multiple arguments
//        fun detailScreenPassId(id:Int,name:String):String{
//            return "detail/$id/$name"
//        }
//
//    }
    //Optional argument
    object Detail: MyRoute(route = "detail?id={id}&name={name}"){
        fun detailScreenPassId(id:Int = 0,name:String = "Mike"):String{
            return "detail?id = $id&name= $name"
        }
    }
}