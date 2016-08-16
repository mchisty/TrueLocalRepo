
package org.truelocal.model;

/**
 * The Class MarsRoverException.
 */
public class MarsRoverException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8579810735291730600L;

	/**
	 * Instantiates a new mars rover exception.
	 */
	public MarsRoverException() {
		super();
	}

	/**
	 * Instantiates a new mars rover exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public MarsRoverException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new mars rover exception.
	 *
	 * @param e
	 *            the e
	 */
	public MarsRoverException(Throwable e) {
		super(e);
	}

	/**
	 * Instantiates a new mars rover exception.
	 *
	 * @param msg
	 *            the msg
	 * @param e
	 *            the e
	 */
	public MarsRoverException(String msg, Throwable e) {
		super(msg, e);
	}

}
