/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn.unsupervised.neuralgas;

/**
 *
 * @author elbosso
 */
public class Node extends de.elbosso.algorithms.nn.FeatureRepresentation
{
	private double squaredFeaturesDistance;
	
	public Node(double[] features)
	{
		super(features);
	}
	protected double computeSquaredFeatureDistance(double[] sample)
	{
		squaredFeaturesDistance=super.computeSquaredFeatureDistance(sample);
		return squaredFeaturesDistance;
	}	

	public double getSquaredFeaturesDistance()
	{
		return squaredFeaturesDistance;
	}
	void updateFeatures(double e, double lambda,double[] sample,double k)
	{
		{
//				double old=computeSquaredFeatureDistance(sample);
				double h=java.lang.Math.exp(-k/lambda);
			for (int i=0;i<sample.length;++i)
			{
				features[i]+=e*h*(sample[i]-features[i]);
			}
//				System.out.println("h "+h+" "+(e*h)+" "+old+" "+computeSquaredFeatureDistance(sample));
		}
	}

}
