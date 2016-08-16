
package org.truelocal.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class RoboticRover.
 */
public class RoboticRover {

	/** The direction. */
	private Direction direction;

	/** The min y. */
	private final int minX = 0, minY = 0;

	/**
	 * The maximum boundary range should be initialized during instantiation.
	 */
	private final int maxX, maxY;

	/** The current positions of x,y. */
	private int posX = 0, posY = 0;

	/** The result. */
	private String result;

	/** The input signal. */
	private String inputSignal;

	/** The direction code. */
	private String directionCode;

	/**
	 * Instantiates a new robotic rover.
	 *
	 * @param maxX
	 *            the max x
	 * @param maxY
	 *            the max y
	 */
	public RoboticRover(int maxX, int maxY) {
		super();
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Turn left.
	 */
	public void turnLeft() {

		if (direction == Direction.NORTH) {
			direction = Direction.WEST;
		} else if (direction == Direction.WEST) {
			direction = Direction.SOUTH;
		} else if (direction == Direction.SOUTH) {
			direction = Direction.EAST;
		} else if (direction == Direction.EAST) {
			direction = Direction.NORTH;
		}
	}

	/**
	 * Turn right.
	 */
	public void turnRight() {

		if (direction == Direction.NORTH) {
			direction = Direction.EAST;
		} else if (direction == Direction.EAST) {
			direction = Direction.SOUTH;
		} else if (direction == Direction.SOUTH) {
			direction = Direction.WEST;
		} else if (direction == Direction.WEST) {
			direction = Direction.NORTH;
		}
	}

	/**
	 * Move.
	 */
	public void move() {
		if (!isValidDirection()) {
			result = Constants.INVALID_DIRECTION;
			return;
		}
		if (!isValidSignal(inputSignal)) {
			result = Constants.INVALID_SIGNAL;
			return;
		}
		for (char c : inputSignal.toCharArray()) {
			if (c == Constants.MOVE) {
				if (!moveForward()) {
					result = Constants.OUT_OF_BOUNDARY;
					return;
				}
			} else if (c == Constants.LEFT) {
				turnLeft();
			} else if (c == Constants.RIGHT) {
				turnRight();
			}
		}
		result = getPosX() + " " + getPosY() + " " + getCurrentDirectionVal();
	}

	/**
	 * Checks if is valid signal.
	 *
	 * @param s
	 *            the s
	 * @return true, if is valid signal
	 */
	private final boolean isValidSignal(String s) {
		if (null == s) {
			return false;
		}
		Pattern p = Pattern.compile("[L|R|M]*");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * Checks if is valid direction code.
	 *
	 * @return true, if is valid direction code
	 */
	private final boolean isValidDirection() {
		if (null == directionCode) {
			return false;
		}
		if (posX < 0 || posY < 0 || posX > maxX || posY > maxY) {
			return false;
		}
		Pattern p = Pattern.compile("[N|S|E|W]");
		Matcher m = p.matcher(getDirectionCode());
		return m.matches();
	}

	/**
	 * Move forward.
	 *
	 * @return true, if successful
	 */
	private boolean moveForward() {
		if (!isRoverWithinBoundary()) {
			return false;
		} else {
			if (direction == Direction.NORTH) {
				++posY;
			} else if (direction == Direction.SOUTH) {
				--posY;
			} else if (direction == Direction.EAST) {
				++posX;
			} else if (direction == Direction.WEST) {
				--posX;
			}
		}
		return true;
	}

	/**
	 * Checks if is rover within boundary.
	 *
	 * @return true, if is rover within boundary
	 */
	public boolean isRoverWithinBoundary() {
		return posX >= minX && posX <= maxX && posY >= minY && posY <= maxY;
	}

	/**
	 * Gets the pos x.
	 *
	 * @return the pos x
	 */
	public int getPosX() {

		return posX;
	}

	/**
	 * Sets the pos x.
	 *
	 * @param posX
	 *            the new pos x
	 */
	public void setPosX(int posX) {

		this.posX = posX;
	}

	/**
	 * Gets the pos y.
	 *
	 * @return the pos y
	 */
	public int getPosY() {

		return posY;
	}

	/**
	 * Sets the pos y.
	 *
	 * @param posY
	 *            the new pos y
	 */
	public void setPosY(int posY) {

		this.posY = posY;
	}

	/**
	 * Gets the current direction.
	 *
	 * @return the current direction
	 */
	public Direction getCurrentDirection() {

		return direction;
	}

	/**
	 * Gets the current direction val.
	 *
	 * @return the current direction val
	 */
	public String getCurrentDirectionVal() {

		return direction.getValue();
	}

	/**
	 * Gets the maximum boundary range should be initialized during
	 * instantiation.
	 *
	 * @return the maximum boundary range should be initialized during
	 *         instantiation
	 */
	public int getMaxX() {

		return maxX;
	}

	/**
	 * Gets the maximum boundary range should be initialized during
	 * instantiation.
	 *
	 * @return the maximum boundary range should be initialized during
	 *         instantiation
	 */
	public int getMaxY() {

		return maxY;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {

		return result;
	}

	/**
	 * Gets the input signal.
	 *
	 * @return the input signal
	 */
	public String getInputSignal() {

		return inputSignal;
	}

	/**
	 * Sets the input signal.
	 *
	 * @param inputSignal
	 *            the new input signal
	 */
	public void setInputSignal(String inputSignal) {

		this.inputSignal = inputSignal;
	}

	/**
	 * Sets the direction code.
	 *
	 * @param code
	 *            the new direction code
	 */
	public void setDirectionCode(String code) {
		this.directionCode = code;
		for (Direction d : Direction.values()) {
			if (d.getValue().equals(code)) {
				this.direction = d;
				break;
			}
		}
	}

	/**
	 * Gets the direction code.
	 *
	 * @return the direction code
	 */
	public String getDirectionCode() {
		return directionCode;
	}
}
