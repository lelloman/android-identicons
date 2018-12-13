package com.lelloman.identicon.util

/**
 * Utility class that holds some useful points for drawing all the shapes.
 */
data class TileMeasures(val width: Int, val height: Int) {
    val wMid: Int = width / 2
    val hMid: Int = height / 2
    val w3: Int = width / 3
    val h3: Int = height / 3
    val w4: Int = width / 4
    val h4: Int = height / 4
    val w32: Int = w3 * 2
    val h32: Int = h3 * 2
    val w43: Int = w4 * 3
    val h43: Int = h4 * 3
    val width2: Int = width * 2
    val height2: Int = height * 2
}
