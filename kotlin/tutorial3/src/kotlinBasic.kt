import java.util.*

fun max(a: Int, b: Int):Int{
    return if(a > b) a else b
}

fun max2(a:Int, b:Int) = if (a > b) a else b

enum class Color(val r: Int, val g: Int, val b:Int){
    RED(255,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255),
    UNKNOWN(-1,-1,-1),

    VOILET(88,88,88),

    YELLOW(1,2,3),

    ORANGE(1,2,4),

    INDIGO(7,7,7)
}

fun stringForColor(color: Color): String{
    return when(color){
        Color.RED->"RED Color"
        Color.GREEN->"GREEN Color"
        Color.BLUE->"BLUE Color"
        Color.UNKNOWN->"Undefined Color"
        Color.VOILET -> "VOILET"
        Color.YELLOW -> "YELLOW"
        Color.ORANGE -> "ORANGE"
        Color.INDIGO -> "INDIGO"
    }
}

fun stringForColor2(color: Color) = when(color){
        Color.RED->"RED Color"
        Color.GREEN->"GREEN Color"
        Color.BLUE->"BLUE Color"
        else->"Undefined Color"
       //
}

fun getWarm(color:Color) = when(color){
    Color.RED, Color.BLUE, Color.GREEN, Color.VOILET->"Warm color"
    else -> "Undefined"
}

fun mix(c1:Color, c2:Color):Color{
    return when(setOf(c1, c2)){
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.YELLOW) -> Color.INDIGO
        else -> Color.UNKNOWN
        //else -> throw Exception("Dirty color")
    }
}

fun mix2(c1:Color, c2:Color):Any{
    return when(setOf(c1, c2)){
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.YELLOW) -> Color.INDIGO
        else -> "Unknown Color"
    //else -> throw Exception("Dirty color")
    }
}

@Suppress("INTEGER_OVERFLOW")
fun overflows(){
    println(2147483647+1)
}

class Person(val name: String, var isMarried:Boolean=false, val gender:String)

class Rectangle(val height:Int, val width: Int){
    //Computed Property
    val isSquare: Boolean
        get(){
            return height == width
        }
    val isSquare2 = height == width

}

fun playWithRectangle(){
    val rectangle = Rectangle(30,40)
    println(rectangle.isSquare)
    println(rectangle.isSquare2)


}

fun playWithPerson() {
    val person = Person("Ali", gender = "male")
    println(person.name)
    println(person.isMarried)
}

fun playWithRandomRectangle(): Rectangle{
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}

interface Expr
class Num(val value:Int):Expr
class Sum(val left:Expr, val right:Expr):Expr

fun eval(e: Expr): Int {
    if(e is Num){
        return e.value
    }
    if(e is Sum){
        return eval(e.left)+eval(e.right)
    }

    throw IllegalArgumentException("Unknown expression........")
}

fun playWithEval(): Int {
    return eval(Sum(Num(10), Num(11)))
}

fun eval2(e: Expr): Int = if (e is Num) e.value
    else if (e is Sum) eval2(e.left) + eval2(e.right)
    else throw IllegalArgumentException("Unknown expression........")



fun playWithEval2(): Int {
    return eval2(Sum(Num(10), Num(11)))
}

fun eval3(e:Expr): Int = when(e){
        is Num -> e.value
        is Sum -> eval3(e.left)+eval3(e.right)
        else -> throw IllegalArgumentException("Unknown expression........")
}


fun playWithEval3(): Int {
    return eval3(Sum(Num(10), Num(11)))
}

fun eval4(e: Expr): Int {

    if(e is Num){
        return e.value
    }
    if(e is Sum){
        return eval(e.left)+eval(e.right)
    }

    throw IllegalArgumentException("Unknown expression........")
}

fun playWithEval4(): Int {
    return eval4(Sum(Num(10), Num(11)))
}


fun main(args: Array<String>){
    print("max:")
    println(max(10, 90))
    print("max2:")
    println(max2(10, 90))
    print("color:")
    println(stringForColor(Color.RED))
    print("color2:")
    println(stringForColor(Color.BLUE))
    print("overflow: ")
    overflows()
    print("mix: ")
    println(mix(Color.YELLOW,Color.BLUE))
    println(mix(Color.YELLOW,Color.GREEN))
    print("mix 2:")
    println(mix(Color.YELLOW,Color.BLUE))
    println(mix(Color.YELLOW,Color.GREEN))
    print("play with person")
    playWithPerson()
    print("is rect: ")
    playWithRectangle()
    print("Rand rect: ")
    println(playWithRandomRectangle().isSquare)

    print("eval: ")
    println(playWithEval())
    print("eval2: ")
    println(playWithEval2())
    print("eval3: ")
    println(playWithEval3())
    print("eval4: ")
    println(playWithEval4())
}