import java.io.Serializable

//Definition Driven Design
//Less coupling <-> Unitness behaviour
//Interface <-> design behaviour
//Class <-> design by state
//Polymorphsim - default arguments
fun something(a:Int = 0, b:Int = 0) = a + b
fun something3(a:Int = 0, b:Int, c:Int = 10) = a + b + c

//Polymorphism - Mechanism is passing behaviour to behaviour
fun sum(a:Int, b:Int) = a+b
fun sub(a:Int, b:Int) = a-b
fun mul(a:Int, b:Int) = a*b

//Integration test -> only one sufficient
fun calculator(a: Int, b: Int, calcOperation:(Int,Int)-> Int): Int{
    return calcOperation(a,b)
}

//Polymorphism - Mechanism is passing behaviour to behaviour
fun getAverage(vararg input:Int):Float{
    var sum =0.0f
    for(item in input){
        sum = sum + item
    }
    return (sum/input.size)
}

//interface Clickable {
//    fun click()
//}
//
//class Button: Clickable{
//    override fun click() {
//        println("button is clicked")
//    }
//}

//Button().click()
interface Clickable{
    fun click()
    fun showOff() = println("I am clickable")
}

interface Focusable{
    fun setFocus(b: Boolean) = println("Focusable setFocus!")
    fun showOff() = println("I am focus")
}

class Button: Clickable, Focusable{
    override fun click() {
        println("I am button!")
    }

    override fun showOff() {
        //can explicitly determine which super to be invoked
        super<Focusable>.showOff()
        super<Clickable>.showOff()
    }
}


class RichButton: Clickable{
    override fun click() {

    }
    fun disable() {}
    open fun animate(){}

}

interface State: Serializable

interface Persistable{
    fun saveInstanceState(state:State){}
    fun restoreInstanceState(): State?
}

class Button1:Persistable{
    var state:State?  = null
    override fun restoreInstanceState(): State? {
        println("Restore require state") //deserialization
        return this.state
    }

    override fun saveInstanceState(state:State){
        println("Persist require state") //serialization
        this.state = state
    }


}

class ButtonState(val clicked: Boolean): State

class Outer{
    //inner class capture the outer class
    //kotlin default is not inner class but nested class
    inner class Inner{
        //...
    }
}

fun main(args: Array<String>){
    println(calculator(10,20, ::sum))
    println(calculator(10,20, ::sub))
    println("Average: ${getAverage(10,20,30)}")
    println("Average: ${getAverage(10,20,30,40)}")
    println(something(b=11))
    println(something3(b=12))

    val function: (Int, Int) -> Int = ::sum
    print("function: ")
    println(function(1,2))

    val calculatorObj:(Int, Int, (Int, Int)->Int)->Int = ::calculator
    println(calculatorObj(10,2, ::sum))

    //Interfaca in kotlin
    val button = Button()
    button.click()
    button.showOff()


    val buttonObject = Button()
    val state:State = ButtonState(clicked = true)
    
}