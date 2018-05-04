package service

import java.security.SecureRandom

val random = SecureRandom()

fun rollDie(sides :Int) :Int {
    return random.nextInt(sides) + 1
}