package com.lelloman.identicon.classic;


/**
 * 	Provides the values for a classic identicon using an int
 */
public class ClassicIdenticonHash {

	public final int value;

	public static int positiveMod(long i, int m) {
		return Math.abs((int) (i % m));
	}

	private int[] values;

	public ClassicIdenticonHash(int v){
		value = v;

		values = new int[11];

		// middle tile
		values[0] = positiveMod(value,8) * 4;

		// corners
		values[1] = positiveMod((value >> 3), 32);

		// sides
		values[2] = positiveMod((value >> 8),32);

		// invert middle
		values[3] = positiveMod((value >> 13),2);

		// invert corners
		values[4] = positiveMod((value >> 14),2);

		// invert sides
		values[5] = positiveMod((value >> 15),2);

		// r
		values[6] = positiveMod((value >> 16),16);

		// g
		values[7] = positiveMod((value >> 20),16);

		// b
		values[8] = positiveMod((value >> 24),16);

		// corners rotation
		values[9] = positiveMod((value >> 28), 4);

		// sides rotation
		values[10] = positiveMod((value >> 30), 4);

	}

	public int getMiddle() {
		return values[0];
	}

	public int getCorners() {
		return values[1];
	}

	public int getSides() {
		return values[2];
	}

	public int getInvertMiddle() {
		return values[3];
	}

	public int getInvertCorners() {
		return values[4];
	}

	public int getInvertSides() {
		return values[5];
	}

	public int getR() {
		return values[6];
	}

	public int getG() {
		return values[7];
	}

	public int getB() {
		return values[8];
	}

	public int getCornersRotation() {
		return values[9];
	}

	public int getSidesRotation() {
		return values[10];
	}
}
