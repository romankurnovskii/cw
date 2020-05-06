
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DaysAppl {
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();

		DayOfWeek[] dayOff = { DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY };
		WorkingDays workingDays = new WorkingDays(5, dayOff);
		System.out.println(workingDays.adjustInto(date));
		howMany("06/06/1799");
		TimeZone("CanADA");

	}

	private static void TimeZone(String zone) {
		for (String zoneId : ZoneId.getAvailableZoneIds()) {
			if(zoneId.toLowerCase().contains(zone.toLowerCase())){
			System.out.println(zoneId);
			}
		}

	}

	public static void howMany(String date) {
		LocalDate current = LocalDate.now();
		String res = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		String[] ar = date.split("/");
//		date = ar[2]+"-"+ar[1]+"-"+ar[0];
		try {
			LocalDate dateFrom = LocalDate.parse(date, format);
			res = getString(current, dateFrom);

		} catch (Exception e) {
			try {
				LocalDate dateFrom = LocalDate.parse(date);
				res = getString(current, dateFrom);

			} catch (Exception e1) {
				res = e1.getMessage();
			}
			res = e.getMessage();
		}

		System.out.println(res);
	}

	private static String getString(LocalDate current, LocalDate dateFrom) {
		Period from = Period.between(dateFrom, current);
		String res = "Years " + from.getYears() + " Month " + from.getMonths() + " Days " + from.getDays();
		return res;
//		ChronoUnit unit = ChronoUnit.YEARS;
//		System.out.printf(" %d %s", unit.between(dateFrom, current), unit);
//		unit = ChronoUnit.MONTHS;
//		System.out.printf(" %d %s", unit.between(dateFrom, current) % 12, unit);
//		unit = ChronoUnit.DAYS;
//		System.out.printf(" %d %s", from.getDays(), unit);
	}
}
