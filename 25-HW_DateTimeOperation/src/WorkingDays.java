import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDays implements TemporalAdjuster {

	int number;
	DayOfWeek[] dayOff;
	public WorkingDays (int number, DayOfWeek[] dayOff) {
		this.number = number;
		this.dayOff = dayOff;
		
	}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal res = temporal;// = getDayOff(temporal);
		int i = 0;
		while(i < number) {
			if (!isDayOff(res)) {
				i++;
				}
			res =  res.plus(1, ChronoUnit.DAYS);
		}
		return res;
	}
	private boolean isDayOff(Temporal res) {
		for(int i = 0; i < dayOff.length; i++) {
			if(res.get(ChronoField.DAY_OF_WEEK) == dayOff[i].getValue()) {
				return true;
			}
		}
		return false;
	}

}
