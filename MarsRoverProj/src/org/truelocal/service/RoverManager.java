package org.truelocal.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

import org.truelocal.model.Constants;
import org.truelocal.model.MarsRoverException;
import org.truelocal.model.RoboticRover;

/**
 * The Class RoverManager.
 */
public class RoverManager {

	/**
	 * The robo list containing all the Robotic Rovers. As per the requirement,
	 * <blockquote> Each rover will be finished sequentially, which means that
	 * the second rover won't start to move until the first one has finished
	 * moving. </blockquote>
	 * 
	 * Therefore, I used a Collections.synchronizedList method. According to the
	 * official doc,
	 * 
	 * <blockquote> This returns a synchronized (thread-safe) list backed by the
	 * specified list. In order to guarantee serial access, it is critical that
	 * all access to the backing list is accomplished through the returned
	 * list. </blockquote>
	 * 
	 * 
	 * I did this to simulate the behaviour that the items in the list are
	 * operated sequentially.
	 * 
	 */
	private final List<RoboticRover> roboList = Collections.synchronizedList(new ArrayList<>());

	/**
	 * Send signal to robotic rovers.
	 *
	 * @param fullPathFileName
	 *            the full path file name
	 * @throws MarsRoverException
	 *             the mars rover exception
	 */
	public void sendSignalToRoboticRovers(String fullPathFileName) throws MarsRoverException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPathFileName));
			String sCurrentLine;
			int maxX = 0;
			int maxY = 0;
			// ------------------------------------------------------------------------------
			// Get the maximum position (upper-right coordinates of the plateau)
			// from the first line. The minimum position (lower left
			// coordinates) is always (0,0) by definition.
			// ------------------------------------------------------------------------------
			if ((sCurrentLine = br.readLine()) != null) {
				StringTokenizer stoken = new StringTokenizer(sCurrentLine, Constants.DELIM_SPACE);
				maxX = Integer.parseInt(stoken.nextToken());
				maxY = Integer.parseInt(stoken.nextToken());
			}

			// ------------------------------------------------------------------------------
			// Read the rest of the lines and populate attributes of the
			// Robotic Rover. Add all rovers in a list.
			// ------------------------------------------------------------------------------

			while ((sCurrentLine = br.readLine()) != null) {
				StringTokenizer stoken = new StringTokenizer(sCurrentLine, " \t");
				RoboticRover rr = new RoboticRover(maxX, maxY);
				rr.setPosX(Integer.parseInt(stoken.nextToken()));
				rr.setPosY(Integer.parseInt(stoken.nextToken()));
				rr.setDirectionCode(stoken.nextToken());
				sCurrentLine = br.readLine();
				rr.setInputSignal(sCurrentLine);
				roboList.add(rr);
			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new MarsRoverException("File not found", e);
		} catch (IOException e) {
			throw new MarsRoverException("I/O exception", e);
		} catch (NumberFormatException e) {
			throw new MarsRoverException("Invalid input number", e);
		} catch (Exception e) {
			throw new MarsRoverException(e);
		}
	}

	/**
	 * Show response output in the console.
	 */
	public void showResponseOutput() {
		Consumer<RoboticRover> cr = robot -> robot.move();
		roboList.stream().forEach(cr.andThen(e -> System.out.println(e.getResult())));
	}

}
