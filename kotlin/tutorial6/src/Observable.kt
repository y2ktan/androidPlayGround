import kotlin.properties.Delegates

var observed = false
var max: Int by Delegates.observable(0) { property, oldValue, newValue ->
    observed = true
}



fun main(args: Array<String>) {
    println(max) // 0
    println("observed is ${observed}") // false

    max = 10
    println(max) // 10
    println("observed is ${observed}") // true
}