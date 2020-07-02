package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entity.Runner;

public class TournamentPreparation {
	private static Map<String, List<Runner>> places = new HashMap<>();

	public static void createPlace(String place) {
		places.putIfAbsent(place, new ArrayList<>());
	}

	public static ArrayList<Runner> getListOfRunnersAtPlace(String place) {
		if (!places.containsKey(place)) {
			createPlace(place);
		}
		return (ArrayList<Runner>) places.get(place);
	}

	public static Places[] getPlaces() {
		return Places.values();
	}

}

enum Places {
	AA, BB
}
