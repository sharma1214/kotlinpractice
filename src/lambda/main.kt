package lambda

// Higher order function  -  When a function takes function as a param or function return function is called higher order function
// Lambda function is  function without a name
// You can create an object without even creating a class by using "object" declaration.
// IF lambda is having only one param you can replace param with  "it" keyword
// function type"(Int,Int)->Int" of lambda function should be same as function which takes lambda as param
// if lambda function is the last param of function, we can move last param (lambda function) out of calling function

fun main(){

    val obj = LambdaExample()
    var value1 = obj.addNumber(2,3)
    println("$value1")

    // creating object of an interface (Since we can not create and object of an interface so we have used object keyword)
    // and passing an interface as a param
    var value2 = obj.addNumber(3,4,object : MyInterface {
        override fun execute(sum: Int) {
            println("Sum of two no is $sum")
        }
    })

    // defining lambda function1
    var value3 = { a:Int,b:Int -> a+b }

    // defining lambda function2
    var value4:(Int,Int)->Int = { a,b -> a+b }

    // passing lambda as a param for addition of two number
    obj.addNumber(4,5,value3)// or you can pass value4 as well

    // another way of passing lambda as a param for addition of two number
    obj.addNumber(4,5,{ a,b ->a+b})
    //or if lambda is a last param of function then we can write lambda outside of parenthesis
    obj.addNumber(4,5) { x, y -> x + y } // we can give any param name instead of a and b like x and y
    //or we can pass normal function also as a param
    obj.addNumber(4,5,::sum)

    //obj.square(25,{ a -> a*a.toLong()})
    //obj.square(25) { a -> a * a.toLong() }
    obj.square(25){ it*it.toLong() } // Converting Int to Long
}

fun sum(a:Int,b:Int):Int{
    return a.plus(b)
}
class LambdaExample {

    fun addNumber(a:Int,b:Int):Int{
        return a+b
    }

    //passing interface as a param and need to create the body for interface from calling place
    fun addNumber(a:Int,b:Int,listener:MyInterface){
        var add = a+b
        listener.execute(add)
    }

    //passing lambda as a last param
    fun addNumber(a:Int,b:Int,fn:(Int,Int)->Int){
        val sum = fn(a,b)
        println("Add number from lambda function is $sum")
    }

    // lambda takes one param
    fun square(a:Int,fn:(Int)->Long){
        fn(a)
    }
}

interface MyInterface{
    fun execute(sum:Int)
}
