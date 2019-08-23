import kotlinx.coroutines.*

//Erlang
//In the Project Structure dialog, go to Project Settings | Libraries, press "+" button and select "From Maven..."?
//org.jetbrains.kotlinx:kotlinx-coroutines-core:0.22.3
//Go to Tools → Kotlin → Configure Kotlin Plugin Updates, select “Early Access Preview 1.3”
fun main(args: Array<String>) {
    runBlocking {
        launch {
            val a = async { first() }
            val b = async { second() }
            val sum = a.await() + b.await()
            println("$sum")
        }
    }
}

suspend fun first(): Int{
    delay(1000)
    return 12
}

suspend fun second(): Int{
    delay(2000)
    return 13
}