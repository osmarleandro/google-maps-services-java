package com.google.maps;

import com.google.maps.StaticMapsRequest.ImageFormat;
import com.google.maps.StaticMapsRequest.Markers;
import com.google.maps.StaticMapsRequest.Path;
import com.google.maps.StaticMapsRequest.StaticMapType;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;

public interface IStaticMapsRequest {

	/**
	   * <code>center</code> (required if markers not present) defines the center of the map,
	   * equidistant from all edges of the map.
	   *
	   * @param location The location of the center of the map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest center(LatLng location);

	/**
	   * <code>center</code> (required if markers not present) defines the center of the map,
	   * equidistant from all edges of the map.
	   *
	   * @param location The location of the center of the map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest center(String location);

	/**
	   * <code>zoom</code> (required if markers not present) defines the zoom level of the map, which
	   * determines the magnification level of the map.
	   *
	   * @param zoom The zoom level of the region.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest zoom(int zoom);

	/**
	   * <code>size</code> defines the rectangular dimensions of the map image.
	   *
	   * @param size The Size of the static map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest size(Size size);

	/**
	   * <code>scale</code> affects the number of pixels that are returned. Setting <code>scale</code>
	   * to 2 returns twice as many pixels as <code>scale</code> set to 1 while retaining the same
	   * coverage area and level of detail (i.e. the contents of the map doesn't change).
	   *
	   * @param scale The scale of the static map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest scale(int scale);

	/**
	   * <code>format</code> defines the format of the resulting image. By default, the Google Static
	   * Maps API creates PNG images. There are several possible formats including GIF, JPEG and PNG
	   * types.
	   *
	   * @param format The format of the static map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest format(ImageFormat format);

	/**
	   * <code>maptype</code> defines the type of map to construct.
	   *
	   * @param maptype The map type of the static map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest maptype(StaticMapType maptype);

	/**
	   * <code>region</code> defines the appropriate borders to display, based on geo-political
	   * sensitivities. Accepts a region code specified as a two-character ccTLD ('top-level domain')
	   * value.
	   *
	   * @param region The region of the static map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest region(String region);

	/**
	   * <code>markers</code> parameter defines a set of one or more markers (map pins) at a set of
	   * locations. Each marker defined within a single markers declaration must exhibit the same visual
	   * style; if you wish to display markers with different styles, you will need to supply multiple
	   * markers parameters with separate style information.
	   *
	   * @param markers A group of markers with the same style.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest markers(Markers markers);

	/**
	   * The <code>path</code> parameter defines a set of one or more locations connected by a path to
	   * overlay on the map image.
	   *
	   * @param path A path to render atop the map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest path(Path path);

	/**
	   * The <code>path</code> parameter defines a set of one or more locations connected by a path to
	   * overlay on the map image. This variant of the method accepts the path as an EncodedPolyline.
	   *
	   * @param path A path to render atop the map, as an EncodedPolyline.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest path(EncodedPolyline path);

	/**
	   * <code>visible</code> instructs the Google Static Maps API service to construct a map such that
	   * the existing locations remain visible.
	   *
	   * @param visibleLocation The location to be made visible in the requested Static Map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest visible(LatLng visibleLocation);

	/**
	   * <code>visible</code> instructs the Google Static Maps API service to construct a map such that
	   * the existing locations remain visible.
	   *
	   * @param visibleLocation The location to be made visible in the requested Static Map.
	   * @return Returns this {@code StaticMapsRequest} for call chaining.
	   */
	IStaticMapsRequest visible(String visibleLocation);

}