/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.google.maps.model;

import static com.google.maps.internal.StringJoin.UrlValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.google.maps.SmallTests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(SmallTests.class)
public class EnumsTest {
  @Test
  public void testUnknown() throws Exception {
    assertNotNull(AddressComponentType.UNKNOWN); // Does not implement UrlValue.

    assertCannotGetUrlValue(AddressType_RENAMED.UNKNOWN);
    assertCannotGetUrlValue(LocationType.UNKNOWN);
    assertCannotGetUrlValue(TravelMode.UNKNOWN);
  }

  @Test
  public void testCanonicalLiteralsForAddressType() {
    Map<AddressType_RENAMED, String> addressTypeToLiteralMap = new HashMap<AddressType_RENAMED, String>();
    // Short alias just to avoid line wrapping in the below code
    Map<AddressType_RENAMED, String> m = addressTypeToLiteralMap;
    m.put(AddressType_RENAMED.STREET_ADDRESS, "street_address");
    m.put(AddressType_RENAMED.ROUTE, "route");
    m.put(AddressType_RENAMED.INTERSECTION, "intersection");
    m.put(AddressType_RENAMED.POLITICAL, "political");
    m.put(AddressType_RENAMED.COUNTRY, "country");
    m.put(AddressType_RENAMED.CONTINENT, "continent");
    m.put(AddressType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_1, "administrative_area_level_1");
    m.put(AddressType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_2, "administrative_area_level_2");
    m.put(AddressType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_3, "administrative_area_level_3");
    m.put(AddressType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_4, "administrative_area_level_4");
    m.put(AddressType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_5, "administrative_area_level_5");
    m.put(AddressType_RENAMED.COLLOQUIAL_AREA, "colloquial_area");
    m.put(AddressType_RENAMED.LOCALITY, "locality");
    m.put(AddressType_RENAMED.WARD, "ward");
    m.put(AddressType_RENAMED.SUBLOCALITY, "sublocality");
    m.put(AddressType_RENAMED.SUBLOCALITY_LEVEL_1, "sublocality_level_1");
    m.put(AddressType_RENAMED.SUBLOCALITY_LEVEL_2, "sublocality_level_2");
    m.put(AddressType_RENAMED.SUBLOCALITY_LEVEL_3, "sublocality_level_3");
    m.put(AddressType_RENAMED.SUBLOCALITY_LEVEL_4, "sublocality_level_4");
    m.put(AddressType_RENAMED.SUBLOCALITY_LEVEL_5, "sublocality_level_5");
    m.put(AddressType_RENAMED.NEIGHBORHOOD, "neighborhood");
    m.put(AddressType_RENAMED.PREMISE, "premise");
    m.put(AddressType_RENAMED.SUBPREMISE, "subpremise");
    m.put(AddressType_RENAMED.POSTAL_CODE, "postal_code");
    m.put(AddressType_RENAMED.NATURAL_FEATURE, "natural_feature");
    m.put(AddressType_RENAMED.AIRPORT, "airport");
    m.put(AddressType_RENAMED.PARK, "park");
    m.put(AddressType_RENAMED.POINT_OF_INTEREST, "point_of_interest");
    m.put(AddressType_RENAMED.POST_OFFICE, "post_office");
    m.put(AddressType_RENAMED.PLACE_OF_WORSHIP, "place_of_worship");
    m.put(AddressType_RENAMED.BUS_STATION, "bus_station");
    m.put(AddressType_RENAMED.TRAIN_STATION, "train_station");
    m.put(AddressType_RENAMED.SUBWAY_STATION, "subway_station");
    m.put(AddressType_RENAMED.TRANSIT_STATION, "transit_station");
    m.put(AddressType_RENAMED.CHURCH, "church");
    m.put(AddressType_RENAMED.PRIMARY_SCHOOL, "primary_school");
    m.put(AddressType_RENAMED.SECONDARY_SCHOOL, "secondary_school");
    m.put(AddressType_RENAMED.FINANCE, "finance");
    m.put(AddressType_RENAMED.ESTABLISHMENT, "establishment");
    m.put(AddressType_RENAMED.POSTAL_TOWN, "postal_town");
    m.put(AddressType_RENAMED.UNIVERSITY, "university");
    m.put(AddressType_RENAMED.STREET_NUMBER, "street_number");
    m.put(AddressType_RENAMED.FLOOR, "floor");
    m.put(AddressType_RENAMED.ROOM, "room");
    m.put(AddressType_RENAMED.POST_BOX, "post_box");
    m.put(AddressType_RENAMED.POSTAL_CODE_PREFIX, "postal_code_prefix");
    m.put(AddressType_RENAMED.POSTAL_CODE_SUFFIX, "postal_code_suffix");
    m.put(AddressType_RENAMED.MUSEUM, "museum");
    m.put(AddressType_RENAMED.LIGHT_RAIL_STATION, "light_rail_station");
    m.put(AddressType_RENAMED.SYNAGOGUE, "synagogue");
    m.put(AddressType_RENAMED.FOOD, "food");
    m.put(AddressType_RENAMED.GROCERY_OR_SUPERMARKET, "grocery_or_supermarket");
    m.put(AddressType_RENAMED.STORE, "store");
    m.put(AddressType_RENAMED.DRUGSTORE, "drugstore");
    m.put(AddressType_RENAMED.LAWYER, "lawyer");
    m.put(AddressType_RENAMED.HEALTH, "health");
    m.put(AddressType_RENAMED.INSURANCE_AGENCY, "insurance_agency");
    m.put(AddressType_RENAMED.GAS_STATION, "gas_station");
    m.put(AddressType_RENAMED.CAR_DEALER, "car_dealer");
    m.put(AddressType_RENAMED.CAR_REPAIR, "car_repair");
    m.put(AddressType_RENAMED.MEAL_TAKEAWAY, "meal_takeaway");
    m.put(AddressType_RENAMED.FURNITURE_STORE, "furniture_store");
    m.put(AddressType_RENAMED.HOME_GOODS_STORE, "home_goods_store");
    m.put(AddressType_RENAMED.SHOPPING_MALL, "shopping_mall");
    m.put(AddressType_RENAMED.GYM, "gym");
    m.put(AddressType_RENAMED.ACCOUNTING, "accounting");
    m.put(AddressType_RENAMED.MOVING_COMPANY, "moving_company");
    m.put(AddressType_RENAMED.LODGING, "lodging");
    m.put(AddressType_RENAMED.STORAGE, "storage");
    m.put(AddressType_RENAMED.CASINO, "casino");
    m.put(AddressType_RENAMED.PARKING, "parking");
    m.put(AddressType_RENAMED.STADIUM, "stadium");
    m.put(AddressType_RENAMED.TRAVEL_AGENCY, "travel_agency");
    m.put(AddressType_RENAMED.NIGHT_CLUB, "night_club");
    m.put(AddressType_RENAMED.BEAUTY_SALON, "beauty_salon");
    m.put(AddressType_RENAMED.HAIR_CARE, "hair_care");
    m.put(AddressType_RENAMED.SPA, "spa");
    m.put(AddressType_RENAMED.SHOE_STORE, "shoe_store");
    m.put(AddressType_RENAMED.BAKERY, "bakery");
    m.put(AddressType_RENAMED.PHARMACY, "pharmacy");
    m.put(AddressType_RENAMED.SCHOOL, "school");
    m.put(AddressType_RENAMED.BOOK_STORE, "book_store");
    m.put(AddressType_RENAMED.DEPARTMENT_STORE, "department_store");
    m.put(AddressType_RENAMED.RESTAURANT, "restaurant");
    m.put(AddressType_RENAMED.REAL_ESTATE_AGENCY, "real_estate_agency");
    m.put(AddressType_RENAMED.BAR, "bar");
    m.put(AddressType_RENAMED.DOCTOR, "doctor");
    m.put(AddressType_RENAMED.HOSPITAL, "hospital");
    m.put(AddressType_RENAMED.FIRE_STATION, "fire_station");
    m.put(AddressType_RENAMED.SUPERMARKET, "supermarket");
    m.put(AddressType_RENAMED.CITY_HALL, "city_hall");
    m.put(AddressType_RENAMED.LOCAL_GOVERNMENT_OFFICE, "local_government_office");
    m.put(AddressType_RENAMED.ATM, "atm");
    m.put(AddressType_RENAMED.BANK, "bank");
    m.put(AddressType_RENAMED.LIBRARY, "library");
    m.put(AddressType_RENAMED.CAR_WASH, "car_wash");
    m.put(AddressType_RENAMED.HARDWARE_STORE, "hardware_store");
    m.put(AddressType_RENAMED.AMUSEMENT_PARK, "amusement_park");
    m.put(AddressType_RENAMED.AQUARIUM, "aquarium");
    m.put(AddressType_RENAMED.ART_GALLERY, "art_gallery");
    m.put(AddressType_RENAMED.BICYCLE_STORE, "bicycle_store");
    m.put(AddressType_RENAMED.BOWLING_ALLEY, "bowling_alley");
    m.put(AddressType_RENAMED.CAFE, "cafe");
    m.put(AddressType_RENAMED.CAMPGROUND, "campground");
    m.put(AddressType_RENAMED.CAR_RENTAL, "car_rental");
    m.put(AddressType_RENAMED.CEMETERY, "cemetery");
    m.put(AddressType_RENAMED.CLOTHING_STORE, "clothing_store");
    m.put(AddressType_RENAMED.CONVENIENCE_STORE, "convenience_store");
    m.put(AddressType_RENAMED.COURTHOUSE, "courthouse");
    m.put(AddressType_RENAMED.DENTIST, "dentist");
    m.put(AddressType_RENAMED.ELECTRICIAN, "electrician");
    m.put(AddressType_RENAMED.ELECTRONICS_STORE, "electronics_store");
    m.put(AddressType_RENAMED.EMBASSY, "embassy");
    m.put(AddressType_RENAMED.FLORIST, "florist");
    m.put(AddressType_RENAMED.FUNERAL_HOME, "funeral_home");
    m.put(AddressType_RENAMED.GENERAL_CONTRACTOR, "general_contractor");
    m.put(AddressType_RENAMED.HINDU_TEMPLE, "hindu_temple");
    m.put(AddressType_RENAMED.JEWELRY_STORE, "jewelry_store");
    m.put(AddressType_RENAMED.LAUNDRY, "laundry");
    m.put(AddressType_RENAMED.LIQUOR_STORE, "liquor_store");
    m.put(AddressType_RENAMED.LOCKSMITH, "locksmith");
    m.put(AddressType_RENAMED.MEAL_DELIVERY, "meal_delivery");
    m.put(AddressType_RENAMED.MOSQUE, "mosque");
    m.put(AddressType_RENAMED.MOVIE_RENTAL, "movie_rental");
    m.put(AddressType_RENAMED.MOVIE_THEATER, "movie_theater");
    m.put(AddressType_RENAMED.PAINTER, "painter");
    m.put(AddressType_RENAMED.PET_STORE, "pet_store");
    m.put(AddressType_RENAMED.PHYSIOTHERAPIST, "physiotherapist");
    m.put(AddressType_RENAMED.PLUMBER, "plumber");
    m.put(AddressType_RENAMED.POLICE, "police");
    m.put(AddressType_RENAMED.ROOFING_CONTRACTOR, "roofing_contractor");
    m.put(AddressType_RENAMED.RV_PARK, "rv_park");
    m.put(AddressType_RENAMED.TAXI_STAND, "taxi_stand");
    m.put(AddressType_RENAMED.VETERINARY_CARE, "veterinary_care");
    m.put(AddressType_RENAMED.ZOO, "zoo");
    m.put(AddressType_RENAMED.ARCHIPELAGO, "archipelago");
    m.put(AddressType_RENAMED.TOURIST_ATTRACTION, "tourist_attraction");
    m.put(AddressType_RENAMED.TOWN_SQUARE, "town_square");

    for (Map.Entry<AddressType_RENAMED, String> addressTypeLiteralPair :
        addressTypeToLiteralMap.entrySet()) {
      assertEquals(
          addressTypeLiteralPair.getValue(), addressTypeLiteralPair.getKey().toCanonicalLiteral());
    }
    List<AddressType_RENAMED> enumsMinusUnknown = new ArrayList<>(Arrays.asList(AddressType_RENAMED.values()));
    enumsMinusUnknown.remove(AddressType_RENAMED.UNKNOWN);
    List<AddressType_RENAMED> onlyInTest = setdiff(addressTypeToLiteralMap.keySet(), enumsMinusUnknown);
    List<AddressType_RENAMED> onlyInEnum = setdiff(enumsMinusUnknown, addressTypeToLiteralMap.keySet());
    assertEquals(
        "Unexpected enum elements: Only in test: " + onlyInTest + ". Only in enum: " + onlyInEnum,
        addressTypeToLiteralMap.size() + 1, // 1 for unknown
        AddressType_RENAMED.values().length);
  }

  @Test
  public void testCanonicalLiteralsForAddressComponentType() {
    Map<AddressComponentType, String> addressComponentTypeToLiteralMap =
        new HashMap<AddressComponentType, String>();
    // Short alias just to avoid line wrapping in the below code
    Map<AddressComponentType, String> m = addressComponentTypeToLiteralMap;
    m.put(AddressComponentType.STREET_ADDRESS, "street_address");
    m.put(AddressComponentType.ROUTE, "route");
    m.put(AddressComponentType.INTERSECTION, "intersection");
    m.put(AddressComponentType.POLITICAL, "political");
    m.put(AddressComponentType.COUNTRY, "country");
    m.put(AddressComponentType.CONTINENT, "continent");
    m.put(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1, "administrative_area_level_1");
    m.put(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2, "administrative_area_level_2");
    m.put(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_3, "administrative_area_level_3");
    m.put(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_4, "administrative_area_level_4");
    m.put(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_5, "administrative_area_level_5");
    m.put(AddressComponentType.COLLOQUIAL_AREA, "colloquial_area");
    m.put(AddressComponentType.LOCALITY, "locality");
    m.put(AddressComponentType.WARD, "ward");
    m.put(AddressComponentType.SUBLOCALITY, "sublocality");
    m.put(AddressComponentType.SUBLOCALITY_LEVEL_1, "sublocality_level_1");
    m.put(AddressComponentType.SUBLOCALITY_LEVEL_2, "sublocality_level_2");
    m.put(AddressComponentType.SUBLOCALITY_LEVEL_3, "sublocality_level_3");
    m.put(AddressComponentType.SUBLOCALITY_LEVEL_4, "sublocality_level_4");
    m.put(AddressComponentType.SUBLOCALITY_LEVEL_5, "sublocality_level_5");
    m.put(AddressComponentType.NEIGHBORHOOD, "neighborhood");
    m.put(AddressComponentType.PREMISE, "premise");
    m.put(AddressComponentType.SUBPREMISE, "subpremise");
    m.put(AddressComponentType.POSTAL_CODE, "postal_code");
    m.put(AddressComponentType.POST_BOX, "post_box");
    m.put(AddressComponentType.POSTAL_CODE_PREFIX, "postal_code_prefix");
    m.put(AddressComponentType.POSTAL_CODE_SUFFIX, "postal_code_suffix");
    m.put(AddressComponentType.NATURAL_FEATURE, "natural_feature");
    m.put(AddressComponentType.AIRPORT, "airport");
    m.put(AddressComponentType.PARK, "park");
    m.put(AddressComponentType.FLOOR, "floor");
    m.put(AddressComponentType.PARKING, "parking");
    m.put(AddressComponentType.POINT_OF_INTEREST, "point_of_interest");
    m.put(AddressComponentType.BUS_STATION, "bus_station");
    m.put(AddressComponentType.TRAIN_STATION, "train_station");
    m.put(AddressComponentType.SUBWAY_STATION, "subway_station");
    m.put(AddressComponentType.TRANSIT_STATION, "transit_station");
    m.put(AddressComponentType.LIGHT_RAIL_STATION, "light_rail_station");
    m.put(AddressComponentType.ESTABLISHMENT, "establishment");
    m.put(AddressComponentType.POSTAL_TOWN, "postal_town");
    m.put(AddressComponentType.ROOM, "room");
    m.put(AddressComponentType.STREET_NUMBER, "street_number");
    m.put(AddressComponentType.GENERAL_CONTRACTOR, "general_contractor");
    m.put(AddressComponentType.FOOD, "food");
    m.put(AddressComponentType.REAL_ESTATE_AGENCY, "real_estate_agency");
    m.put(AddressComponentType.CAR_RENTAL, "car_rental");
    m.put(AddressComponentType.STORE, "store");
    m.put(AddressComponentType.SHOPPING_MALL, "shopping_mall");
    m.put(AddressComponentType.LODGING, "lodging");
    m.put(AddressComponentType.TRAVEL_AGENCY, "travel_agency");
    m.put(AddressComponentType.ELECTRONICS_STORE, "electronics_store");
    m.put(AddressComponentType.HOME_GOODS_STORE, "home_goods_store");
    m.put(AddressComponentType.SCHOOL, "school");
    m.put(AddressComponentType.ART_GALLERY, "art_gallery");
    m.put(AddressComponentType.LAWYER, "lawyer");
    m.put(AddressComponentType.RESTAURANT, "restaurant");
    m.put(AddressComponentType.BAR, "bar");
    m.put(AddressComponentType.MEAL_TAKEAWAY, "meal_takeaway");
    m.put(AddressComponentType.CLOTHING_STORE, "clothing_store");
    m.put(AddressComponentType.LOCAL_GOVERNMENT_OFFICE, "local_government_office");
    m.put(AddressComponentType.FINANCE, "finance");
    m.put(AddressComponentType.MOVING_COMPANY, "moving_company");
    m.put(AddressComponentType.STORAGE, "storage");
    m.put(AddressComponentType.CAFE, "cafe");
    m.put(AddressComponentType.CAR_REPAIR, "car_repair");
    m.put(AddressComponentType.HEALTH, "health");
    m.put(AddressComponentType.INSURANCE_AGENCY, "insurance_agency");
    m.put(AddressComponentType.PAINTER, "painter");
    m.put(AddressComponentType.ARCHIPELAGO, "archipelago");
    m.put(AddressComponentType.MUSEUM, "museum");
    m.put(AddressComponentType.RV_PARK, "rv_park");
    m.put(AddressComponentType.CAMPGROUND, "campground");
    m.put(AddressComponentType.MEAL_DELIVERY, "meal_delivery");
    m.put(AddressComponentType.PRIMARY_SCHOOL, "primary_school");
    m.put(AddressComponentType.SECONDARY_SCHOOL, "secondary_school");
    m.put(AddressComponentType.TOWN_SQUARE, "town_square");
    m.put(AddressComponentType.TOURIST_ATTRACTION, "tourist_attraction");
    m.put(AddressComponentType.PLUS_CODE, "plus_code");
    m.put(AddressComponentType.DRUGSTORE, "drugstore");

    for (Map.Entry<AddressComponentType, String> AddressComponentTypeLiteralPair :
        addressComponentTypeToLiteralMap.entrySet()) {
      assertEquals(
          AddressComponentTypeLiteralPair.getValue(),
          AddressComponentTypeLiteralPair.getKey().toCanonicalLiteral());
    }
    List<AddressComponentType> enumsMinusUnknown =
        new ArrayList<>(Arrays.asList(AddressComponentType.values()));
    enumsMinusUnknown.remove(AddressComponentType.UNKNOWN);
    List<AddressComponentType> onlyInTest =
        setdiff(addressComponentTypeToLiteralMap.keySet(), enumsMinusUnknown);
    List<AddressComponentType> onlyInEnum =
        setdiff(enumsMinusUnknown, addressComponentTypeToLiteralMap.keySet());
    assertEquals(
        "Unexpected enum elements: Only in test: " + onlyInTest + ". Only in enum: " + onlyInEnum,
        addressComponentTypeToLiteralMap.size() + 1, // 1 for unknown
        AddressComponentType.values().length);
  }

  private static <T extends UrlValue> void assertCannotGetUrlValue(T unknown) {
    assertNotNull(unknown);
    try {
      unknown.toUrlValue();
      fail("Expected to throw UnsupportedOperationException");
    } catch (UnsupportedOperationException expected) {
      // Expected.
    }
  }

  private static <T> List<T> setdiff(Collection<T> a, Collection<T> b) {
    List<T> out = new ArrayList<>(a);
    out.removeAll(b);
    return out;
  }
}
