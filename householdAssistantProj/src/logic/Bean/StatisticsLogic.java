package logic.Bean;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import household.enumeration.Category;

//class providing methods for calculate and display statistics charts of the reports objects 
public class StatisticsLogic implements StatisticsLogicDao {

	static java.util.List<Input> deserializedInputs;
	static java.util.List<Input> reports = new ArrayList<>();
	private int nextYear = Calendar.getInstance().get(Calendar.YEAR)+1;
	private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private int lastYear = Calendar.getInstance().get(Calendar.YEAR) -1 ;
	private int last2Years = Calendar.getInstance().get(Calendar.YEAR) -2 ;
	private int last3Years = Calendar.getInstance().get(Calendar.YEAR) -3 ;
	private int last4Years = Calendar.getInstance().get(Calendar.YEAR) -4 ;
	private int last5Years = Calendar.getInstance().get(Calendar.YEAR) -5 ;

	public StatisticsLogic() {
		try {
			// importing the database file inserting all values to an arrayList
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("data\\reports.obg"));
			deserializedInputs = (java.util.List<Input>) ois.readObject();
			reports = deserializedInputs;
			ois.close();

		} catch (Exception v) {
			v.printStackTrace();
		}
	}

	//-------search methods by filtering values and attributes ,Get statistics by Specific attributes
	@Override
	public double getReportByPubCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Pub) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByAnimalCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Animals) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv = rv + input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByGroceriesCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Groceries) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByResturantCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Restaurant) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByHouseholdCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Household) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByElectronicsCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Electronics) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByPharmaceuticalCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Pharmaceutical) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByOtherCategoryAndDate(Date fromDate, Date untilDate) throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(Category.Other) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	// Get statistics from a specific months or years
	@Override
	public double getReportByJan() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(lastYear+"-12-31")) && input.getDate().before(formatter(currentYear+"-02-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByFab() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-01-31")) && input.getDate().before(formatter(currentYear+"-03-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByMar() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-02-31")) && input.getDate().before(formatter(currentYear+"-04-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByApr() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-03-31")) && input.getDate().before(formatter(currentYear+"-05-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByMay() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-04-31")) && input.getDate().before(formatter(currentYear+"-06-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByJun() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-05-31")) && input.getDate().before(formatter(currentYear+"-07-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByjuly() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-06-31")) && input.getDate().before(formatter(currentYear+"-08-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByAug() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-07-31")) && input.getDate().before(formatter(currentYear+"-09-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportBySep() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-08-31")) && input.getDate().before(formatter(currentYear+"-10-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByOct() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-09-31")) && input.getDate().before(formatter(currentYear+"-11-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByNov() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-10-31")) && input.getDate().before(formatter(currentYear+"-12-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByDec() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(currentYear+"-11-31")) && input.getDate().before(formatter(nextYear+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}



	@Override
	public double getReportByYear1() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(lastYear+"-11-31")) && input.getDate().before(formatter(nextYear+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByYear2() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(last2Years+"-11-31")) && input.getDate().before(formatter(currentYear+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByYear3() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(last3Years+"-11-31")) && input.getDate().before(formatter(lastYear+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByYear4() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(last4Years+"-11-31")) && input.getDate().before(formatter(last2Years+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}

	@Override
	public double getReportByYear5() throws ParseException {
		double rv = 0;
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(formatter(last5Years+"-11-31")) && input.getDate().before(formatter(last3Years+"-01-1"))) {
				newList.add(input);
			}
			for (Input input1 : newList) {
				rv += input1.getAmount();
			}
		}
		return rv;
	}
	
	public Date formatter(String string) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(string);
		java.sql.Date newDate = new java.sql.Date(myDate.getTime());
		return newDate;

	}

}
