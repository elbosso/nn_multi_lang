/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn;

/**
 *
 * @author elbosso
 */
public class FeatureRepresentation
{
	private static long runningNumber;
	private final long id=++runningNumber;
	protected final double[] features;

	public FeatureRepresentation(double[] features)
	{
		super();
		this.features = features;
	}

	public long getId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		java.lang.StringBuffer sb=new java.lang.StringBuffer();
		for (double feature : features)
		{
			sb.append(feature);
			sb.append("\t");
		}
		sb.append(id);
		sb.append("\t");
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

	public double[] getFeatures()
	{
		return features;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final FeatureRepresentation other = (FeatureRepresentation) obj;
		if (this.id != other.id)
		{
			return false;
		}
		return true;
	}
}
