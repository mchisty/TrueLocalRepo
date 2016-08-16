
package org.truelocal.client;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.truelocal.model.MarsRoverException;
import org.truelocal.service.RoverManager;

/**
 * The Client application to use the {@link }.
 */
public class MarsRoverClientSolution {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(MarsRoverClientSolution.class.getName());

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			MarsRoverClientSolution solution = new MarsRoverClientSolution();
			solution.processMarsRoboticSignals();
		} catch (MarsRoverException e) {
			logger.log(Level.WARNING, e.getMessage(), e.getCause());
		}
	}

	/**
	 * Process mars robotic signals.
	 *
	 * @throws MarsRoverException
	 *             the mars rover exception
	 */
	void processMarsRoboticSignals() throws MarsRoverException {
		try {
			URL resource = MarsRoverClientSolution.class.getResource("../../../input.txt");
			RoverManager roverManager = new RoverManager();
			roverManager.sendSignalToRoboticRovers(resource.getFile());
			roverManager.showResponseOutput();
		} catch (Exception e) {
			throw new MarsRoverException(e);
		}

	}

}
