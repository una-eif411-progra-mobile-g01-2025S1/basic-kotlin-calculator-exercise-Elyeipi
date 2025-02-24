package org.example

/*
*
* Calculator performs basic mathematical operations with 2 numbers of  type Double.
*
* @Constructor Makes an instance of Calculator
* */
class Calculator{

    companion object{

        /*
        *
        * A Dictionary which contains every exception message used in this Calculator.
        *
        * Exception Messages:
        * - "DivideByZero": User tries to divide by zero
        * - "...Infinite" : The result of the operation tends to infinite
        * - "NaNArgument" : One or two arguments are Not A Number (NaN)
        * */
        val exceptionMessagesDictionary : MutableMap<String, String> = mutableMapOf(
            "DivideByZero" to "Cannot divide by zero",
            "SumInfinite" to "The sum of arguments is Infinite",
            "SubtractInfinite" to "The subtract of arguments is Infinite",
            "MultiplyInfinite" to "The product of arguments is Infinite",
            "DivideInfinite" to "The quotie of arguments is Infinite",
            "NaNArgument" to "Arguments cannot be anything but a number"
        )


        /*
        * Verifies if result is Finite
        * @param The result of the operation
        * @return A Boolean that verifies if the result is finite. Returns false if it is infinite
        * */
        fun edgeRestriction(result : Double) : Boolean{
            return result.isFinite()
        }

        /*
        * Verifies if arguments are Not A Number (NaN)
        * @param First operand
        * @param Second operand
        * @return A Boolean that verifies if arguments are numbers. Returns false if one or two arguments are NaN
        * */
        fun isNotANaN(a : Double, b : Double) : Boolean{
            return !a.isNaN() && !b.isNaN()
        }

        /*
        * Verifies if divisor is not zero
        * @param Divisor
        * @return A Boolean that verifies if divisor is not zero. Returns false if divisor is zero
        * */
        fun divideByZeroRestriction(divisor : Double) : Boolean{
            return divisor != 0.0
        }
    }

    /*
    * Adds two numbers
    * @param First number to add
    * @param Second number to add
    * @return The result of the addition
    * @throws IllegalArgument if any of the numbers is NaN or the addition tends to Infinite
    * */
    fun add(a : Double, b : Double) : Double{
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NaNArgument"] ?: "IllegalArgument"}
        require(edgeRestriction(a + b)){ exceptionMessagesDictionary["SumInfinite"] ?: "IllegalArgument"}
        return a + b
    }

    /*
    * Subtract two numbers
    * @param First number to subtract
    * @param Second number to subtract
    * @return The result of the subtraction
    * @throws IllegalArgument if any of the numbers is NaN or subtract tends to Infinite
    * */
    fun subtract(a : Double, b : Double) : Double{
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NaNArgument"] ?: "IllegalArgument"}
        require(edgeRestriction(a - b)){ exceptionMessagesDictionary["SubtractInfinite"] ?: "IllegalArgument"}
        return a - b;
    }

    /*
    * multiply two numbers
    * @param First number to multiply
    * @param Second number to multiply
    * @return The product of the multiplication
    * @throws IllegalArgument if any of the numbers is NaN or the product tends to Infinite
    * */
    fun multiply(a : Double, b : Double) : Double{
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NaNArgument"] ?: "IllegalArgument"}
        require(edgeRestriction(a * b)){ exceptionMessagesDictionary["MultiplyInfinite"] ?: "IllegalArgument"}
        return a * b;
    }

    /*
    * Divide two numbers
    * @param Dividend
    * @param Divisor
    * @return The result of the addition
    * @throws IllegalArgument if any of the numbers is NaN, divisor is zero or the quotie tends to Infinite
    * */
    fun divide(a : Double, b : Double) : Double{
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NaNArgument"] ?: "IllegalArgument"}
        require(divideByZeroRestriction(b)) { exceptionMessagesDictionary["DivideByZero"] ?: "IllegalArgument" }
        require(edgeRestriction(a / b)){exceptionMessagesDictionary["DivideInfinite"] ?: "IllegalArgument"}
        return a / b
    }
}