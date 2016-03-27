/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn.unsupervised.gneuralgas;

/**
 *
 * @author elbosso
 */
public class Node extends de.elbosso.algorithms.nn.FeatureRepresentation
{
	private double squaredFeaturesDistance;
	private double error;
	
	public Node(double[] features)
	{
		super(features);
	}
	protected double computeSquaredFeatureDistance(double[] sample)
	{
		squaredFeaturesDistance=super.computeSquaredFeatureDistance(sample);
//		error+=squaredFeaturesDistance;
		return squaredFeaturesDistance;
	}	

	public double getSquaredFeaturesDistance()
	{
		return squaredFeaturesDistance;
	}
	void accumulateError()
	{
		error+=squaredFeaturesDistance;
	}
	public double getError()
	{
		return error;
	}
	void decreaseError(double factor)
	{
		error*=factor;
	}
	public void updateFeatures(double e, double[] sample)
	{
		{
//				double old=computeSquaredFeatureDistance(sample);
			for (int i=0;i<sample.length;++i)
			{
				features[i]+=e*(sample[i]-features[i]);
			}
//				System.out.println("h "+h+" "+(e*h)+" "+old+" "+computeSquaredFeatureDistance(sample));
		}
	}
}
