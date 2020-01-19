package tools;

import java.io.Serializable;

public class Vector2 implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6836913977251154349L;

	public static Vector2 direction(final float x, final float y) {
		return new Vector2(x / length(x, y), y / length(x, y));
	}

	public static float distance(final Vector2 first, final Vector2 second) {
		return length(second.x - first.x, second.y - first.y);
	}

	public static float length(final float x, final float y) {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float x;

	public float y;

	public Vector2(final float x, final float y) {
		this.x = x;
		this.y = y;
	}
}