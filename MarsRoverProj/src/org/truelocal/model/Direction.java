package org.truelocal.model;

/**
 * The Enum Direction.
 */
public enum Direction {
	/** The north. */
	NORTH("N"),
	/** The south. */
	SOUTH("S"),
	/** The east. */
	EAST("E"),
	/** The west. */
	WEST("W");
	/** The val. */
	private String val;

	/**
	 * Instantiates a new direction.
	 *
	 * @param v
	 *            the v
	 */
	private Direction(String v) {
		this.val = v;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.val;
	}

	public void setDirectionByCode(String v) {
		this.val = v;
	}
}
