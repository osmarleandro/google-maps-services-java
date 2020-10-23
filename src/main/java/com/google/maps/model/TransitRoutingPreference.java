package com.google.maps.model;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.internal.StringJoin.UrlValue;
import java.util.Locale;

/** Indicates user preference when requesting transit directions. */
public enum TransitRoutingPreference implements UrlValue {
  LESS_WALKING,
  FEWER_TRANSFERS;

  @Override
  public String toString() {
    return name().toLowerCase(Locale.ENGLISH);
  }

  @Override
  public String toUrlValue() {
    return name().toLowerCase(Locale.ENGLISH);
  }

/**
   * Specifies preferences for transit requests. Using this parameter, you can bias the options
   * returned, rather than accepting the default best route chosen by the API.
   *
   * @param directionsApiRequest TODO
 * @return Returns this {@code DirectionsApiRequest} for call chaining.
   */
  public DirectionsApiRequest transitRoutingPreference(DirectionsApiRequest directionsApiRequest) {
    return directionsApiRequest.param("transit_routing_preference", this);
  }
}
