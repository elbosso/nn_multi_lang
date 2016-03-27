/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn.unsupervised.kohonen;

/**
 *
 * @author elbosso
 */
public class Node extends de.elbosso.algorithms.nn.FeatureRepresentation
{
	private int[] locations;
	private double distanceFromWinner;

	public Node(double[] features, int[] locations)
	{
		super(features);
		this.locations = locations;
	}

	@Override
	public String toString()
	{
		java.lang.StringBuffer sb=new java.lang.StringBuffer();
		for (int location : locations)
		{
			sb.append(location);
			sb.append("\t");
		}
//		sb.append(":");
		sb.append(super.toString());
		return sb.toString();
	}
	protected double computeSquaredFeatureDistance(double[] sample)
	{
		double result=0;
		for (int i=0;i<sample.length;++i)
		{
			double fac=sample[i]-features[i];
			result+=fac*fac;
		}
		return result;
	}

	public int[] getLocations()
	{
		return locations;
	}
	
	void updateDistanceSquaredFromWinner(Node winner)
	{
		double result=0;
		for (int i=0;i<getLocations().length;++i)
		{
			double fac=winner.getLocations()[i]-locations[i];
			result+=fac*fac;
		}
		distanceFromWinner=result;
	}

	public double getDistanceSquaredFromWinner()
	{
		return distanceFromWinner;
	}
	boolean updateFeatures(double e, double deltasquared,double[] sample)
	{
		boolean rv=getDistanceSquaredFromWinner()<deltasquared;
		if(rv==true)
		{
//				double old=computeSquaredFeatureDistance(sample);
				double h=java.lang.Math.exp(-getDistanceSquaredFromWinner()/(2*deltasquared));
			for (int i=0;i<sample.length;++i)
			{
				features[i]+=e*h*(sample[i]-features[i]);
			}
//				System.out.println("h "+h+" "+(e*h)+" "+old+" "+computeSquaredFeatureDistance(sample));
		}
		return rv;
	}
}
