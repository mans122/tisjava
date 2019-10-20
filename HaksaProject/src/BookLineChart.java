
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
        final String series1 = "Ã¥1";
        final String series2 = "book2";
        final String series3 = "book3";
//        final String series4 = "book4";
//        final String series5 = "book5";
        
        // column keys...
        for(int i=0;i<12;i++) {
        	type[i] = (i+1)+"¿ù";
        	System.out.println(type[i]);
        }
        
        final String type1 = "1¿ù";
        final String type2 = "2¿ù";
        final String type3 = "3¿ù";
        final String type4 = "4¿ù";
        final String type5 = "5¿ù";
        final String type6 = "6¿ù";
        final String type7 = "7¿ù";
        final String type8 = "8¿ù";
        final String type9 = "9¿ù";
        final String type10 = "10¿ù";
        final String type11 = "11¿ù";
        final String type12 = "12¿ù";

        // create the dataset..r.
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(30.0, series1, type1);
        dataset.addValue(4.0, series1, type2);
        dataset.addValue(3.0, series1, type3);
        dataset.addValue(5.0, series1, type4);
        dataset.addValue(5.0, series1, type5);
        dataset.addValue(7.0, series1, type6);
        dataset.addValue(7.0, series1, type7);
        dataset.addValue(8.0, series1, type8);
        dataset.addValue(8.0, series1, type9);
        dataset.addValue(8.0, series1, type10);
        dataset.addValue(8.0, series1, type11);
        dataset.addValue(8.0, series1, type12);
        
        for(int i=0;i<12;i++) {
        	dataset.addValue(5.0, series2, type[i]);
        }

        dataset.addValue(4.0, series3, type1);
        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "»óÀ§5°³ µµ¼­ ¿ùº° º¯È­·®",       // chart title
            "¿ù",                    // domain axis label
            "±Ç",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
//        chart.getPlot().labet
//        chart.getTitle().setFont(new Font("°íµñ", Font.BOLD, 15));
////		plot.setLabelFont(new Font("°íµñ", Font.PLAIN, 10));
//		chart.getLegend().setItemFont(new Font("°íµñ", Font.PLAIN, 10));
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("±¼¸²",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("°íµñ", Font.PLAIN, 13));
        
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        //¸Ç¹Ø °¡·Î Ä«Å×°í¸® (¿ù) Ä­
        plot.getDomainAxis().setLabelFont(new Font("±¼¸²",Font.BOLD,15));
        //1~12¿ù Ä­
        plot.getDomainAxis().setTickLabelFont(new Font("±¼¸²",Font.BOLD,10));
        
        //ÁÂÃø º§·ù°ª
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelAngle(20);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelFont(new Font("°íµñ", Font.BOLD, 18));
        rangeAxis.setAutoRangeIncludesZero(true);

        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

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