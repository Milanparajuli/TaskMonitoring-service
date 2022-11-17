package com.taskmonitoring.system.enumtype;

import org.springframework.http.HttpStatus;

public enum TaskStatus {
	TODO(1, "Todo"), DOING(2, "Doing"), DONE(3, "Done");

	private final int value;

	private final String reasonPhrase;

	TaskStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the string value of this state code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this state code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	/**
	 * Return the enum constant of this type with the specified string value.
	 * 
	 * @param statusCode the string value of the enum to be returned
	 * @return the enum constant with the specified string value
	 * @throws IllegalArgumentException if this enum has no constant for the
	 *                                  specified string value
	 */
	public static TaskStatus getTaskStatus(int statusCode) {
		for (TaskStatus status : values()) {
			if (status.value==statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

	/**
	 * Return the string value of this status code.
	 */
	public int getValue() {
		return this.value;
	}
}
