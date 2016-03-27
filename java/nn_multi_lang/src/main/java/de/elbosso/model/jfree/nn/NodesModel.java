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
public class NodesModel extends org.jfree.data.general.AbstractSeriesDataset implements org.jfree.data.xy.XYDataset
{
	private java.util.LinkedList<java.awt.geom.Point2D> pointlist=new java.util.LinkedList();
	private int xoffset=0;

	public void addFeature(java.awt.geom.Point2D newpoint)
	{
		pointlist.addLast(newpoint);
		this.notifyListeners(new org.jfree.data.general.DatasetChangeEvent(this,this));
	}
    public double getXValue(int series, int item) {
        return pointlist.get(item).getX();
    }
    public Number getX(int series, int item) {
        return new Double(getXValue(series,item));
    }
    public double getYValue(int series, int item) {
        return pointlist.get(item).getY();
    }
    public Number getY(int series, int item) {
        return new Double(getYValue(series,item));
    }
    public int getSeriesCount() {
        return 1;
    }
    public String getSeriesName(int series) {
        return "Schaun mer mal";
    }
    public String getSeriesKey(int series) {
        return getSeriesName(series);
    }
    public org.jfree.data.DomainOrder getDomainOrder() {
		return org.jfree.data.DomainOrder.NONE;
    }
    public int getItemCount(int series) {
        return pointlist.size();
    }

	public void clear()
	{
		pointlist.clear();
		this.notifyListeners(new org.jfree.data.general.DatasetChangeEvent(this,this));
	}

}
