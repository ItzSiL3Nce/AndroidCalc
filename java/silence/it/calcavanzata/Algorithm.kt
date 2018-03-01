package silence.it.calcavanzata

fun canPutPoint(s: String): Boolean {
    if(s.isEmpty())
        return false
    var len = s.length - 1
    while(len >= 0) {
        if(s[len] == '.')
            return false
        if(!s[len].isDigit())
            break
        len -= 1
    }
    return s[s.length - 1].isDigit()
}

fun canPutOperator(s: String): Boolean {
    if(s.isEmpty())
        return false
    val char = s[s.length - 1]
    return char.isDigit() || char == 'e' || char == 'π'
}

fun canPutFunction(s: String): Boolean {
    if(s.isEmpty())
        return true
    val char = s[s.length - 1]
    return char == '+' || char == '-' || char == '×' || char == '÷'
}

fun canPutNumber(s: String): Boolean {
    if(s.isEmpty())
        return true
    val char = s[s.length - 1]
    return char != 'e' && char != 'π'
}

fun canPutConstant(s: String): Boolean {
    if(s.isEmpty())
        return true
    val char = s[s.length - 1]
    return char == '+' || char == '-' || char == '×' || char == '÷' || char == '^'
        || char == '\u221A' || char == 'g' || char == '%'
}

fun canCalculate(s: String): Boolean {
    if(s.isEmpty())
        return false
    val char = s[s.length - 1]
    return char.isDigit() || char == 'e' || char == 'π'
}

fun fib(nth: Long): Double {
    var x = 0.0
    var y = 1.0
    var z = 1.0
    for (i: Long in 0 until nth) {
        x = y
        y = z
        z = x + y
    }
    return x
}

fun isFunction(s: String): Boolean {
    return s == "Fib" || s == "Log" || s == "\u221A" // sqrt
}

