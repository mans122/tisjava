import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import java.awt.*;
 
/**
 * FileName : BarChartBean.java
 * Comment  : 
 * @version : 1.0
 * @author  : sunshiny
 * @date    : 2011. 1. 5.
 */
public class BarChartBean {
 
    public static void main(String arg[]){
//        BarChartBean bcb = new BarChartBean();
// 
//        JFreeChart chart = bcb.getBarChart();
//        ChartFrame frame1 = new ChartFrame("차트",chart);
//        frame1.setSize(700,250);  
//        frame1.setVisible(true);
//        frame1.setLocation(100, 200);
// 
 
    }
 
	public JFreeChart getBarChart() {
        String titleStr = "연간 도서 대출 현황";
 
        // row keys...
        final String series1 = "2017년";
        final String series2 = "2018년";
        final String series3 = "2019년";
 
        // column keys...
        final String category1 = "1월";
        final String category2 = "2월";
        final String category3 = "3월";
        final String category4 = "4월";
        final String category5 = "5월";
        final String category6 = "6월";
        final String category7 = "7월";
        final String category8 = "8월";
        final String category9 = "9월";
        final String category10 = "10월";
        final String category11 = "11월";
        final String category12 = "12월";
 
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
 
        dataset.addValue(1.0, series1, category1);
        dataset.addValue(5.0, series1, category2);
        dataset.addValue(4.0, series1, category3);
        dataset.addValue(27.0, series1, category4);
        dataset.addValue(30.0, series1, category5);
        dataset.addValue(10.0, series1, category6);
        dataset.addValue(11.0, series1, category7);
        dataset.addValue(7.0, series1, category8);
        dataset.addValue(2.0, series1, category9);
        dataset.addValue(16.0, series1, category10);
        dataset.addValue(19.0, series1, category11);
        dataset.addValue(22.0, series1, category12);
        
        dataset.addValue(12.0, series2, category1);
        dataset.addValue(12.0, series2, category2);
        dataset.addValue(12.0, series2, category3);
        
        dataset.addValue(12.0, series3, category4);
 
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
        
        plot.setDomainAxis(new CategoryAxis("월별"));
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