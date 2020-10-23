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
    assertNotNull(AddressComponentType_RENAMED.UNKNOWN); // Does not implement UrlValue.

    assertCannotGetUrlValue(AddressType.UNKNOWN);
    assertCannotGetUrlValue(LocationType.UNKNOWN);
    assertCannotGetUrlValue(TravelMode.UNKNOWN);
  }

  @Test
  public void testCanonicalLiteralsForAddressType() {
    Map<AddressType, String> addressTypeToLiteralMap = new HashMap<AddressType, String>();
    // Short alias just to avoid line wrapping in the below code
    Map<AddressType, String> m = addressTypeToLiteralMap;
    m.put(AddressType.STREET_ADDRESS, "street_address");
    m.put(AddressType.ROUTE, "route");
    m.put(AddressType.INTERSECTION, "intersection");
    m.put(AddressType.POLITICAL, "political");
    m.put(AddressType.COUNTRY, "country");
    m.put(AddressType.CONTINENT, "continent");
    m.put(AddressType.ADMINISTRATIVE_AREA_LEVEL_1, "administrative_area_level_1");
    m.put(AddressType.ADMINISTRATIVE_AREA_LEVEL_2, "administrative_area_level_2");
    m.put(AddressType.ADMINISTRATIVE_AREA_LEVEL_3, "administrative_area_level_3");
    m.put(AddressType.ADMINISTRATIVE_AREA_LEVEL_4, "administrative_area_level_4");
    m.put(AddressType.ADMINISTRATIVE_AREA_LEVEL_5, "administrative_area_level_5");
    m.put(AddressType.COLLOQUIAL_AREA, "colloquial_area");
    m.put(AddressType.LOCALITY, "locality");
    m.put(AddressType.WARD, "ward");
    m.put(AddressType.SUBLOCALITY, "sublocality");
    m.put(AddressType.SUBLOCALITY_LEVEL_1, "sublocality_level_1");
    m.put(AddressType.SUBLOCALITY_LEVEL_2, "sublocality_level_2");
    m.put(AddressType.SUBLOCALITY_LEVEL_3, "sublocality_level_3");
    m.put(AddressType.SUBLOCALITY_LEVEL_4, "sublocality_level_4");
    m.put(AddressType.SUBLOCALITY_LEVEL_5, "sublocality_level_5");
    m.put(AddressType.NEIGHBORHOOD, "neighborhood");
    m.put(AddressType.PREMISE, "premise");
    m.put(AddressType.SUBPREMISE, "subpremise");
    m.put(AddressType.POSTAL_CODE, "postal_code");
    m.put(AddressType.NATURAL_FEATURE, "natural_feature");
    m.put(AddressType.AIRPORT, "airport");
    m.put(AddressType.PARK, "park");
    m.put(AddressType.POINT_OF_INTEREST, "point_of_interest");
    m.put(AddressType.POST_OFFICE, "post_office");
    m.put(AddressType.PLACE_OF_WORSHIP, "place_of_worship");
    m.put(AddressType.BUS_STATION, "bus_station");
    m.put(AddressType.TRAIN_STATION, "train_station");
    m.put(AddressType.SUBWAY_STATION, "subway_station");
    m.put(AddressType.TRANSIT_STATION, "transit_station");
    m.put(AddressType.CHURCH, "church");
    m.put(AddressType.PRIMARY_SCHOOL, "primary_school");
    m.put(AddressType.SECONDARY_SCHOOL, "secondary_school");
    m.put(AddressType.FINANCE, "finance");
    m.put(AddressType.ESTABLISHMENT, "establishment");
    m.put(AddressType.POSTAL_TOWN, "postal_town");
    m.put(AddressType.UNIVERSITY, "university");
    m.put(AddressType.STREET_NUMBER, "street_number");
    m.put(AddressType.FLOOR, "floor");
    m.put(AddressType.ROOM, "room");
    m.put(AddressType.POST_BOX, "post_box");
    m.put(AddressType.POSTAL_CODE_PREFIX, "postal_code_prefix");
    m.put(AddressType.POSTAL_CODE_SUFFIX, "postal_code_suffix");
    m.put(AddressType.MUSEUM, "museum");
    m.put(AddressType.LIGHT_RAIL_STATION, "light_rail_station");
    m.put(AddressType.SYNAGOGUE, "synagogue");
    m.put(AddressType.FOOD, "food");
    m.put(AddressType.GROCERY_OR_SUPERMARKET, "grocery_or_supermarket");
    m.put(AddressType.STORE, "store");
    m.put(AddressType.DRUGSTORE, "drugstore");
    m.put(AddressType.LAWYER, "lawyer");
    m.put(AddressType.HEALTH, "health");
    m.put(AddressType.INSURANCE_AGENCY, "insurance_agency");
    m.put(AddressType.GAS_STATION, "gas_station");
    m.put(AddressType.CAR_DEALER, "car_dealer");
    m.put(AddressType.CAR_REPAIR, "car_repair");
    m.put(AddressType.MEAL_TAKEAWAY, "meal_takeaway");
    m.put(AddressType.FURNITURE_STORE, "furniture_store");
    m.put(AddressType.HOME_GOODS_STORE, "home_goods_store");
    m.put(AddressType.SHOPPING_MALL, "shopping_mall");
    m.put(AddressType.GYM, "gym");
    m.put(AddressType.ACCOUNTING, "accounting");
    m.put(AddressType.MOVING_COMPANY, "moving_company");
    m.put(AddressType.LODGING, "lodging");
    m.put(AddressType.STORAGE, "storage");
    m.put(AddressType.CASINO, "casino");
    m.put(AddressType.PARKING, "parking");
    m.put(AddressType.STADIUM, "stadium");
    m.put(AddressType.TRAVEL_AGENCY, "travel_agency");
    m.put(AddressType.NIGHT_CLUB, "night_club");
    m.put(AddressType.BEAUTY_SALON, "beauty_salon");
    m.put(AddressType.HAIR_CARE, "hair_care");
    m.put(AddressType.SPA, "spa");
    m.put(AddressType.SHOE_STORE, "shoe_store");
    m.put(AddressType.BAKERY, "bakery");
    m.put(AddressType.PHARMACY, "pharmacy");
    m.put(AddressType.SCHOOL, "school");
    m.put(AddressType.BOOK_STORE, "book_store");
    m.put(AddressType.DEPARTMENT_STORE, "department_store");
    m.put(AddressType.RESTAURANT, "restaurant");
    m.put(AddressType.REAL_ESTATE_AGENCY, "real_estate_agency");
    m.put(AddressType.BAR, "bar");
    m.put(AddressType.DOCTOR, "doctor");
    m.put(AddressType.HOSPITAL, "hospital");
    m.put(AddressType.FIRE_STATION, "fire_station");
    m.put(AddressType.SUPERMARKET, "supermarket");
    m.put(AddressType.CITY_HALL, "city_hall");
    m.put(AddressType.LOCAL_GOVERNMENT_OFFICE, "local_government_office");
    m.put(AddressType.ATM, "atm");
    m.put(AddressType.BANK, "bank");
    m.put(AddressType.LIBRARY, "library");
    m.put(AddressType.CAR_WASH, "car_wash");
    m.put(AddressType.HARDWARE_STORE, "hardware_store");
    m.put(AddressType.AMUSEMENT_PARK, "amusement_park");
    m.put(AddressType.AQUARIUM, "aquarium");
    m.put(AddressType.ART_GALLERY, "art_gallery");
    m.put(AddressType.BICYCLE_STORE, "bicycle_store");
    m.put(AddressType.BOWLING_ALLEY, "bowling_alley");
    m.put(AddressType.CAFE, "cafe");
    m.put(AddressType.CAMPGROUND, "campground");
    m.put(AddressType.CAR_RENTAL, "car_rental");
    m.put(AddressType.CEMETERY, "cemetery");
    m.put(AddressType.CLOTHING_STORE, "clothing_store");
    m.put(AddressType.CONVENIENCE_STORE, "convenience_store");
    m.put(AddressType.COURTHOUSE, "courthouse");
    m.put(AddressType.DENTIST, "dentist");
    m.put(AddressType.ELECTRICIAN, "electrician");
    m.put(AddressType.ELECTRONICS_STORE, "electronics_store");
    m.put(AddressType.EMBASSY, "embassy");
    m.put(AddressType.FLORIST, "florist");
    m.put(AddressType.FUNERAL_HOME, "funeral_home");
    m.put(AddressType.GENERAL_CONTRACTOR, "general_contractor");
    m.put(AddressType.HINDU_TEMPLE, "hindu_temple");
    m.put(AddressType.JEWELRY_STORE, "jewelry_store");
    m.put(AddressType.LAUNDRY, "laundry");
    m.put(AddressType.LIQUOR_STORE, "liquor_store");
    m.put(AddressType.LOCKSMITH, "locksmith");
    m.put(AddressType.MEAL_DELIVERY, "meal_delivery");
    m.put(AddressType.MOSQUE, "mosque");
    m.put(AddressType.MOVIE_RENTAL, "movie_rental");
    m.put(AddressType.MOVIE_THEATER, "movie_theater");
    m.put(AddressType.PAINTER, "painter");
    m.put(AddressType.PET_STORE, "pet_store");
    m.put(AddressType.PHYSIOTHERAPIST, "physiotherapist");
    m.put(AddressType.PLUMBER, "plumber");
    m.put(AddressType.POLICE, "police");
    m.put(AddressType.ROOFING_CONTRACTOR, "roofing_contractor");
    m.put(AddressType.RV_PARK, "rv_park");
    m.put(AddressType.TAXI_STAND, "taxi_stand");
    m.put(AddressType.VETERINARY_CARE, "veterinary_care");
    m.put(AddressType.ZOO, "zoo");
    m.put(AddressType.ARCHIPELAGO, "archipelago");
    m.put(AddressType.TOURIST_ATTRACTION, "tourist_attraction");
    m.put(AddressType.TOWN_SQUARE, "town_square");

    for (Map.Entry<AddressType, String> addressTypeLiteralPair :
        addressTypeToLiteralMap.entrySet()) {
      assertEquals(
          addressTypeLiteralPair.getValue(), addressTypeLiteralPair.getKey().toCanonicalLiteral());
    }
    List<AddressType> enumsMinusUnknown = new ArrayList<>(Arrays.asList(AddressType.values()));
    enumsMinusUnknown.remove(AddressType.UNKNOWN);
    List<AddressType> onlyInTest = setdiff(addressTypeToLiteralMap.keySet(), enumsMinusUnknown);
    List<AddressType> onlyInEnum = setdiff(enumsMinusUnknown, addressTypeToLiteralMap.keySet());
    assertEquals(
        "Unexpected enum elements: Only in test: " + onlyInTest + ". Only in enum: " + onlyInEnum,
        addressTypeToLiteralMap.size() + 1, // 1 for unknown
        AddressType.values().length);
  }

  @Test
  public void testCanonicalLiteralsForAddressComponentType() {
    Map<AddressComponentType_RENAMED, String> addressComponentTypeToLiteralMap =
        new HashMap<AddressComponentType_RENAMED, String>();
    // Short alias just to avoid line wrapping in the below code
    Map<AddressComponentType_RENAMED, String> m = addressComponentTypeToLiteralMap;
    m.put(AddressComponentType_RENAMED.STREET_ADDRESS, "street_address");
    m.put(AddressComponentType_RENAMED.ROUTE, "route");
    m.put(AddressComponentType_RENAMED.INTERSECTION, "intersection");
    m.put(AddressComponentType_RENAMED.POLITICAL, "political");
    m.put(AddressComponentType_RENAMED.COUNTRY, "country");
    m.put(AddressComponentType_RENAMED.CONTINENT, "continent");
    m.put(AddressComponentType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_1, "administrative_area_level_1");
    m.put(AddressComponentType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_2, "administrative_area_level_2");
    m.put(AddressComponentType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_3, "administrative_area_level_3");
    m.put(AddressComponentType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_4, "administrative_area_level_4");
    m.put(AddressComponentType_RENAMED.ADMINISTRATIVE_AREA_LEVEL_5, "administrative_area_level_5");
    m.put(AddressComponentType_RENAMED.COLLOQUIAL_AREA, "colloquial_area");
    m.put(AddressComponentType_RENAMED.LOCALITY, "locality");
    m.put(AddressComponentType_RENAMED.WARD, "ward");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY, "sublocality");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY_LEVEL_1, "sublocality_level_1");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY_LEVEL_2, "sublocality_level_2");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY_LEVEL_3, "sublocality_level_3");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY_LEVEL_4, "sublocality_level_4");
    m.put(AddressComponentType_RENAMED.SUBLOCALITY_LEVEL_5, "sublocality_level_5");
    m.put(AddressComponentType_RENAMED.NEIGHBORHOOD, "neighborhood");
    m.put(AddressComponentType_RENAMED.PREMISE, "premise");
    m.put(AddressComponentType_RENAMED.SUBPREMISE, "subpremise");
    m.put(AddressComponentType_RENAMED.POSTAL_CODE, "postal_code");
    m.put(AddressComponentType_RENAMED.POST_BOX, "post_box");
    m.put(AddressComponentType_RENAMED.POSTAL_CODE_PREFIX, "postal_code_prefix");
    m.put(AddressComponentType_RENAMED.POSTAL_CODE_SUFFIX, "postal_code_suffix");
    m.put(AddressComponentType_RENAMED.NATURAL_FEATURE, "natural_feature");
    m.put(AddressComponentType_RENAMED.AIRPORT, "airport");
    m.put(AddressComponentType_RENAMED.PARK, "park");
    m.put(AddressComponentType_RENAMED.FLOOR, "floor");
    m.put(AddressComponentType_RENAMED.PARKING, "parking");
    m.put(AddressComponentType_RENAMED.POINT_OF_INTEREST, "point_of_interest");
    m.put(AddressComponentType_RENAMED.BUS_STATION, "bus_station");
    m.put(AddressComponentType_RENAMED.TRAIN_STATION, "train_station");
    m.put(AddressComponentType_RENAMED.SUBWAY_STATION, "subway_station");
    m.put(AddressComponentType_RENAMED.TRANSIT_STATION, "transit_station");
    m.put(AddressComponentType_RENAMED.LIGHT_RAIL_STATION, "light_rail_station");
    m.put(AddressComponentType_RENAMED.ESTABLISHMENT, "establishment");
    m.put(AddressComponentType_RENAMED.POSTAL_TOWN, "postal_town");
    m.put(AddressComponentType_RENAMED.ROOM, "room");
    m.put(AddressComponentType_RENAMED.STREET_NUMBER, "street_number");
    m.put(AddressComponentType_RENAMED.GENERAL_CONTRACTOR, "general_contractor");
    m.put(AddressComponentType_RENAMED.FOOD, "food");
    m.put(AddressComponentType_RENAMED.REAL_ESTATE_AGENCY, "real_estate_agency");
    m.put(AddressComponentType_RENAMED.CAR_RENTAL, "car_rental");
    m.put(AddressComponentType_RENAMED.STORE, "store");
    m.put(AddressComponentType_RENAMED.SHOPPING_MALL, "shopping_mall");
    m.put(AddressComponentType_RENAMED.LODGING, "lodging");
    m.put(AddressComponentType_RENAMED.TRAVEL_AGENCY, "travel_agency");
    m.put(AddressComponentType_RENAMED.ELECTRONICS_STORE, "electronics_store");
    m.put(AddressComponentType_RENAMED.HOME_GOODS_STORE, "home_goods_store");
    m.put(AddressComponentType_RENAMED.SCHOOL, "school");
    m.put(AddressComponentType_RENAMED.ART_GALLERY, "art_gallery");
    m.put(AddressComponentType_RENAMED.LAWYER, "lawyer");
    m.put(AddressComponentType_RENAMED.RESTAURANT, "restaurant");
    m.put(AddressComponentType_RENAMED.BAR, "bar");
    m.put(AddressComponentType_RENAMED.MEAL_TAKEAWAY, "meal_takeaway");
    m.put(AddressComponentType_RENAMED.CLOTHING_STORE, "clothing_store");
    m.put(AddressComponentType_RENAMED.LOCAL_GOVERNMENT_OFFICE, "local_government_office");
    m.put(AddressComponentType_RENAMED.FINANCE, "finance");
    m.put(AddressComponentType_RENAMED.MOVING_COMPANY, "moving_company");
    m.put(AddressComponentType_RENAMED.STORAGE, "storage");
    m.put(AddressComponentType_RENAMED.CAFE, "cafe");
    m.put(AddressComponentType_RENAMED.CAR_REPAIR, "car_repair");
    m.put(AddressComponentType_RENAMED.HEALTH, "health");
    m.put(AddressComponentType_RENAMED.INSURANCE_AGENCY, "insurance_agency");
    m.put(AddressComponentType_RENAMED.PAINTER, "painter");
    m.put(AddressComponentType_RENAMED.ARCHIPELAGO, "archipelago");
    m.put(AddressComponentType_RENAMED.MUSEUM, "museum");
    m.put(AddressComponentType_RENAMED.RV_PARK, "rv_park");
    m.put(AddressComponentType_RENAMED.CAMPGROUND, "campground");
    m.put(AddressComponentType_RENAMED.MEAL_DELIVERY, "meal_delivery");
    m.put(AddressComponentType_RENAMED.PRIMARY_SCHOOL, "primary_school");
    m.put(AddressComponentType_RENAMED.SECONDARY_SCHOOL, "secondary_school");
    m.put(AddressComponentType_RENAMED.TOWN_SQUARE, "town_square");
    m.put(AddressComponentType_RENAMED.TOURIST_ATTRACTION, "tourist_attraction");
    m.put(AddressComponentType_RENAMED.PLUS_CODE, "plus_code");
    m.put(AddressComponentType_RENAMED.DRUGSTORE, "drugstore");

    for (Map.Entry<AddressComponentType_RENAMED, String> AddressComponentTypeLiteralPair :
        addressComponentTypeToLiteralMap.entrySet()) {
      assertEquals(
          AddressComponentTypeLiteralPair.getValue(),
          AddressComponentTypeLiteralPair.getKey().toCanonicalLiteral());
    }
    List<AddressComponentType_RENAMED> enumsMinusUnknown =
        new ArrayList<>(Arrays.asList(AddressComponentType_RENAMED.values()));
    enumsMinusUnknown.remove(AddressComponentType_RENAMED.UNKNOWN);
    List<AddressComponentType_RENAMED> onlyInTest =
        setdiff(addressComponentTypeToLiteralMap.keySet(), enumsMinusUnknown);
    List<AddressComponentType_RENAMED> onlyInEnum =
        setdiff(enumsMinusUnknown, addressComponentTypeToLiteralMap.keySet());
    assertEquals(
        "Unexpected enum elements: Only in test: " + onlyInTest + ". Only in enum: " + onlyInEnum,
        addressComponentTypeToLiteralMap.size() + 1, // 1 for unknown
        AddressComponentType_RENAMED.values().length);
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
