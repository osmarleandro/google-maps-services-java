package com.google.maps.model;

import com.google.maps.internal.StringJoin.UrlValue;
import java.util.Locale;

/** Indicates user preference when requesting transit directions. */
public enum TransitRoutingPreference_RENAMED implements UrlValue {
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
}
