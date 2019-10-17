

import java.awt.Dimension;
import java.awt.Font;
import java.text.AttributedString;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class PieChart3D extends JPanel {
	public static ChartPanel chartPanel;
	
    public PieChart3D() {
        final PieDataset dataset = createSampleDataset();
        final JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
//        chartPanel.setOpaque(false);
        setSize(600,500);
        add(chartPanel);
        setVisible(true);
        

    }

    private PieDataset createSampleDataset() {
        
        final DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java : "+ new Integer(43), new Integer(43));
        result.setValue("Visual Basic", new Integer(10));
        result.setValue("C/C++", new Integer(17));
        result.setValue("PHP", new Integer(32));
        result.setValue("Perl", new Integer(1));
        
        return result;
        
    }
 
    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "3D ÆÄÀÌÂ÷Æ®",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(new CustomLabelGenerator());
        chart.getTitle().setFont(new Font("°íµñ", Font.BOLD, 15));
        // ¹ü·Ê
        chart.getLegend().setItemFont(new Font("°íµñ", Font.PLAIN, 10));
        return chart;
        
    }
 
    public static void main(final String[] args) {
    }
    

    static class CustomLabelGenerator implements PieSectionLabelGenerator {
        public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
            String result = null;    
            if (dataset != null) {
                if (!key.equals("PHP")) {
                    result = key.toString();   
                }else if (key.equals("PHP")) {
                	result = key.toString();
                }
            }
            return result;
        }
		@Override
		public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
			return null;
		}
   
    }

}