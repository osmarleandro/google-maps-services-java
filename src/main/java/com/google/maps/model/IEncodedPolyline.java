package com.google.maps.model;

import java.util.List;

public interface IEncodedPolyline {

	String getEncodedPath();

	List<LatLng> decodePath();

	// Use the encoded point representation; decoding to get an alternate representation for
	// individual points would be expensive.
	String toString();

}