/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.scratch.algorithms.nn;

/**
 *
 * @author elbosso
 */
public class JFreeChartRenderer
{
	static <T extends de.elbosso.algorithms.nn.FeatureRepresentation> void outNodes(java.lang.String basename, int steps, java.util.Collection<T> features)
	{
		de.elbosso.model.jfree.nn.NodesModel model=new de.elbosso.model.jfree.nn.NodesModel();
		for (de.elbosso.algorithms.nn.FeatureRepresentation feature : features)
		{
			double [] f= feature.getFeatures();
			model.addFeature(new java.awt.geom.Point2D.Double(f[0], f[1]));
		}
		javax.swing.JFrame f=new javax.swing.JFrame();
		org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createScatterPlot("Test", "Domain",
            "Range", model, org.jfree.chart.plot.PlotOrientation.VERTICAL, true, false, false);
org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart) {
                    @Override
                    public java.awt.Dimension getPreferredSize() {
                        return new java.awt.Dimension(640, 480);
                    }
                };
java.awt.Shape cross = org.jfree.util.ShapeUtilities.createDiagonalCross(3, 1);
        org.jfree.chart.plot.XYPlot xyPlot = (org.jfree.chart.plot.XYPlot) chart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesShape(0, cross);
        renderer.setSeriesPaint(0, java.awt.Color.red);
                f.add(chartPanel);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
	}
	static <T extends de.elbosso.algorithms.nn.Edge> void outEdges(java.lang.String basename, int steps, java.util.Collection<T> edges) 
	{
		de.elbosso.model.jfree.nn.EdgesModel model=new de.elbosso.model.jfree.nn.EdgesModel();
		for (de.elbosso.algorithms.nn.Edge edge : edges)
		{
			model.addEdge(edge);
		}
		javax.swing.JFrame f=new javax.swing.JFrame();
		org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createXYLineChart("Test", "Domain",
            "Range", model, org.jfree.chart.plot.PlotOrientation.VERTICAL, true, false, false);
org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart) {
                    @Override
                    public java.awt.Dimension getPreferredSize() {
                        return new java.awt.Dimension(640, 480);
                    }
                };
	
chart.removeLegend();
java.awt.Shape cross = org.jfree.util.ShapeUtilities.createDiagonalCross(3, 1);
        org.jfree.chart.plot.XYPlot xyPlot = (org.jfree.chart.plot.XYPlot) chart.getPlot();
for(int i=0;i<model.getSeriesCount();++i)
		xyPlot.getRenderer().setSeriesPaint(i, java.awt.Color.RED);
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesShape(0, cross);
        renderer.setSeriesPaint(0, java.awt.Color.red);
                f.add(chartPanel);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
	}
	
}
