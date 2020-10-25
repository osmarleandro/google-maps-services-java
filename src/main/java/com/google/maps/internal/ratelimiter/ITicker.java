package com.google.maps.internal.ratelimiter;

public interface ITicker {

	/** Returns the number of nanoseconds elapsed since this ticker's fixed point of reference. */
	long read();

}