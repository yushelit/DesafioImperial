package Modelo

enum class Tipo{
    caza, bombardero, carguero
}

data class Nave(var matricula:Int, var tipo:Tipo, var aptoCarga:Boolean): java.io.Serializable
