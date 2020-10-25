package com.google.maps;

import java.time.Instant;

import com.google.maps.DirectionsApiRequest.Waypoint;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public interface IDirectionsApiRequest {

	/**
	   * The address or textual latitude/longitude value from which you wish to calculate directions. If
	   * you pass an address as a location, the Directions service will geocode the location and convert
	   * it to a latitude/longitude coordinate to calculate directions. If you pass coordinates, ensure
	   * that no space exists between the latitude and longitude values.
	   *
	   * @param origin The starting location for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest origin(String origin);

	/**
	   * The address or textual latitude/longitude value from which you wish to calculate directions. If
	   * you pass an address as a location, the Directions service will geocode the location and convert
	   * it to a latitude/longitude coordinate to calculate directions. If you pass coordinates, ensure
	   * that no space exists between the latitude and longitude values.
	   *
	   * @param destination The ending location for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest destination(String destination);

	/**
	   * The Place ID value from which you wish to calculate directions.
	   *
	   * @param originPlaceId The starting location Place ID for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest originPlaceId(String originPlaceId);

	/**
	   * The Place ID value from which you wish to calculate directions.
	   *
	   * @param destinationPlaceId The ending location Place ID for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest destinationPlaceId(String destinationPlaceId);

	/**
	   * The origin, as a latitude/longitude location.
	   *
	   * @param origin The starting location for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest origin(LatLng origin);

	/**
	   * The destination, as a latitude/longitude location.
	   *
	   * @param destination The ending location for the Directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest destination(LatLng destination);

	/**
	   * Specifies the mode of transport to use when calculating directions. The mode defaults to
	   * driving if left unspecified. If you set the mode to {@code TRANSIT} you must also specify
	   * either a {@code departureTime} or an {@code arrivalTime}.
	   *
	   * @param mode The travel mode to request directions for.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest mode(TravelMode mode);

	/**
	   * Indicates that the calculated route(s) should avoid the indicated features.
	   *
	   * @param restrictions one or more of {@link DirectionsApi.RouteRestriction} objects.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest avoid(DirectionsApi.RouteRestriction... restrictions);

	/**
	   * Specifies the unit system to use when displaying results.
	   *
	   * @param units The preferred units for displaying distances.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest units(Unit units);

	/**
	   * @param region The region code, specified as a ccTLD ("top-level domain") two-character value.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest region(String region);

	/**
	   * Set the arrival time for a Transit directions request.
	   *
	   * @param time The arrival time to calculate directions for.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest arrivalTime(Instant time);

	/**
	   * Set the departure time for a transit or driving directions request. If both departure time and
	   * traffic model are not provided, then "now" is assumed. If traffic model is supplied, then
	   * departure time must be specified. Duration in traffic will only be returned if the departure
	   * time is specified.
	   *
	   * @param time The departure time to calculate directions for.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest departureTime(Instant time);

	/**
	   * Set the departure time for a transit or driving directions request as the current time. If
	   * traffic model is supplied, then departure time must be specified. Duration in traffic will only
	   * be returned if the departure time is specified.
	   *
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest departureTimeNow();

	/**
	   * Specifies a list of waypoints. Waypoints alter a route by routing it through the specified
	   * location(s). A waypoint is specified as either a latitude/longitude coordinate or as an address
	   * which will be geocoded. Waypoints are only supported for driving, walking and bicycling
	   * directions.
	   *
	   * <p>For more information on waypoints, see <a
	   * href="https://developers.google.com/maps/documentation/directions/intro#Waypoints">Using
	   * Waypoints in Routes</a>.
	   *
	   * @param waypoints The waypoints to add to this directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest waypoints(Waypoint... waypoints);

	/**
	   * Specifies the list of waypoints as String addresses. If any of the Strings are Place IDs, you
	   * must prefix them with {@code place_id:}.
	   *
	   * <p>See {@link #prefixPlaceId(String)}.
	   *
	   * <p>See {@link #waypoints(Waypoint...)}.
	   *
	   * @param waypoints The waypoints to add to this directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest waypoints(String... waypoints);

	/**
	   * Specifies the list of waypoints as Plade ID Strings, prefixing them as required by the API.
	   *
	   * <p>See {@link #prefixPlaceId(String)}.
	   *
	   * <p>See {@link #waypoints(Waypoint...)}.
	   *
	   * @param waypoints The waypoints to add to this directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest waypointsFromPlaceIds(String... waypoints);

	/**
	   * The list of waypoints as latitude/longitude locations.
	   *
	   * <p>See {@link #waypoints(Waypoint...)}.
	   *
	   * @param waypoints The waypoints to add to this directions request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest waypoints(LatLng... waypoints);

	/**
	   * Allow the Directions service to optimize the provided route by rearranging the waypoints in a
	   * more efficient order.
	   *
	   * @param optimize Whether to optimize waypoints.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest optimizeWaypoints(boolean optimize);

	/**
	   * If set to true, specifies that the Directions service may provide more than one route
	   * alternative in the response. Note that providing route alternatives may increase the response
	   * time from the server.
	   *
	   * @param alternateRoutes whether to return alternate routes.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest alternatives(boolean alternateRoutes);

	/**
	   * Specifies one or more preferred modes of transit. This parameter may only be specified for
	   * requests where the mode is transit.
	   *
	   * @param transitModes The preferred transit modes.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest transitMode(TransitMode... transitModes);

	/**
	   * Specifies preferences for transit requests. Using this parameter, you can bias the options
	   * returned, rather than accepting the default best route chosen by the API.
	   *
	   * @param pref The transit routing preferences for this request.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest transitRoutingPreference(TransitRoutingPreference pref);

	/**
	   * Specifies the traffic model to use when requesting future driving directions. Once set, you
	   * must specify a departure time.
	   *
	   * @param trafficModel The traffic model for estimating driving time.
	   * @return Returns this {@code DirectionsApiRequest} for call chaining.
	   */
	IDirectionsApiRequest trafficModel(TrafficModel trafficModel);

	/**
	   * Helper method for prefixing a Place ID, as specified by the API.
	   *
	   * @param placeId The Place ID to be prefixed.
	   * @return Returns the Place ID prefixed with {@code place_id:}.
	   */
	String prefixPlaceId(String placeId);

}