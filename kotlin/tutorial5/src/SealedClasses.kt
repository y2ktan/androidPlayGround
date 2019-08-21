//Static scenario
//1. as Constant using static at class level - Enum/sealed classes/File level members
//2. Singleton - special case, factory methods
//3. static main - Java messed up
//4. Utility methods - Java messed up
//5. Inner classed to made nexted class - Java messed up
//6  Object Counters - Object manager class
//7. Object communication - pub, sub
//8. Visibility of Context and lifetime
//9. Unittest

enum class Color(val r: Int, val g: Int, val b:Int){
    RED(255,0,0), GREEN(0,255,0), BLUE(0,0,255)
}
sealed class Expr {

}
class Num(val value:Int): Expr()
class Sum(val left:Expr, val right:Expr): Expr()

//sealed class Expr {
//    class Num(val value:Int): Expr()
//    class Sum(val left:Expr, val right:Expr): Expr()
//}

fun eval(e:Expr):Int =
        when(e){
            is Num -> e.value
            is Sum -> eval(e.right) + eval(e.left)
        }

class User(val nickname:String, val isSubscribed:Boolean = true)

fun objectCreation() {
    val alice = User("Alice")
    println("${alice.nickname} is subscribed: ${alice.isSubscribed}")
    val bob = User("Bob", false)
    println("${bob.nickname} is subscribed: ${bob.isSubscribed}")
    val carol = User("Carol", true)
    println("${carol.nickname} is subscribed: ${carol.isSubscribed}")
}

class LengthCounter{
    var counter:Int = 0
        private set

    fun addWord(word:String){
        counter += word.length
    }
}

fun changingAccesorVisibility(){
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)
}

fun main(args: Array<String>){
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    changingAccesorVisibility()
}