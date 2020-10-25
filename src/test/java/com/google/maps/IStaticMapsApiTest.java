package com.google.maps;

import org.junit.Test;

public interface IStaticMapsApiTest {

	void testGetSydneyStaticMap() throws Exception;

	void testGetSydneyLatLngStaticMap() throws Exception;

	void testRequest() throws Exception;

	void testValidateRequest_noCenter() throws Exception;

	void testValidateRequest_noZoom() throws Exception;

	void testValidateRequest_noCenterAndNoZoomWithMarkers() throws Exception;

	void testValidateRequest_noCenterAndNoZoomWithPath() throws Exception;

	void testValidateRequest_noSize() throws Exception;

	void testMarkerAndPath() throws Exception;

	void testMarkerAndPathAsEncodedPolyline() throws Exception;

	void testBrooklynBridgeNYMarkers() throws Exception;

}