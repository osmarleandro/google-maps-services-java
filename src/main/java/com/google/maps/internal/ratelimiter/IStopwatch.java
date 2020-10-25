package com.google.maps.internal.ratelimiter;

import java.util.concurrent.TimeUnit;

public interface IStopwatch {

	/**
	   * Returns {@code true} if {@link #start()} has been called on this stopwatch, and {@link #stop()}
	   * has not been called since the last call to {@code start()}.
	   */
	boolean isRunning();

	/**
	   * Starts the stopwatch.
	   *
	   * @return this {@code Stopwatch} instance
	   * @throws IllegalStateException if the stopwatch is already running.
	   */
	IStopwatch start();

	/**
	   * Stops the stopwatch. Future reads will return the fixed duration that had elapsed up to this
	   * point.
	   *
	   * @return this {@code Stopwatch} instance
	   * @throws IllegalStateException if the stopwatch is already stopped.
	   */
	IStopwatch stop();

	/**
	   * Sets the elapsed time for this stopwatch to zero, and places it in a stopped state.
	   *
	   * @return this {@code Stopwatch} instance
	   */
	IStopwatch reset();

	/**
	   * Returns the current elapsed time shown on this stopwatch, expressed in the desired time unit,
	   * with any fraction rounded down.
	   *
	   * <p>Note that the overhead of measurement can be more than a microsecond, so it is generally not
	   * useful to specify {@link TimeUnit#NANOSECONDS} precision here.
	   */
	long elapsed(TimeUnit desiredUnit);

	/** Returns a string representation of the current elapsed time. */
	String toString();

}