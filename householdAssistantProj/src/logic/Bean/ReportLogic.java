package logic.Bean;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import household.enumeration.Category;
import household.enumeration.Payment;

//class providing methods for calculate filter and display the reports objects in database table like  
public class ReportLogic implements ReportLogicDao {

	static java.util.List<Input> deserializedInputs;
	static java.util.List<Input> reports = new ArrayList<>();

	public ReportLogic() {
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

	// ----------------search methods by filtering values and attributes
	@Override
	public List<Input> getReport(String person, Date fromDate, Date untilDate, double fromPay, double UntillPay,
			Category category, Payment payment) {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getDate().after(fromDate) && input.getDate().before(untilDate)
					&& input.getAmount() >= fromPay && input.getAmount() <= UntillPay
					&& input.getCategory().equals(category) && input.getPaymentType().equals(payment)) {
				newList.add(input);
			}
		}

		return newList;
	}

	@Override
	public List<Input> getAllReport() {
		return reports;
	}

	@Override
	public List<Input> getReportWithountPayment(String person, Date fromDate, Date untilDate, Category category,
			Payment payment) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getDate().after(fromDate) && input.getDate().before(untilDate)
					&& input.getCategory().equals(category) && input.getPaymentType().equals(payment)) {
				newList.add(input);
			}
		}

		return newList;
	}

	@Override
	public List<Input> getReportByPersonAndDate(String person, Date Start, Date end) {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getDate().after(Start) && input.getDate().before(end)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByDate(Date fromDate, Date untilDate) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByDescription(String description) {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getDescription().contains(description)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByCategoryPaymentTypeAndDate(Date fromDate, Date untilDate, Category category,
			Payment paymentType) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPaymentType().equals(paymentType) && input.getCategory().equals(category)
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPersonCategoryAndDate(Date fromDate, Date untilDate, Category category, String person)
			throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getCategory().equals(category)
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByCategoryAndDate(Date fromDate, Date untilDate, Category category)
			throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(category) && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPaymentAndDate(Payment payment, Date Start, Date end) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPaymentType().equals(payment) && input.getDate().after(Start) && input.getDate().before(end)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPersonPaymentAndDate(Date fromDate, Date untilDate, Payment payment, String person)
			throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getPaymentType().equals(payment)
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByAmountAndDate(Date fromDate, Date untilDate, double fromPay, double UntillPay)
			throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getAmount() >= fromPay && input.getAmount() <= UntillPay && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByAmount(double fromPay, double UntillPay) {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getAmount() >= fromPay && input.getAmount() <= UntillPay) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPersonAmountAndDate(String person, Date fromDate, Date untilDate, double fromPay,
			double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getAmount() >= fromPay && input.getAmount() <= UntillPay
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByCategoryAmountAndDate(Category category, Date fromDate, Date untilDate,
			double fromPay, double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getCategory().equals(category) && input.getAmount() >= fromPay && input.getAmount() <= UntillPay
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPaymentAmountAndDate(Payment payment, Date fromDate, Date untilDate, double fromPay,
			double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPaymentType().equals(payment) && input.getAmount() >= fromPay && input.getAmount() <= UntillPay
					&& input.getDate().after(fromDate) && input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPersonCategoryAmountAndDate(String person, Category category, Date fromDate,
			Date untilDate, double fromPay, double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getCategory().equals(category) && input.getAmount() >= fromPay
					&& input.getAmount() <= UntillPay && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByPersonPaymentAmountAndDate(String person, Payment payment, Date fromDate,
			Date untilDate, double fromPay, double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPerson().equals(person) && input.getPaymentType().equals(payment)
					&& input.getAmount() >= fromPay && input.getAmount() <= UntillPay && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

	@Override
	public List<Input> getReportByCategoryPaymentAmountAndDate(Category category, Payment payment, Date fromDate,
			Date untilDate, double fromPay, double UntillPay) throws ParseException {
		List<Input> newList = new ArrayList<>();
		for (Input input : reports) {
			if (input.getPaymentType().equals(payment) && input.getCategory().equals(category)
					&& input.getAmount() >= fromPay && input.getAmount() <= UntillPay && input.getDate().after(fromDate)
					&& input.getDate().before(untilDate)) {
				newList.add(input);
			}
		}
		return newList;
	}

}