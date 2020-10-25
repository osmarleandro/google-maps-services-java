package com.google.maps.internal;

import java.lang.reflect.Type;

import com.google.maps.model.EncodedPolyline;

public interface IEncodedPolylineInstanceCreator {

	EncodedPolyline createInstance(Type type);

}