package com.google.maps.internal.ratelimiter;

import java.util.concurrent.TimeUnit;

public interface IRateLimiter {

	/**
	   * Updates the stable rate of this {@code RateLimiter}, that is, the {@code permitsPerSecond}
	   * argument provided in the factory method that constructed the {@code RateLimiter}. Currently
	   * throttled threads will <b>not</b> be awakened as a result of this invocation, thus they do not
	   * observe the new rate; only subsequent requests will.
	   *
	   * <p>Note though that, since each request repays (by waiting, if necessary) the cost of the
	   * <i>previous</i> request, this means that the very next request after an invocation to {@code
	   * setRate} will not be affected by the new rate; it will pay the cost of the previous request,
	   * which is in terms of the previous rate.
	   *
	   * <p>The behavior of the {@code RateLimiter} is not modified in any other way, e.g. if the {@code
	   * RateLimiter} was configured with a warmup period of 20 seconds, it still has a warmup period of
	   * 20 seconds after this method invocation.
	   *
	   * @param permitsPerSecond the new stable rate of this {@code RateLimiter}
	   * @throws IllegalArgumentException if {@code permitsPerSecond} is negative or zero
	   */
	void setRate(double permitsPerSecond);

	/**
	   * Returns the stable rate (as {@code permits per seconds}) with which this {@code RateLimiter} is
	   * configured with. The initial value of this is the same as the {@code permitsPerSecond} argument
	   * passed in the factory method that produced this {@code RateLimiter}, and it is only updated
	   * after invocations to {@linkplain #setRate}.
	   */
	double getRate();

	/**
	   * Acquires a single permit from this {@code RateLimiter}, blocking until the request can be
	   * granted. Tells the amount of time slept, if any.
	   *
	   * <p>This method is equivalent to {@code acquire(1)}.
	   *
	   * @return time spent sleeping to enforce rate, in seconds; 0.0 if not rate-limited
	   * @since 16.0 (present in 13.0 with {@code void} return type})
	   */
	double acquire();

	/**
	   * Acquires the given number of permits from this {@code RateLimiter}, blocking until the request
	   * can be granted. Tells the amount of time slept, if any.
	   *
	   * @param permits the number of permits to acquire
	   * @return time spent sleeping to enforce rate, in seconds; 0.0 if not rate-limited
	   * @throws IllegalArgumentException if the requested number of permits is negative or zero
	   * @since 16.0 (present in 13.0 with {@code void} return type})
	   */
	double acquire(int permits);

	/**
	   * Acquires a permit from this {@code RateLimiter} if it can be obtained without exceeding the
	   * specified {@code timeout}, or returns {@code false} immediately (without waiting) if the permit
	   * would not have been granted before the timeout expired.
	   *
	   * <p>This method is equivalent to {@code tryAcquire(1, timeout, unit)}.
	   *
	   * @param timeout the maximum time to wait for the permit. Negative values are treated as zero.
	   * @param unit the time unit of the timeout argument
	   * @return {@code true} if the permit was acquired, {@code false} otherwise
	   * @throws IllegalArgumentException if the requested number of permits is negative or zero
	   */
	boolean tryAcquire(long timeout, TimeUnit unit);

	/**
	   * Acquires permits from this {@link RateLimiter} if it can be acquired immediately without delay.
	   *
	   * <p>This method is equivalent to {@code tryAcquire(permits, 0, anyUnit)}.
	   *
	   * @param permits the number of permits to acquire
	   * @return {@code true} if the permits were acquired, {@code false} otherwise
	   * @throws IllegalArgumentException if the requested number of permits is negative or zero
	   * @since 14.0
	   */
	boolean tryAcquire(int permits);

	/**
	   * Acquires a permit from this {@link RateLimiter} if it can be acquired immediately without
	   * delay.
	   *
	   * <p>This method is equivalent to {@code tryAcquire(1)}.
	   *
	   * @return {@code true} if the permit was acquired, {@code false} otherwise
	   * @since 14.0
	   */
	boolean tryAcquire();

	/**
	   * Acquires the given number of permits from this {@code RateLimiter} if it can be obtained
	   * without exceeding the specified {@code timeout}, or returns {@code false} immediately (without
	   * waiting) if the permits would not have been granted before the timeout expired.
	   *
	   * @param permits the number of permits to acquire
	   * @param timeout the maximum time to wait for the permits. Negative values are treated as zero.
	   * @param unit the time unit of the timeout argument
	   * @return {@code true} if the permits were acquired, {@code false} otherwise
	   * @throws IllegalArgumentException if the requested number of permits is negative or zero
	   */
	boolean tryAcquire(int permits, long timeout, TimeUnit unit);

	String toString();

}