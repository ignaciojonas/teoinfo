import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


public class PlotWindow extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlotWindow(Engine e) {
		super("Plot");
		XYSeriesCollection dataset = new XYSeriesCollection();
		double[] aux = e.history.get(0);
		for (int i = 0; i < aux.length; i++) {
			XYSeries series = new XYSeries("Simbol " + i);
			dataset.addSeries(series);
		}
		
		for (int i = 0; i < e.history.size(); i++) {
			aux = e.history.get(i);
			for (int j = 0; j < aux.length; j++) {
				dataset.getSeries(j).add(i+1,aux[j]);
			}	
		}
		
		 
		final JFreeChart chart = ChartFactory.createXYLineChart(
	            "V*",          // chart title
	            "Steps",               // domain axis label
	            "Probabilities",                  // range axis label
	            dataset,                  // data
	            PlotOrientation.VERTICAL,
	            true,                     // include legend
	            true,
	            false
	        );

	        final XYPlot plot = chart.getXYPlot();
	        final NumberAxis domainAxis = new LogarithmicAxis("Steps");
	        final NumberAxis rangeAxis = new NumberAxis("Prob");
	        plot.setDomainAxis(domainAxis);
	        plot.setRangeAxis(rangeAxis);
	        chart.setBackgroundPaint(Color.white);
	        plot.setOutlinePaint(Color.black);
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        setContentPane(chartPanel);
	}

}
