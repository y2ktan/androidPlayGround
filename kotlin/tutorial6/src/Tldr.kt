//DSL entry point
fun user(init: User.()-> Unit): User{
    val user = User("lee")
    user.init()
    return user
}

fun user2(init: User.()-> Unit) = User("A").apply(init)

class User(val name:String)

//fun divide(a:Int, b:Int):Nothing{
//    if(b==0) {
//        throw Exception("divide by zero")
//    } else {
//        return a/b
//    }
//}

data class Address(var street:String="")

data class user3(val name:String, val age:Int){
    fun address(init:Address.()-> Unit){
        val address = Address().apply(init)
    }
}

fun main(args: Array<String>) {
    val receiver: Person.()->Unit = {println("hello $name")}
    //same as conventional lambda calling
    receiver(Person("Tan", 12))
    //More elegant way calling -> extension method
    val p = Person("ang", 123).receiver()


    //Conventional lambda expression
    val someone: (Person)->Unit = {p -> println("hi ${p.name}")}
    someone(Person("Tan", 112))


    val myUser = user(){
        println("init user")
    }

    val myUser2 = user2 { println("init user") }

    val myuser3 = user3("a", 12).address {
        this.street="16 Jalan Hang tuah"
        user {

        }
    }
}
