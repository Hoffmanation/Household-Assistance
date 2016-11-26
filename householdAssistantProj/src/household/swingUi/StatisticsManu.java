/*
 * 
 *
 * Copyright (c) 2016 - Oren Hoffman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * 
 * 
 */


package household.swingUi;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

import household.enumeration.Category;
import household.swingUi.MainManu.JPanelWithBackground;
import logic.Bean.Input;
import logic.Bean.ReportLogic;
import logic.Bean.StatisticsLogic;

public class StatisticsManu extends JFrame {
	/**
	 * 
	 */
	private DefaultPieDataset dataset;
	private DefaultCategoryDataset data;
	private static JFrame frame;
	private JLabel string;
	private static JPanel top;
	// buttons
	private JButton expenseBtn;
	private JButton searchDateBtn;
	private JButton searchYear2Btn;
	private JButton statBtn;
	private JButton backBtn;
	// date label
	private JLabel date = new JLabel("Date Filter: ");
	// year label
	private JLabel year = new JLabel("Last years/months: ");
	// from date label
	private JLabel fromD = new JLabel("From: ");
	// until date label
	private JLabel toD = new JLabel("To: ");
	// from date component
	private JXDatePicker fromDate = new JXDatePicker();
	// until date component
	private JXDatePicker untilDate = new JXDatePicker();
	// back image
	private ImageIcon img_back = new ImageIcon("pics\\back.png");
	private JLabel backImg = new JLabel(img_back);
	// search image
	private ImageIcon img_submit = new ImageIcon("pics\\searchBtn.png");
	private JLabel submitImg = new JLabel(img_submit);
	// search image
	private ImageIcon img_submit2 = new ImageIcon("pics\\searchBtn.png");
	private JLabel submit2Img = new JLabel(img_submit2);
	// person Option box
	private DefaultComboBoxModel yearInput = new DefaultComboBoxModel();
	private JComboBox yearOptionBox = new JComboBox(yearInput);

	// module objects
	private ReportLogic r = new ReportLogic();
	private StatisticsLogic sl = new StatisticsLogic();
	private MainManu m = new MainManu();

	// statistics value
	double Groceries = 0;
	double Supermarket = 0;
	double Restaurant = 0;
	double Pub = 0;
	double Household = 0;
	double Animals = 0;
	double Electronics = 0;
	double Pharmaceutical = 0;
	double Other = 0;

	public StatisticsManu() {
		try {
			prepareGUI();
		} catch (IOException e) {
			// do nothing
		}
	}

	// an init method will create all components
	public void prepareGUI() throws IOException {
		// main frame
		frame = new JFrame("Household Assistant");
		frame.setBounds(600, 150, 905, 650);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// main panel
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBackground(Color.blue);
		top.setSize(0, 0);

		// year label
		year.setBounds(470, 70, 200, 50);
		year.setFont(new Font("Tahoma", Font.BOLD, 15));
		year.setForeground(new Color(51, 51, 51));

		// nameOptionBox
		yearOptionBox.setBounds(470, 140, 120, 25);
		yearOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yearOptionBox.setForeground(new Color(51, 51, 51));
		yearInput.addElement("Last Years");
		yearInput.addElement("Last months");
		yearOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// date label
		date.setBounds(50, 70, 100, 50);
		date.setFont(new Font("Tahoma", Font.BOLD, 15));
		date.setForeground(new Color(51, 51, 51));

		// from Date label
		fromD.setBounds(50, 100, 100, 50);
		fromD.setFont(new Font("Tahoma", Font.BOLD, 15));
		fromD.setForeground(new Color(51, 51, 51));

		// fromDate
		fromDate.setDate(Calendar.getInstance().getTime());
		fromDate.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		fromDate.setBounds(50, 140, 120, 25);
		fromDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// until Date label
		toD.setBounds(180, 100, 100, 50);
		toD.setFont(new Font("Tahoma", Font.BOLD, 15));
		toD.setForeground(new Color(51, 51, 51));

		// untilDate
		untilDate.setDate(Calendar.getInstance().getTime());
		untilDate.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		untilDate.setBounds(180, 140, 120, 25);
		untilDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// back button
		backBtn = new JButton();
		backBtn.setBounds(750, 560, 100, 40);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setForeground(Color.gray);
		backBtn.setBackground(Color.gray);
		backBtn.setBorder(null);
		backBtn.setOpaque(true);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBorder(null);
		backBtn.setFocusPainted(false);
		backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// search Date button
		searchDateBtn = new JButton();
		searchDateBtn.setBounds(348, 115, 100, 50);
		searchDateBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchDateBtn.setForeground(Color.gray);
		searchDateBtn.setBackground(Color.gray);
		searchDateBtn.setBorder(null);
		searchDateBtn.setOpaque(true);
		searchDateBtn.setContentAreaFilled(false);
		searchDateBtn.setBorderPainted(false);
		searchDateBtn.setBorder(null);
		searchDateBtn.setFocusPainted(false);
		searchDateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// search year button
		searchYear2Btn = new JButton();
		searchYear2Btn.setBounds(768, 115, 100, 50);
		searchYear2Btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchYear2Btn.setForeground(Color.gray);
		searchYear2Btn.setBackground(Color.gray);
		searchYear2Btn.setBorder(null);
		searchYear2Btn.setOpaque(true);
		searchYear2Btn.setContentAreaFilled(false);
		searchYear2Btn.setBorderPainted(false);
		searchYear2Btn.setBorder(null);
		searchYear2Btn.setFocusPainted(false);
		searchYear2Btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// search button action
		searchDateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// getting values from database, inserting them to a dataset and
				// displaying the pie chart
				java.util.Date dateA = fromDate.getDate();
				java.util.Date dateB = untilDate.getDate();
				
				 java.sql.Date date1 = new java.sql.Date(dateA.getTime());
				 java.sql.Date date2 = new java.sql.Date(dateB.getTime());
				try {
					dataset.setValue(Category.Animals, sl.getReportByAnimalCategoryAndDate(date1, date2));
					dataset.setValue(Category.Electronics, sl.getReportByElectronicsCategoryAndDate(date1, date2));
					dataset.setValue(Category.Groceries, sl.getReportByGroceriesCategoryAndDate(date1, date2));
					dataset.setValue(Category.Household, sl.getReportByHouseholdCategoryAndDate(date1, date2));
					dataset.setValue(Category.Other, sl.getReportByOtherCategoryAndDate(date1, date2));
					dataset.setValue(Category.Pharmaceutical,sl.getReportByPharmaceuticalCategoryAndDate(date1, date2));
					dataset.setValue(Category.Pub, sl.getReportByPubCategoryAndDate(date1, date2));
					dataset.setValue(Category.Restaurant, sl.getReportByResturantCategoryAndDate(date1, date2));
					dataset.setValue(Category.Supermarket, Supermarket);

				} catch (ParseException e1) {
					// do nothing
				}

			}
		});
		
		// searchYear button action - search by year
		searchYear2Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			data.clear();
				
				// getting values from database, inserting them to a dataset and
				// displaying the bar chart
				String yearsSelect = (String) yearOptionBox.getSelectedItem();
				if (yearsSelect == "Last months") {
					System.out.println("in");
					try {

						data.setValue(sl.getReportByJan(), "b", "Jan");
						data.setValue(sl.getReportByFab(), "b", "Fab");
						data.setValue(sl.getReportByMar(), "b", "Mar");
						data.setValue(sl.getReportByApr(), "b", "Apr");
						data.setValue(sl.getReportByMay(), "b", "May");
						data.setValue(sl.getReportByJun(), "b", "Jun");
						data.setValue(sl.getReportByjuly(), "b", "Jul");
						data.setValue(sl.getReportByAug(), "b", "Aug");
						data.setValue(sl.getReportBySep(), "b", "Sep");
						data.setValue(sl.getReportByOct(), "b", "Oct");
						data.setValue(sl.getReportByNov(), "b", "Nov");
						data.setValue(sl.getReportByDec(), "b", "Dec");

					} catch (Exception e2) {
						// do nothing
					}

				} else if (yearsSelect == "Last Years"){
					try {
						data.setValue(sl.getReportByYear1(), "b", "This year");
						data.setValue(sl.getReportByYear2(), "b", "Last year");
						data.setValue(sl.getReportByYear3(), "b", "2 years ago");
						data.setValue(sl.getReportByYear4(), "b", "3 years ago");
						data.setValue(sl.getReportByYear5(), "b", "4 years ago");
					} catch (ParseException e1) {
						// do nothing
					}
				}

			}
		});
		
		// initializing the pie chart in the frame
		dataset = new DefaultPieDataset();
		JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
		ChartPanel chartPanel1 = new ChartPanel(chart);
		chartPanel1.setPreferredSize(new Dimension(390, 300));
		JScrollPane scrollPane2 = new JScrollPane(chartPanel1);
		scrollPane2.setBounds(50, 170, 400, 350);
		frame.add(scrollPane2, BorderLayout.CENTER);

		// initializing the bar chart in the frame
		data = new DefaultCategoryDataset();
		JFreeChart chart_a = ChartFactory.createBarChart("", "", "", data, PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot plot = chart_a.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		ChartPanel chartPanel = new ChartPanel(chart_a);
		chartPanel.setPreferredSize(new Dimension(390, 300));
		JScrollPane scrollPane = new JScrollPane(chartPanel);
		scrollPane.setBounds(470, 170, 400, 350);

		//changing the bar colors of the bar chart
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		Color color = new Color(39, 127, 201);
		renderer.setSeriesPaint(0, color);
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		frame.add(scrollPane, BorderLayout.CENTER);

		// adding components to the frame
		searchDateBtn.add(submitImg);
		searchYear2Btn.add(submit2Img);
		backBtn.add(backImg);
		frame.add(searchDateBtn);
		frame.add(searchYear2Btn);
		frame.add(date);
		frame.add(yearOptionBox);
		frame.add(year);
		frame.add(untilDate);
		frame.add(backBtn);
		frame.add(fromDate);
		frame.add(fromD);
		frame.add(toD);
		frame.getContentPane().add(new JPanelWithBackground("pics\\statisticsbg.png"));
		frame.setVisible(true);
		top.setVisible(true);

		
		// back button action
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					m.prepareGUI();
					frame.setVisible(false);
					frame.dispose();
				} catch (IOException e1) {
					// do nothing

				}

			}
		});

	}

	// drawing the background image to the frame
	public class JPanelWithBackground extends JPanel {

		private Image backgroundImage;

		// Here, we use the constructor to load the image. This
		// can vary depending on the use case of the panel and the layout type
		// of the frame
		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File(fileName));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw the background image.
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}
}
