package com.google.maps;

import org.junit.Test;

public interface IPlacesApiTest {

	void testPlaceDetailsRequest() throws Exception;

	void testAutocompletePredictionStructuredFormatting() throws Exception;

	void testPlaceDetailsLookupGoogleSydney() throws Exception;

	void testPlaceDetailsLookupPermanentlyClosedPlace() throws Exception;

	void testPlaceDetailsLookupReturnsUserRatingsTotal() throws Exception;

	void testPlaceDetailsLookupQuay() throws Exception;

	void testQueryAutocompleteRequest() throws Exception;

	void testQueryAutocompletePizzaNearPar() throws Exception;

	void testQueryAutocompleteWithPlaceId() throws Exception;

	void testTextSearchRequest() throws Exception;

	void testTextSearchRequestWithLocation() throws Exception;

	void testTextSearchRequestWithType() throws Exception;

	void testTextSearchLocationWithoutRadius() throws Exception;

	void testTextSearchResponse() throws Exception;

	void testTextSearchNYC() throws Exception;

	void testPhotoRequest() throws Exception;

	void testNearbySearchRequest() throws Exception;

	void testNearbySearchRequestWithMultipleType() throws Exception;

	void testNearbySearchRadiusAndRankbyDistance() throws Exception;

	void testNearbySearchRankbyDistanceWithoutKeywordNameOrType() throws Exception;

	void testPlaceAutocompleteRequest() throws Exception;

	void testTextSearch() throws Exception;

	void testPhoto() throws Exception;

	void testPizzaInNewYorkPagination() throws Exception;

	void testPlaceDetailsInFrench() throws Exception;

	void testNearbySearchRequestByKeyword() throws Exception;

	void testNearbySearchRequestByName() throws Exception;

	void testNearbySearchRequestByType() throws Exception;

	void testNearbySearchRequestByTypeReturnsUserRatingsTotal() throws Exception;

	void testPlaceAutocomplete() throws Exception;

	void testPlaceAutocompleteWithType() throws Exception;

	void testPlaceAutocompleteWithStrictBounds() throws Exception;

	void testKitaWard() throws Exception;

	void testFindPlaceFromText() throws Exception;

	void testFindPlaceFromTextPoint() throws Exception;

	void testFindPlaceFromTextCircular() throws Exception;

	void testFindPlaceFromTextRectangular() throws Exception;

	void testPlaceDetailsWithBusinessStatus() throws Exception;

	void testPlaceDetailsRequestHasFieldMask() throws Exception;

}