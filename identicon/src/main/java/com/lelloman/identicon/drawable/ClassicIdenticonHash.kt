package com.lelloman.identicon.drawable


/**
 * Provides the values for a classic identicon using an int
 */
class ClassicIdenticonHash(val value: Int) {

    private val values: IntArray

    val middle: Int
        get() = values[0]

    val corners: Int
        get() = values[1]

    val sides: Int
        get() = values[2]

    val invertMiddle: Int
        get() = values[3]

    val invertCorners: Int
        get() = values[4]

    val invertSides: Int
        get() = values[5]

    val r: Int
        get() = values[6]

    val g: Int
        get() = values[7]

    val b: Int
        get() = values[8]

    val cornersRotation: Int
        get() = values[9]

    val sidesRotation: Int
        get() = values[10]

    init {

        values = IntArray(11)

        // middle tile
        values[0] = positiveMod(value.toLong(), 8) * 4

        // corners
        values[1] = positiveMod((value shr 3).toLong(), 32)

        // sides
        values[2] = positiveMod((value shr 8).toLong(), 32)

        // invert middle
        values[3] = positiveMod((value shr 13).toLong(), 2)

        // invert corners
        values[4] = positiveMod((value shr 14).toLong(), 2)

        // invert sides
        values[5] = positiveMod((value shr 15).toLong(), 2)

        // r
        values[6] = positiveMod((value shr 16).toLong(), 16)

        // g
        values[7] = positiveMod((value shr 20).toLong(), 16)

        // b
        values[8] = positiveMod((value shr 24).toLong(), 16)

        // corners rotation
        values[9] = positiveMod((value shr 28).toLong(), 4)

        // sides rotation
        values[10] = positiveMod((value shr 30).toLong(), 4)

    }

    companion object {

        fun positiveMod(i: Long, m: Int): Int {
            return Math.abs((i % m).toInt())
        }
    }
}
