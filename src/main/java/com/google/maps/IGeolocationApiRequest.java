package com.google.maps;

import com.google.maps.model.CellTower;
import com.google.maps.model.GeolocationPayload;
import com.google.maps.model.WifiAccessPoint;

public interface IGeolocationApiRequest {

	IGeolocationApiRequest HomeMobileCountryCode(int newHomeMobileCountryCode);

	IGeolocationApiRequest HomeMobileNetworkCode(int newHomeMobileNetworkCode);

	IGeolocationApiRequest RadioType(String newRadioType);

	IGeolocationApiRequest Carrier(String newCarrier);

	IGeolocationApiRequest ConsiderIp(boolean newConsiderIp);

	IGeolocationApiRequest CellTowers(CellTower[] newCellTowers);

	IGeolocationApiRequest AddCellTower(CellTower newCellTower);

	IGeolocationApiRequest WifiAccessPoints(WifiAccessPoint[] newWifiAccessPoints);

	IGeolocationApiRequest AddWifiAccessPoint(WifiAccessPoint newWifiAccessPoint);

	IGeolocationApiRequest Payload(GeolocationPayload payload);

	IGeolocationApiRequest CreatePayload();

}