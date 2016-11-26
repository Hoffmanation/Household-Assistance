package logic.Bean;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import household.enumeration.Category;
import household.enumeration.Payment;


public interface ReportLogicDao {
	//method to search reports  by all values available 
	public List<Input> getReport(String person,Date fromDate,Date untilDate,double fromPay,double UntillPay,Category category,Payment payment )throws ParseException;
	public List<Input> getAllReport()throws ParseException;
	
	//method to search reports  without the Amount value 
	public List<Input> getReportByDescription(String description)throws ParseException;
	public List<Input> getReportByCategoryAndDate(Date fromDate , Date untilDate, Category category)throws ParseException;
	public List<Input> getReportByPersonAndDate(String person,Date Start,Date end) throws ParseException;
	public List<Input> getReportByPaymentAndDate(Payment payment,Date Start,Date end) throws ParseException;
	public List<Input> getReportByPersonCategoryAndDate(Date fromDate , Date untilDate, Category category,String person)throws ParseException;
	public List<Input> getReportByCategoryPaymentTypeAndDate(Date fromDate , Date untilDate, Category category,Payment paymentType)throws ParseException;
	public List<Input> getReportByDate(Date fromDate , Date untilDate)throws ParseException;
	public List<Input> getReportByPersonPaymentAndDate(Date fromDate , Date untilDate, Payment payment,String person)throws ParseException;
	public List<Input> getReportWithountPayment(String person,Date fromDate,Date untilDate,Category category,Payment payment )throws ParseException;

	
//method to search reports with  the amount	value 
	public List<Input> getReportByAmount(double fromPay,double UntillPay)throws ParseException;
	public List<Input> getReportByAmountAndDate(Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByPersonAmountAndDate(String person,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByCategoryAmountAndDate(Category category,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByPaymentAmountAndDate(Payment payment ,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByPersonCategoryAmountAndDate(String person,Category category,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByPersonPaymentAmountAndDate(String person,Payment payment,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;
	public List<Input> getReportByCategoryPaymentAmountAndDate(Category category,Payment payment,Date fromDate , Date untilDate,double fromPay,double UntillPay )throws ParseException;

	
	
	
}
