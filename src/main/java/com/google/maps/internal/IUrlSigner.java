package com.google.maps.internal;

public interface IUrlSigner {

	/** Generate url safe HmacSHA1 of a path. */
	String getSignature(String path);

}