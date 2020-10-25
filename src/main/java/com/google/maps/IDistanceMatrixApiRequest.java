package com.google.maps;

import java.time.Instant;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public interface IDistanceMatrixApiRequest {

	/**
	   * One or more addresses from which to calculate distance and time. The service will geocode the
	   * strings and convert them to latitude/longitude coordinates to calculate directions.
	   *
	   * @param origins Strings to geocode and use as an origin point (e.g. "New York, NY")
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest origins(String... origins);

	/**
	   * One or more latitude/longitude values from which to calculate distance and time.
	   *
	   * @param points The origin points.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest origins(LatLng... points);

	/**
	   * One or more addresses to which to calculate distance and time. The service will geocode the
	   * strings and convert them to latitude/longitude coordinates to calculate directions.
	   *
	   * @param destinations Strings to geocode and use as a destination point (e.g. "Jersey City, NJ")
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest destinations(String... destinations);

	/**
	   * One or more latitude/longitude values to which to calculate distance and time.
	   *
	   * @param points The destination points.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest destinations(LatLng... points);

	/**
	   * Specifies the mode of transport to use when calculating directions.
	   *
	   * <p>Note that Distance Matrix requests only support {@link TravelMode#DRIVING}, {@link
	   * TravelMode#WALKING}, {@link TravelMode#BICYCLING} and {@link TravelMode#TRANSIT}.
	   *
	   * @param mode One of the travel modes supported by the Distance Matrix API.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest mode(TravelMode mode);

	/**
	   * Introduces restrictions to the route. Only one restriction can be specified.
	   *
	   * @param restriction A {@link RouteRestriction} object.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest avoid(RouteRestriction restriction);

	/**
	   * Specifies the unit system to use when expressing distance as text. Distance Matrix results
	   * contain text within distance fields to indicate the distance of the calculated route.
	   *
	   * @param unit One of {@link Unit#METRIC} or {@link Unit#IMPERIAL}.
	   * @see <a
	   *     href="https://developers.google.com/maps/documentation/distance-matrix/intro#unit_systems">
	   *     Unit systems in the Distance Matrix API</a>
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest units(Unit unit);

	/**
	   * Specifies the desired time of departure.
	   *
	   * <p>The departure time may be specified in two cases:
	   *
	   * <ul>
	   *   <li>For requests where the travel mode is transit: You can optionally specify one of
	   *       departure_time or arrival_time. If neither time is specified, the departure_time defaults
	   *       to now. (That is, the departure time defaults to the current time.)
	   *   <li>For requests where the travel mode is driving: Google Maps API for Work customers can
	   *       specify the departure_time to receive trip duration considering current traffic
	   *       conditions. The departure_time must be set to within a few minutes of the current time.
	   * </ul>
	   *
	   * <p>Setting the parameter to null will remove it from the API request.
	   *
	   * @param departureTime The time of departure.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest departureTime(Instant departureTime);

	/**
	   * Specifies the assumptions to use when calculating time in traffic. This parameter may only be
	   * specified when the travel mode is driving and the request includes a departure_time.
	   *
	   * @param trafficModel The traffic model to use in estimating time in traffic.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest trafficModel(TrafficModel trafficModel);

	/**
	   * Specifies the desired time of arrival for transit requests. You can specify either
	   * departure_time or arrival_time, but not both.
	   *
	   * @param arrivalTime The preferred arrival time.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest arrivalTime(Instant arrivalTime);

	/**
	   * Specifies one or more preferred modes of transit. This parameter may only be specified for
	   * requests where the mode is transit.
	   *
	   * @param transitModes The preferred transit modes.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest transitModes(TransitMode... transitModes);

	/**
	   * Specifies preferences for transit requests. Using this parameter, you can bias the options
	   * returned, rather than accepting the default best route chosen by the API.
	   *
	   * @param pref The transit routing preference for this distance matrix.
	   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
	   */
	IDistanceMatrixApiRequest transitRoutingPreference(TransitRoutingPreference pref);

}