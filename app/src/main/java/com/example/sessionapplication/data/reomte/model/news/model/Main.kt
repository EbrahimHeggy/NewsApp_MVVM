//package com.example.retrofitapp.data.remote.model
//
//import kotlinx.coroutines.async
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//suspend fun networkCall() : Int{
//    delay(1000L)
//    return 1
//}
//suspend fun networkCall2() : Int{
//    delay(1000L)
//    return 1
//}
//abstract class Calls() {
//  fun  onCalls(message : String) :Calls{}
//     fun callsFeiled(message : String){}
//}
//class Video () : Calls(){
//
//    fun setCall(isCalled : Boolean){
//
//     if (!isCalled){
//
//         connect(onCalls("asd"))}
//    }
//    fun connect(calls: Calls) {
//        if (calls == Calls::onCalls)  calls.onCalls("Success")
//        if (calls == Calls::callsFeiled)  calls.onCalls("Error")
//
//    }
//
//}
//suspend fun awaitConnection() : Boolean{
//    val video = Video()
//   video.setCall(false)
//    return suspendCoroutine {continuation ->
//        video.connect(object : Calls() {
//            override fun onCalls(message: String) {
//               continuation.resume(true)
//            }
//
//            override fun callsFeiled(message: String) {
//               continuation.resume(false)
//            }
//
//        })
//    }
//}
//fun main() {
//     runBlocking {
////        val one = async {  networkCall()}
////         val two = async {  networkCall2()}
////         println(one.await().toString())
////         println(two.await().toString())
//val x = awaitConnection()
//         println(x)
//
//     }
//}