package org.example

class Calculator{

    companion object{
        val exceptionMessagesDictionary : MutableMap<String, String> = mutableMapOf(
            "DivideByZero" to "Cannot divide by zero",
            "SumInfinite" to "The sum of arguments is Infinite",
            "SubtractInfinite" to "The subtract of arguments is Infinite",
            "MultiplyInfinite" to "The product of arguments is Infinite",
            "DivideInfinite" to "The quotie of arguments is Infinite",
            "NullArgument" to "Arguments cannot be anything but a number"
        )

        fun edgeRestriction(result : Double) : Boolean{
            return result.isFinite()
        }

        fun isNotANaN(a : Double, b : Double) : Boolean{
            return !a.isNaN() || !b.isNaN()
        }

        fun divideByZeroRestriction(divisor : Double) : Boolean{
            return divisor != 0.0
        }
    }

    fun add(a : Double, b : Double) : Double{
        require(edgeRestriction(a + b)){ exceptionMessagesDictionary["SumInfinite"] ?: "IllegalArgument"}
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NullArgument"] ?: "IllegalArgument"}
        return a + b
    }

    fun subtract(a : Double, b : Double) : Double{
        require(edgeRestriction(a - b)){ exceptionMessagesDictionary["SubtractInfinite"] ?: "IllegalArgument"}
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NullArgument"] ?: "IllegalArgument"}
        return a - b;
    }

    fun multiply(a : Double, b : Double) : Double{
        require(edgeRestriction(a * b)){ exceptionMessagesDictionary["MultiplyInfinite"] ?: "IllegalArgument"}
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NullArgument"] ?: "IllegalArgument"}
        return a * b;
    }

    fun divide(a : Double, b : Double) : Double{
        require(divideByZeroRestriction(b)) { exceptionMessagesDictionary["DivideByZero"] ?: "IllegalArgument" }
        require(edgeRestriction(a / b)){exceptionMessagesDictionary["DivideInfinite"] ?: "IllegalArgument"}
        require(isNotANaN(a, b)){exceptionMessagesDictionary["NullArgument"] ?: "IllegalArgument"}
        return a / b
    }
}