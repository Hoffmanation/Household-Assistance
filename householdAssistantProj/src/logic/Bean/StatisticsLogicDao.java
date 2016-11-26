package logic.Bean;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import household.enumeration.Category;
import household.enumeration.Payment;


public interface StatisticsLogicDao {
	
	//method to search reports  by category field and specific date values
	public double  getReportByPubCategoryAndDate(Date fromDate , Date untilDate)throws ParseException;
	public double getReportByAnimalCategoryAndDate(Date fromDate , Date untilDate )throws ParseException;
	public double getReportByGroceriesCategoryAndDate(Date fromDate , Date untilDate)throws ParseException;
	public double getReportByResturantCategoryAndDate(Date fromDate , Date untilDate )throws ParseException;
	public double getReportByHouseholdCategoryAndDate(Date fromDate , Date untilDate)throws ParseException;
	public double getReportByElectronicsCategoryAndDate(Date fromDate , Date untilDate)throws ParseException;
	public double getReportByPharmaceuticalCategoryAndDate(Date fromDate , Date untilDate )throws ParseException;
	public double getReportByOtherCategoryAndDate(Date fromDate , Date untilDate)throws ParseException;

	//method to search reports of the last months (last 12 months of current year)
	public double  getReportByJan()throws ParseException;
	public double  getReportByFab()throws ParseException;
	public double  getReportByMar()throws ParseException;
	public double  getReportByApr()throws ParseException;
	public double  getReportByMay()throws ParseException;
	public double  getReportByJun()throws ParseException;
	public double  getReportByjuly()throws ParseException;
	public double  getReportByAug()throws ParseException;
	public double  getReportBySep()throws ParseException;
	public double  getReportByOct()throws ParseException;
	public double  getReportByNov()throws ParseException;
	public double  getReportByDec()throws ParseException;

	//method to search reports of the last years (last 5 years)
	public double  getReportByYear1()throws ParseException;
	public double  getReportByYear2()throws ParseException;
	public double  getReportByYear3()throws ParseException;
	public double  getReportByYear4()throws ParseException;
	public double  getReportByYear5()throws ParseException;

	
}
