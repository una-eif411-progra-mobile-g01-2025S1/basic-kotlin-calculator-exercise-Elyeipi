package org.example

class Calculator{
    fun add(a : Double, b : Double) : Double{
        require(a + b <= Double.POSITIVE_INFINITY){"The sum of arguments is bigger than double max value"}
        return a + b;
    }

    fun subtract(a : Double, b : Double) : Double{
        require(a - b >= Double.NEGATIVE_INFINITY){"The subtract of arguments is smaller than double min value"}
        return a - b;
    }

    fun multiply(a : Double, b : Double) : Double{
        require(a * b <= Double.POSITIVE_INFINITY){"The product of arguments is bigger than double max value"}
        return a * b;
    }

    fun divide(a : Double, b : Double) : Double{
        require(b != 0.0) {"Cannot divide by zero"}
        return a/b
    }
}