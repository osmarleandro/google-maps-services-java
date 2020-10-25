package com.google.maps;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public interface IGeoApiContextTest {

	void Setup();

	void Teardown();

	void testGetIncludesDefaultUserAgent() throws Exception;

	void testErrorResponseRetries() throws Exception;

	void testSettingMaxRetries() throws Exception;

	void testRetryCanBeDisabled() throws Exception;

	void testRetryEventuallyReturnsTheRightException() throws Exception;

	void testQueryParamsHaveOrderPreserved() throws Exception;

	void testToggleIfExceptionIsAllowedToRetry() throws Exception;

	void testSingleExperienceId();

	void testMultipleExperienceId();

	void testNoExperienceId();

	void testClearingExperienceId();

	void testExperienceIdIsInHeader() throws Exception;

	void testExperienceIdNotInHeader() throws Exception;

	void testExperienceIdSample();

	void testShutdown() throws InterruptedException;

}