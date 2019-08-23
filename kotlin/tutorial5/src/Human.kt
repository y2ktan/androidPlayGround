//Composition design...strategy design
interface Superpower{
    fun fly() {println("never fly")}
    fun saveWorld() {println("never saveworld")}
}
class Spiderman: Superpower{
    override fun fly(){
        println("Fly like spiderman")
    }
    override fun saveWorld(){
        println("Save world like spiderman")
    }
}

class Superman: Superpower{
    override fun fly(){
        println("Fly like superman")
    }
    override fun saveWorld(){
        println("Save world like superman")
    }
}

class Heman: Superpower{
    override fun fly(){
        println("Fly like heman")
    }
    override fun saveWorld(){
        println("Save world like heman")
    }
}

class Wonderwoman: Superpower{
    override fun fly(){
        println("Fly like wonderwoman")
    }
    override fun saveWorld(){
        println("Save world like wonderwoman")
    }
}

class Human {
    lateinit var  power: Superpower
    fun fly(){
        power.fly()
    }

    fun saveWorld(){
        power.saveWorld()
    }
}

object SingletonHuman{
    lateinit var  power: Superpower
    fun fly(){
        power.fly()
    }

    fun saveWorld(){
        power.saveWorld()
    }
}

fun main(args: Array<String>){

//    val h = Human()
//    h.power = Spiderman()
//    h.fly()
//    h.saveWorld()
//
//    h.power = Superman()
//    h.fly()
//    h.saveWorld()
//
//    h.power = Heman()
//    h.fly()
//    h.saveWorld()
//
//    h.power = Wonderwoman()
//    h.fly()
//    h.saveWorld()
    val ironman:Superpower = object:Superpower {
        override fun fly() {
            super.fly()
        }

        override fun saveWorld() {
            super.saveWorld()
        }
    }
    SingletonHuman.power = Spiderman()
    SingletonHuman.fly()
    SingletonHuman.saveWorld()
}