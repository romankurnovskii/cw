import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int[][] meanGroups(int[][] a) {

		int[] tempList = new int[a.length];
		int i = 0;

		Map<Float, List<Integer>> map = new TreeMap<>();
		List<Integer> ar = new ArrayList<>();

		int indexTmp = 0;

		for (int[] tmp : a) {
			int sum = 0;

			indexTmp++;
			for (int el : tmp) {
				sum += el;
			}

			float mean = sum / tmp.length;

			if (map.containsKey(mean)) {
				ar = map.get(mean);
				ar.add(indexTmp);
				map.put(mean, ar);
			} else {
				ar.clear();
				ar.add(indexTmp);
				map.put(mean, ar);
			}

		}

		List<List<Integer>> resList = new ArrayList<>();
		map.forEach((k, v) -> resList.add(v));
	
		int[][] dsa = (int[][]) resList.toArray();
		
		
		map.forEach((k, v) -> v.toArray().toString());
		return null;
		

	}

}
