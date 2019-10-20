
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BookLineChart extends JPanel {
	String titleName = null;
	String[] type = new String[12];
	String[] series = new String[5];
    public BookLineChart() {
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 430));
        setSize(new Dimension(610,440));
        add(chartPanel);
        setVisible(true);
    }
    private CategoryDataset createDataset() {
    	
        // row keys...
    	series[0] = "책1";
    	series[1] = "책2";
//        final String series1 = "책1";
//        final String series2 = "book2";
//        final String series3 = "book3";
//        final String series4 = "book4";
//        final String series5 = "book5";
        
        // column keys...
        for(int i=0;i<12;i++) {
        	type[i] = (i+1)+"월";
        	System.out.println(type[i]);
        }
        
        final String type1 = "1월";
        final String type2 = "2월";
        final String type3 = "3월";
        final String type4 = "4월";
        final String type5 = "5월";
        final String type6 = "6월";
        final String type7 = "7월";
        final String type8 = "8월";
        final String type9 = "9월";
        final String type10 = "10월";
        final String type11 = "11월";
        final String type12 = "12월";

        // create the dataset..r.
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(30.0, series[0], type1);
        dataset.addValue(4.0, series[0], type2);
        dataset.addValue(3.0, series[0], type3);
        dataset.addValue(5.0, series[0], type4);
        dataset.addValue(5.0, series[0], type5);
        dataset.addValue(7.0, series[0], type6);
        dataset.addValue(7.0, series[0], type7);
        dataset.addValue(8.0, series[0], type8);
        dataset.addValue(8.0, series[0], type9);
        dataset.addValue(8.0, series[0], type10);
        dataset.addValue(8.0, series[0], type11);
        dataset.addValue(8.0, series[0], type12);
        
        for(int i=0;i<12;i++) {
        	dataset.addValue(5.0, series[1], type[i]);
        }

        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "상위5개 도서 월별 변화량",       // chart title
//            "월",                    // domain axis label
            "",
            "권",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("굴림",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("고딕", Font.PLAIN, 13));
        
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        //맨밑 가로 카테고리 (월) 칸
        plot.getDomainAxis().setLabelFont(new Font("굴림",Font.BOLD,15));
        //1~12월 칸
        plot.getDomainAxis().setTickLabelFont(new Font("굴림",Font.BOLD,10));
        
        //좌측 벨류값
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelAngle(20);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelFont(new Font("고딕", Font.BOLD, 18));
        rangeAxis.setAutoRangeIncludesZero(true);

        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDrawOutlines(true);

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;
    }

    public static void main(final String[] args) {
//
//        final BookLineChart demo = new BookLineChart("Line Chart Demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

}