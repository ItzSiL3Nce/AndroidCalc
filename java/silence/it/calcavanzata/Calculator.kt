package silence.it.calcavanzata

import android.app.Activity
import android.widget.Toast

typealias Result = Pair<String, Double> // STRING = Operator character, DOUBLE = result
typealias Operation = Triple<Double, Double, Result>

object Calculator {

    fun calc(act: Activity?, s: String): String {
        var res = s.replace("π", Math.PI.toString()).replace("e", Math.E.toString())
        while(res.contains("Fib"))
            res = stringOperation(res, getFirstOperation(null, res, "Fib"))
        while(res.contains("Log"))
            res = stringOperation(res, getFirstOperation(null, res, "Log"))
        while(res.contains("\u221A"))
            res = stringOperation(res, getFirstOperation(null, res, "\u221A"))
        while(res.contains("^"))
            res = stringOperation(res, getFirstOperation(null, res, "^"))
        while(res.contains("%"))
            res = stringOperation(res, getFirstOperation(act, res, "%"))
        while(res.contains("×"))
            res = stringOperation(res, getFirstOperation(null, res, "×"))
        while(res.contains("÷"))
            res = stringOperation(res, getFirstOperation(act, res, "÷"))
        while(res.contains("+"))
            res = stringOperation(res, getFirstOperation(null, res, "+"))
        while(res.contains("-"))
            res = stringOperation(res, getFirstOperation(null, res, "-"))
        return res
    }

    private val invalidOperationResult = Operation(0.0, 0.0, Result("+", 0.0))

    private fun getFirstLeft(s: String): Double {
        val sb = StringBuilder()
        var len = s.length - 1
        while(len >= 0) {
            if(s[len] == '.' || s[len].isDigit()) {
                sb.append(s[len])
                --len
            } else break
        }
        return sb.toString().reversed().toDouble()
    }

    private fun getFirstRight(s: String): Double {
        val sb = StringBuilder()
        var i = 0
        while(i < s.length) {
            if(s[i] == '.' || s[i].isDigit()) {
                sb.append(s[i])
                ++i
            }
            else break
        }
        return sb.toString().toDouble()
    }

    private fun getFirstOperation(act: Activity?, s: String, op: String): Operation {

        val n1: Double
        val n2: Double

        if(isFunction(op)) {
            n1 = 0.0
                                      // Es: Fib5 -> 5, Log10 -> 10
            n2 = getFirstRight(s.substring(s.indexOf(op[0]) + op.length))
        }
        else {
            val parts = s.split(op, limit = 2)
            n1 = getFirstLeft(parts[0])
            n2 = getFirstRight(parts[1])
            if((op == "÷" || op == "%") && n2 == 0.0 && act != null) {
                Toast.makeText(act, "Errore: Divisione per 0.", Toast.LENGTH_LONG).show()
                return Operation(n1, n2, Result("÷", 0.0))
            }
        }

        return Operation(n1, n2, Result(op, when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "×" -> n1 * n2
            "÷" -> n1 / n2
            "%" -> n1 * 100 / n2
            "^" -> Math.pow(n1, n2)
            "Fib" -> fib(n2.toLong())
            "Log" -> Math.log(n2)
            "\u221A" -> Math.sqrt(n2)
            else -> return invalidOperationResult
        }))
    }

    private fun stringOperation(s: String, operation: Operation): String {

        val op = operation.third.first

        var n2 = operation.second.toString()
        if(n2.endsWith(".0"))
            n2 = n2.substring(0, n2.length - 2)

        var res = operation.third.second.toString()
        if (res.endsWith(".0"))
            res = res.substring(0, res.length - 2)

        if(isFunction(op))
            return s.replace("$op$n2", res)

        var n1 = operation.first.toString()
        if (n1.endsWith(".0"))
            n1 = n1.substring(0, n1.length - 2)



        return s.replace("$n1$op$n2", res)
    }
}