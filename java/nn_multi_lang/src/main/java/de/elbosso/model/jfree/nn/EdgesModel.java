/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.model.jfree.nn;

/**
 *
 * @author elbosso
 */
public class EdgesModel extends org.jfree.data.general.AbstractSeriesDataset implements org.jfree.data.xy.XYDataset
{
	private java.util.LinkedList<de.elbosso.algorithms.nn.Edge> edgelist=new java.util.LinkedList();
	private int xoffset=0;

	public void addEdge(de.elbosso.algorithms.nn.Edge newedge)
	{
		edgelist.addLast(newedge);
		this.notifyListeners(new org.jfree.data.general.DatasetChangeEvent(this,this));
	}
    public double getXValue(int series, int item) {
		de.elbosso.algorithms.nn.FeatureRepresentation fr=((item==0)?edgelist.get(series).getA():edgelist.get(series).getB());
        return fr.getFeatures()[0];
    }
    public Number getX(int series, int item) {
        return new Double(getXValue(series,item));
    }
    public double getYValue(int series, int item) {
		de.elbosso.algorithms.nn.FeatureRepresentation fr=(item==0?edgelist.get(series).getA():edgelist.get(series).getB());
        return fr.getFeatures()[1];
    }
    public Number getY(int series, int item) {
        return new Double(getYValue(series,item));
    }
    public int getSeriesCount() {
        return edgelist.size();
    }
    public String getSeriesName(int series) {
        return "Schaun mer mal "+series;
    }
    public String getSeriesKey(int series) {
        return getSeriesName(series);
    }
    public org.jfree.data.DomainOrder getDomainOrder() {
		return org.jfree.data.DomainOrder.NONE;
    }
    public int getItemCount(int series) {
        return 2;
    }

	public void clear()
	{
		edgelist.clear();
		this.notifyListeners(new org.jfree.data.general.DatasetChangeEvent(this,this));
	}

}