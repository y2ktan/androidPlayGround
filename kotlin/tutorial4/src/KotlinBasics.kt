import java.util.*
import kotlin.collections.AbstractCollection

//Single responsibilities (localization)
fun iteratingOverMaps(){
    val binaryRep = HashMap<Char, String>()
    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryRep[c] = binary
    }

    for((letter, binary) in binaryRep){
        println("$letter = $binary")
    }
}

//Reducing Cyclomatic Complexity
//Flat is better than nested
//Code readibility improves
//Bug rates will reduce because comprehension is better
//Write code for readability
fun isLetter(c:Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c:Char) = c !in '0'..'9'

//Idiomatic Style Of Programming
//Pythonic style
fun recognize(c: Char) = when(c){
    in '0'..'9'-> "It is digit"
    in 'a'..'z', in 'A'..'Z' -> "It is letter"
    else -> "I don't know"
}

fun creatingCollectionsInKotlin(){
    val set = hashSetOf(1, 70, 90)
    val list = arrayListOf(1, 70, 90)
    val map = hashMapOf(1 to "One", 7 to "Seven", 90 to "Ninety")

    println(set)
    println(set.javaClass)

    println(list)
    println(list.javaClass)

    println(map)
    println(map.javaClass)

}

fun lastCharOld(string:String) = string.get(string.length -1)
//Extension function
fun String.lastChar() = this.get(this.length - 1)
fun Pair<Int,Int>.mean() = (this.first + this.second) / 2.0

fun <T> joinToStringOld(collection: Collection<T>,
                     seperator:String,
                     prefix:String,
                     postfix:String):String{
    val result = StringBuilder(prefix)

    val temp = collection.sortedWith(Comparator<T>{ a, b ->
        when {
            a.toString() > b.toString() -> 1
            a.toString() < b.toString() -> -1
            else -> 0
        }
    })
    for((index, element) in temp.withIndex()){
        if(index > 0) result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

//Available for all of the type
//Coherant/consistency
fun <T> Collection<T>.joinToString(
                        seperator:String=", ",
                        prefix:String="[",
                        postfix:String="]"):String{
    val result = StringBuilder(prefix)
    this.sortedWith(Comparator<T>{ a, b ->
        when {
            a.toString() > b.toString() -> 1
            a.toString() < b.toString() -> -1
            else -> 0
        }
    })
    for((index, element) in this.withIndex()){
        if(index > 0) result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}



fun <T> Collection<T>.join(
        seperator:String=", ",
        prefix:String="[",
        postfix:String="]") = joinToStringOld(this,seperator,prefix,postfix)

open class View{
    open fun click() = println("view clicked!")
}

class Button: View(){
    override fun click() = println("Button clicked")
    fun magic() = println("magic show")
}
// 0 Type matching
// 1 Mapping table look up for message entry (Extension work on type area)
// 2 Lookup order for message netries
// 3 Binding of message to method
// 4 Invocation of method
fun View.showOff() = println("view showOff")
fun Button.showOff() = println("button showOff")

val String.lastChar: Char
  get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length -1)
    set(value: Char){
        this.setCharAt(length - 1, value)
    }

class User(val id: Int, val name:String, val address:String) {
}

fun saveUser1(user:User){
    if(user.name.isEmpty()){
        throw IllegalArgumentException("name is Empty!!!!!!!")
    }
    if(user.address.isEmpty()){
        throw IllegalArgumentException("address is Empty!!!!!!!")
    }
}

fun saveUser(user:User){
    fun validate(user:User, value:String, fieldName:String){
        if(value.isEmpty()){
            throw IllegalArgumentException("$user -> $fieldName")
        } else {
            println("validated ${user.name}'s $fieldName is ok")
        }
    }
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")

}

fun User.save() {
    fun validate(value:String, fieldName:String){
        if(value.isEmpty()){
            throw IllegalArgumentException("${this.name} -> $fieldName")
        } else {
            println("validated ${this.name}'s $fieldName is ok")
        }
    }
    validate(this.name, "Name")
    validate(this.address, "Address")

}

fun main(args: Array<String>){
    iteratingOverMaps()
    println(isLetter('D'))
    println(isNotDigit('D'))
    println(recognize('8'))

    creatingCollectionsInKotlin()

    println(lastCharOld("Penang"))
    println("Penang".lastChar())

    val setOfInt = Pair(1,2)
    println(setOfInt.mean())

    println("Join to string")
    println(joinToStringOld(arrayListOf(1,2,"a","c","b"),":","[","]"))
    println(arrayListOf(1,2,"a","c","b").joinToString(":","[","]"))
    println(arrayListOf(1,2,"a","c","b","A"))

    println("Button click")
    val v:View = Button()
    v.click()
    //Magic is part of view type, so need to cast
    //(v as Button).magic()

    print("v.showOff(): ")
    v.showOff()
    print("(v as Button).showOff(): ")
    (v as Button).showOff()

    print("(Button() as View).showOff(): ")
    (Button() as View).showOff()
    print("(View()).showOff(): ")
    (View()).showOff()

    println("User validation")
    val user1 = User(1, "najib", "pahang")
    val user2 = User(2, "Mark", "penang")
    saveUser(user1)
    saveUser(user2)

    println("\nUser validation with extension")
    user1.save()
    user2.save()
}