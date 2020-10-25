package com.google.maps.internal;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface IRateLimitExecutorService {

	void setQueriesPerSecond(int maxQps);

	/** Main loop. */
	void run();

	void execute(Runnable runnable);

	void shutdown();

	List<Runnable> shutdownNow();

	boolean isShutdown();

	boolean isTerminated();

	boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException;

	<T> Future<T> submit(Callable<T> tCallable);

	<T> Future<T> submit(Runnable runnable, T t);

	Future<?> submit(Runnable runnable);

	<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables) throws InterruptedException;

	<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit)
			throws InterruptedException;

	<T> T invokeAny(Collection<? extends Callable<T>> callables) throws InterruptedException, ExecutionException;

	<T> T invokeAny(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit)
			throws InterruptedException, ExecutionException, TimeoutException;

}