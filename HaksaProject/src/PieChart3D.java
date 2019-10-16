

import java.awt.Dimension;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;


public class PieChart3D extends JPanel {

    public PieChart3D() {
        final PieDataset dataset = createSampleDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setPreferredSize(new Dimension(500, 300));
//        setTitle("마마마마마");
//        setContentPane(chartPanel);

    }

    private PieDataset createSampleDataset() {
        
        final DefaultPieDataset result = new DefaultPieDataset();
//        result.setValue("Java", new Double(43.2));
//        result.setValue("Visual Basic", new Double(10.0));
//        result.setValue("C/C++", new Double(17.5));
//        result.setValue("PHP", new Double(32.5));
//        result.setValue("Perl", new Double(1.0));
        result.setValue("Java : "+ new Integer(43), new Integer(43));
        result.setValue("Visual Basic", new Integer(10));
        result.setValue("C/C++", new Integer(17));
        result.setValue("PHP", new Integer(32));
        result.setValue("Perl", new Integer(1));
        return result;
        
    }
 
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "3D 파이차트",  // chart title
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
        return chart;
        
    }
 
    public static void main(final String[] args) {

        final PieChart3D demo = new PieChart3D();
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

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
			// TODO Auto-generated method stub
			return null;
		}
   
    }

}