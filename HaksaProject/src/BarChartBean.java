import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import java.awt.*;
import java.sql.ResultSet;

public class BarChartBean {
	static String[] month = new String[13];
	static Integer[] monthCount =new Integer[13];
	static String[] month2 = new String[13];
	static Integer[] monthCount2 =new Integer[13];
	static ResultSet rs = null;
	static String query;
	public static void main(String arg[]){
	}

	public JFreeChart getBarChart() {
		for(int i=0;i<13;i++) {
//			month[i] = null;
//			month2[i] = null;
//			monthCount[i]=0;
//			monthCount2[i]=0;
		}
		try {
			//			query = "select s.id,s.name,s.dept, bk.title, b.bookno,b.year, b.month from student s,"
			//				    +" (select id,bookno,substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from bookrent2 br) b ,books2 bk"
			//				    +" where s.id = b.id and b.bookno = bk.no"; 
			//			년,월별 총합
			query = "select year,month, count(*) count from (select s.id,s.name,s.dept, bk.title, b.bookno,b.year, b.month from student s,"
					+" (select id,bookno,substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from bookrent2 br) b ,books2 bk"
					+" where s.id = b.id and b.bookno = bk.no) b where year='2018' group by year,month order by year,month";
			rs = DBManager.stmt.executeQuery(query);
			while(rs.next())
			{
				month[rs.getInt("month")] = rs.getString("month");
//				System.out.println(rs.getInt("month"));
//				System.out.println(month[rs.getInt("month")]);
				monthCount[rs.getInt("month")] = rs.getInt("count");
			}

			query = "select year,month, count(*) count from (select s.id,s.name,s.dept, bk.title, b.bookno,b.year, b.month from student s,"
					+" (select id,bookno,substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from bookrent2 br) b ,books2 bk"
					+" where s.id = b.id and b.bookno = bk.no) b where year='2019' group by year,month order by year,month";
			rs = DBManager.stmt.executeQuery(query);
			while(rs.next())
			{
				month2[rs.getInt("month")] = rs.getString("month");
//				System.out.println(rs.getInt("month"));
//				System.out.println(month2[rs.getInt("month")]);
				monthCount2[rs.getInt("month")] = rs.getInt("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		String titleStr = "연간 도서 대출 현황";

		// row keys...
		final String series1 = "2018년";
		final String series2 = "2019년";

		// column keys...
		String[] category = new String[12];

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

//dataset =>값 넣기
		for(int i=0;i<12;i++) {
			category[i] = (i+1)+"월";
			dataset.addValue(monthCount[i+1], series1, category[i]);
			dataset.addValue(monthCount2[i+1], series2, category[i]);
		}

		JFreeChart chart = ChartFactory.createBarChart(titleStr, "", ""
				, dataset, PlotOrientation.VERTICAL, true,true, false);

		/**
		 * Chart 폰트 설정, 클래스 파일 위치 : package org.jfree.chart.StandardChartTheme.java
		 *      
		 */
		chart.getTitle().setFont(new Font("sansserif", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("sansserif", Font.BOLD, 15));
		chart.getCategoryPlot().setNoDataMessageFont(new Font("sansserif", Font.BOLD, 15));
		chart.getPlot().setNoDataMessageFont(new Font("sansserif", Font.BOLD, 15));

		System.out.println(chart.getPlot().getNoDataMessageFont().getName());

		chart.setBackgroundPaint(Color.WHITE);
		chart.getTitle().setPaint(Color.orange);

		StandardCategoryItemLabelGenerator stdCateGen 
		= new StandardCategoryItemLabelGenerator();
		BarRenderer barRender = new BarRenderer();

		CategoryPlot plot = chart.getCategoryPlot();

		barRender.setItemLabelGenerator(stdCateGen); // 그래프별 값 출력
		plot.setRenderer(barRender);

//		plot.setDomainAxis(new CategoryAxis("월별"));
		plot.setRangeAxis(new NumberAxis("권수"));
		plot.getRangeAxis().setLabelAngle(1.6);
		plot.setOrientation(PlotOrientation.VERTICAL);


		//      plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true); // 가로 그리드 라인 보이기

		plot.setRangeGridlinePaint(Color.GRAY); 
		plot.setBackgroundPaint(Color.WHITE);
		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setUpperMargin(0.40);
		plot.getDomainAxis().setTickLabelFont(new Font("굴림",Font.BOLD,10));

		// disable bar outlines...
		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		renderer.setSeriesItemLabelsVisible(1, Boolean.TRUE);
		renderer.setSeriesItemLabelsVisible(2, Boolean.TRUE);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		// x축 문자열 회전(STANDARD, UP_45, UP_90,DOWN_45,DOWN_90)
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD); 
		domainAxis.setLowerMargin(0.01d);
		domainAxis.setUpperMargin(0.01d);
		domainAxis.setCategoryMargin(0.30);

		return chart;
	}
}