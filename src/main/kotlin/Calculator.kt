package org.example

class Calculator{
    private fun edgeRestriction(result : Double) : Boolean{
        return result.isFinite()
    }

    private fun divideByZeroRestriction(divisor : Double) : Boolean{
        return divisor != 0.0
    }

    fun add(a : Double, b : Double) : Double{
        require(edgeRestriction(a + b)){"The sum of arguments over edges the limits of Double numbers"}
        return a + b
    }

    fun subtract(a : Double, b : Double) : Double{
        require(edgeRestriction(a - b)){"The subtract of arguments over edges the limits of Double numbers"}
        return a - b;
    }

    fun multiply(a : Double, b : Double) : Double{
        require(edgeRestriction(a * b)){"The product of arguments over edges the limits of Double numbers"}
        return a * b;
    }

    fun divide(a : Double, b : Double) : Double{
        require(divideByZeroRestriction(b)) {"Cannot divide by zero"}
        require(edgeRestriction(a / b)){"The quotie of arguments over edges the limits of Double numbers"}
        return a / b
    }
}