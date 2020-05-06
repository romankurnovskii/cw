import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateTimetestAppl {

	public static void main(String[] args) {
		LocalDate current = LocalDate.now();
		System.out.println(current);
//		LocalDate birthDateASP = LocalDate.parse("1799-06-06");
//		LocalDate barMizvaASP = birthDateASP.plus(13,
//				ChronoUnit.YEARS);
//		LocalDate britASP = birthDateASP.plus(7, ChronoUnit.DAYS);
//		DateTimeFormatter format1 =
//				DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter format2 =
//				DateTimeFormatter.ofPattern("eeee d/MMMM/y",
//						Locale.forLanguageTag("th-TH-x-lvariant-TH"));
//		System.out.println(birthDateASP + " Birthdate of Pushkin");
//		System.out.println(barMizvaASP.format(format1) + " Bar Mizva of Pushkin ");
//		System.out.println(britASP.format(format2) + " Brit Mila of Pushkin");
//			ChronoUnit unit = ChronoUnit.DAYS;
//			System.out.printf("between %s and %s there are %d %s\n",
//					birthDateASP, barMizvaASP,
//					unit.between(birthDateASP, barMizvaASP), unit);
//		System.out.println(current.with
//				(new NextFriday13()));
		ZonedDateTime zdt =
				ZonedDateTime.now(ZoneId.of("GMT+5"));
		
		System.out.println(zdt);
//		for(String zoneId: ZoneId.getAvailableZoneIds()) {
//			System.out.println(zoneId);
//		}

	}

}
