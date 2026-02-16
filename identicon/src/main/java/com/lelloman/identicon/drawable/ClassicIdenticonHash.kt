package com.lelloman.identicon.drawable

import com.lelloman.identicon.util.extractBits

/**
 * Provides the values for a classic identicon using MD5 bytes.
 */
class ClassicIdenticonHash(md5: ByteArray) {

    val middle: Int
    val corners: Int
    val sides: Int
    val invertMiddle: Int
    val invertCorners: Int
    val invertSides: Int
    val r: Int
    val g: Int
    val b: Int
    val cornersRotation: Int
    val sidesRotation: Int
    val middleRotation: Int

    init {
        var offset = 0

        // middle tile: 4 bits, mod 9 → index into symmetric tiles
        middle = extractBits(md5, offset, 4) % 9
        offset += 4

        // corners tile: 6 bits, mod 37
        corners = extractBits(md5, offset, 6) % 37
        offset += 6

        // sides tile: 6 bits, mod 37
        sides = extractBits(md5, offset, 6) % 37
        offset += 6

        // invert flags: 1 bit each
        invertMiddle = extractBits(md5, offset, 1)
        offset += 1
        invertCorners = extractBits(md5, offset, 1)
        offset += 1
        invertSides = extractBits(md5, offset, 1)
        offset += 1

        // RGB: 6 bits each, range 0-63
        r = extractBits(md5, offset, 6) % 64
        offset += 6
        g = extractBits(md5, offset, 6) % 64
        offset += 6
        b = extractBits(md5, offset, 6) % 64
        offset += 6

        // rotations: 2 bits each
        cornersRotation = extractBits(md5, offset, 2)
        offset += 2
        sidesRotation = extractBits(md5, offset, 2)
        offset += 2
        middleRotation = extractBits(md5, offset, 2)
    }
}
