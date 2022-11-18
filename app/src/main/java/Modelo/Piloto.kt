package Modelo

data class Piloto(var nombre:String,var password:String , var edad:Int, var experiencia:Int, var imagen:ByteArray, var primer_log:String): java.io.Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Piloto

        if (nombre != other.nombre) return false

        return true
    }

    override fun hashCode(): Int {
        return nombre.hashCode()
    }
}
