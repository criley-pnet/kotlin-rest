package service


fun parseCommand(command :String) :Int {
    val packed = command.trimWhitespace()

    return evalCommand(packed)
}

fun evalCommand(command :String) :Int {
    val sumExpression = command.splitOn('+')
    if (sumExpression != null) {
        //evaluate sum
        return evalCommand(sumExpression.first) + evalCommand(sumExpression.second)
    }

    val rollExpression = command.splitOn('d')
    if (rollExpression != null) {
        //evaluate die roll
        val times :Int = rollExpression.first.toIntOrNull() ?: 1

        var sum = 0
        repeat(times, {
            sum += rollDie(rollExpression.second.toInt())
        })
        return sum
    }

    //evaluate primitive
    return command.toInt()
}

fun String.trimWhitespace() :String {
    return this.replace("\\s".toRegex(), "")
}

fun String.splitOn(separator :Char) :Pair<String, String>? {
    val index = this.indexOf(separator)
    if (index == -1) {
        return null
    }
    val left = this.substring(0, index)
    val right = this.substring(index + 1, this.length)
    return Pair(left, right)
}