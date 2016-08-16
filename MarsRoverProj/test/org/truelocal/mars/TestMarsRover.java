package org.truelocal.mars;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.truelocal.model.Constants;
import org.truelocal.model.Direction;
import org.truelocal.model.RoboticRover;

/**
 * The Class TestMarsRover.
 */
public class TestMarsRover {

	/** The RoboticRover object. */
	private RoboticRover r;

	/**
	 * Initialize robotic rover.
	 */
	@Before
	public void initializeRoboticRover() {
		// Set some default values
		r = new RoboticRover(5, 5);
		r.setDirectionCode("N");
		r.setPosX(0);
		r.setPosY(0);
		r.setInputSignal("M");
	}

	/**
	 * Check direction.
	 */
	@Test
	public void checkDirection() {
		r.setDirectionCode("N");
		Assert.assertTrue("N".equals(r.getCurrentDirectionVal()));
		r.setDirectionCode("S");
		Assert.assertTrue("S".equals(r.getCurrentDirectionVal()));
	}

	/**
	 * Check direction by code.
	 */
	@Test
	public void checkDirectionByCode() {
		r.setDirectionCode("S");
		Assert.assertTrue(r.getCurrentDirection() == Direction.SOUTH);
		Assert.assertFalse(r.getCurrentDirection() == Direction.NORTH);
	}

	/**
	 * Turn left right when direction in north.
	 */
	@Test
	public void turnLeftRightWhenDirectionInNorth() {
		// First set the direction to North
		r.setDirectionCode("N");
		// Turn left
		r.turnLeft();
		Assert.assertTrue(r.getCurrentDirection() == Direction.WEST);
		// Turn right
		r.turnRight();
		// Fail as it has already turned in the previous step
		Assert.assertFalse(r.getCurrentDirection() == Direction.EAST);
		// Pass if it is considered based on the previous turn
		Assert.assertTrue(r.getCurrentDirection() == Direction.NORTH);
	}

	/**
	 * Turn left right when position in south.
	 */
	@Test
	public void turnLeftRightWhenPositionInSouth() {
		r.setDirectionCode("S");
		r.turnLeft();
		Assert.assertTrue(r.getCurrentDirection() == Direction.EAST);
		r.turnRight();
		Assert.assertFalse(r.getCurrentDirection() == Direction.WEST);
		Assert.assertTrue(r.getCurrentDirection() == Direction.SOUTH);
	}

	/**
	 * Turn left right when position in east.
	 */
	@Test
	public void turnLeftRightWhenPositionInEast() {
		r.setDirectionCode("E");
		r.turnLeft();
		Assert.assertTrue(r.getCurrentDirection() == Direction.NORTH);
		r.turnRight();
		Assert.assertFalse(r.getCurrentDirection() == Direction.SOUTH);
		Assert.assertTrue(r.getCurrentDirection() == Direction.EAST);
	}

	/**
	 * Turn left right when position in west.
	 */
	@Test
	public void turnLeftRightWhenPositionInWest() {
		r.setDirectionCode("W");
		r.turnLeft();
		Assert.assertTrue(r.getCurrentDirection() == Direction.SOUTH);
		r.turnRight();
		Assert.assertFalse(r.getCurrentDirection() == Direction.NORTH);
		Assert.assertTrue(r.getCurrentDirection() == Direction.WEST);
	}

	/**
	 * Move and turn when position in north.
	 */
	@Test
	public void moveAndTurnWhenPositionInNorth() {
		r.setDirectionCode("N");
		r.setPosX(1);
		r.setPosY(2);
		r.setInputSignal("MMR");
		r.move();
		Assert.assertTrue(r.getPosY() == 4);
		Assert.assertTrue(r.getCurrentDirection() == Direction.EAST);
	}

	/**
	 * Move and turn when position in south.
	 */
	@Test
	public void moveAndTurnWhenPositionInSouth() {
		r.setDirectionCode("S");
		r.setPosX(3);
		r.setPosY(3);
		r.setInputSignal("MMML");
		r.move();
		Assert.assertTrue(r.getPosY() == 0);
		Assert.assertTrue(r.getCurrentDirection() == Direction.EAST);
	}

	/**
	 * Turn andmove when position in east.
	 */
	@Test
	public void turnAndmoveWhenPositionInEast() {
		// First set the position and direction
		r.setDirectionCode("E");
		r.setPosX(1);
		r.setPosY(1);
		r.setInputSignal("LMM");
		r.move();
		Assert.assertTrue(r.getPosY() == 3);
		Assert.assertTrue(r.getCurrentDirection() == Direction.NORTH);
	}

	/**
	 * Turn andmove when position in west.
	 */
	@Test
	public void turnAndmoveWhenPositionInWest() {
		// First set the position and direction
		r.setDirectionCode("W");
		r.setPosX(2);
		r.setPosY(2);
		r.setInputSignal("LMM");
		r.move();
		Assert.assertTrue(r.getPosY() == 0);
		Assert.assertTrue(r.getCurrentDirection() == Direction.SOUTH);
	}

	/**
	 * Check rover position within boundary.
	 */
	@Test
	public void checkRoverPositionOutOfBoundary() {
		// First set the position and direction
		r.setPosX(4);
		r.setPosY(4);
		r.setInputSignal("MMMMMMMMMMMMMM");
		r.move();
		Assert.assertTrue(r.getResult().equals(Constants.OUT_OF_BOUNDARY));
	}

	/**
	 * Check invalid direction.
	 */
	@Test
	public void checkInvalidDirection() {
		// First set the position and direction
		r.setDirectionCode("X");
		r.move();
		Assert.assertTrue(r.getResult().equals(Constants.INVALID_DIRECTION));
	}

	/**
	 * Check invalid co ordination.
	 */
	@Test
	public void checkInvalidCoOrdination() {
		r.setPosX(-1);
		r.move();
		Assert.assertTrue(r.getResult().equals(Constants.INVALID_DIRECTION));
		r.setPosX(100);
		r.move();
		Assert.assertTrue(r.getResult().equals(Constants.INVALID_DIRECTION));
	}

	/**
	 * Check if unknown signal.
	 */
	@Test
	public void checkIfUnknownSignal() {
		r.setInputSignal("LLMXYZ");
		r.move();
		Assert.assertTrue(r.getResult().equals(Constants.INVALID_SIGNAL));
	}

	/**
	 * Move with test data1.
	 */
	@Test
	public void moveWithTestData1() {
		r.setPosX(1);
		r.setPosY(2);
		r.setDirectionCode("N");
		String testSignal = "LMLMLMLMM";
		r.setInputSignal(testSignal);
		r.move();
		String expectedVal = "1 3 N";
		Assert.assertTrue(expectedVal.equals(r.getResult()));
	}

	/**
	 * Move with test data2.
	 */
	@Test
	public void moveWithTestData2() {
		r.setPosX(3);
		r.setPosY(3);
		r.setDirectionCode("E");
		String testSignal = "MMRMMRMRRM";
		r.setInputSignal(testSignal);
		r.move();
		String expectedVal = "5 1 E";
		Assert.assertTrue(expectedVal.equals(r.getResult()));
	}

	/**
	 * Move with test data3.
	 */
	@Test
	public void moveWithTestData3() {
		r.setPosX(0);
		r.setPosY(0);
		r.setDirectionCode("E");
		String testSignal = "MMMMMLMMMMMLMMMMMLMMMMM";
		r.setInputSignal(testSignal);
		r.move();
		String expectedVal = "0 0 S";
		Assert.assertTrue(expectedVal.equals(r.getResult()));
	}

	/**
	 * Removes the robotic rover.
	 */
	@After
	public void removeRoboticRover() {
		r = null;
	}
}
