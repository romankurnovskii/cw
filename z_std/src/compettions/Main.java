package compettions;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int m = Integer.parseInt(sc.nextLine());

		List<LocalTime[]> orders = new ArrayList<>();

		LocalTime[][] orders_ar = new LocalTime[m][2];

		for (int i = 0; i < m; i++) {
			String[] timeStrings = sc.nextLine().split(" ");

			LocalTime[] localTimes = new LocalTime[2];
			localTimes[0] = LocalTime.parse(timeStrings[0]);
			localTimes[1] = LocalTime.parse(timeStrings[1]);

			orders.add(localTimes);

			orders_ar[i][0] = localTimes[0];
			orders_ar[i][1] = localTimes[1];

		}

		Comparator<LocalTime[]> comparator = new Comparator<LocalTime[]>() {

			@Override
			public int compare(LocalTime[] o1, LocalTime[] o2) {
				return o1[0].compareTo(o2[0]);
			}

		};

		orders.sort(comparator);
		
		

		int c = 0;
//		LocalTime[] couriersTimes = new LocalTime[m];
		Arrays.sort(orders_ar);
		
		List<LocalTime> couriersTimesList = new ArrayList<>();
		
		
		for (int i = 0; i < orders_ar.length ; i++) {
			if (couriersTimesList.size() == 0) {
				couriersTimesList.add(orders_ar[i][1]); // add finish time
				c++;
			} else {
				if (Collections.min(couriersTimesList).compareTo(orders_ar[i][0]) <= 0) {
					couriersTimesList.remove(Collections.min(couriersTimesList));
				} else {
					couriersTimesList.add(orders_ar[i][1]);
					c++;
				}
			}
		}
		System.out.println(c);
	}

	
	
//	
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int m = Integer.parseInt(sc.nextLine());
//
//		List<LocalTime[]> orders = new ArrayList<>();
//
//		for (int i = 0; i < m; i++) {
//			String[] timeStrings = sc.nextLine().split(" ");
//
//			LocalTime[] localTimes = new LocalTime[2];
//			localTimes[0] = LocalTime.parse(timeStrings[0]);
//			localTimes[1] = LocalTime.parse(timeStrings[1]);
//
//			orders.add(localTimes);
//
//		}
//
//		Comparator<LocalTime[]> comparator = new Comparator<LocalTime[]>() {
//
//			@Override
//			public int compare(LocalTime[] o1, LocalTime[] o2) {
//				return o1[0].compareTo(o2[0]);
//			}
//
//		};
//
//		orders.sort(comparator);
//
//		int used_couriers = 0;
//
//		LocalTime[] available_at = new LocalTime[m];
//		for (int i = 0; i < available_at.length; i++) {
//			available_at[i] = orders.get(i)[0];
//		}
//
//		for (int order_id = 0; order_id < m; order_id++) {
//			int available_courier = -1;
//
//			for (int courier_id = 0; courier_id < m; courier_id++) {
//				if (orders.get(courier_id)[1].compareTo(orders.get(order_id)[0]) == 1) {
//					available_courier = courier_id;
//					break;
//				}
//			}
//
//			if (available_at[available_courier] == orders.get(order_id)[1]) {
//				used_couriers += 1;
//			}
//
//			available_at[available_courier] = orders.get(order_id)[0];
//		}
//
//		System.out.println(used_couriers);

	}



