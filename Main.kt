import kotlin.concurrent.thread
import java.util.Scanner

var numero = 0
var juegoEnMarcha = true

fun main() {
    val scanner = Scanner(System.`in`)

    println("Por favor, introduce el número objetivo a alcanzar (Hasta 10):")
    val objetivo = scanner.nextInt()
    scanner.nextLine()
    while (objetivo > 10) {
        println("Por favor, introduce el número objetivo a alcanzar (Hasta 10):")
        scanner.nextLine()
    }

    val hiloInstrucciones = thread(start = true) {
        println("Bienvenido al juego atrapando números")
        println("Tu objetivo es detener el número cuando sea mayor o igual a $objetivo.")
        println("Escribe 'atrapar' para intentar atrapar el número, el juego iniciará en 5 segundos.")
        Thread.sleep(5000) // Tiempo para que al jugador le de tiempo a leer las instrucciones
    }

    hiloInstrucciones.join()

    val hiloNumero = thread(start = true) {
        while (juegoEnMarcha) {
            numero = (1..10).random()
            println("El número actual es: $numero")
            Thread.sleep(700)
        }
    }

    while (juegoEnMarcha) {
        val entrada = scanner.nextLine()
        if (entrada.equals("atrapar", ignoreCase = true)) {
            if (numero >= objetivo) {
                println("Felicidades, has atrapado el número $numero, que es mayor o igual a $objetivo.")
                juegoEnMarcha = false
            } else {
                println("Fallaste, el número $numero es menor que $objetivo. Intenta de nuevo.")
            }
        } else {
            println("Error, escribe atrapar para intentar atrapar el número.")
        }
    }

    hiloNumero.join()
    println("Juego terminado")
}