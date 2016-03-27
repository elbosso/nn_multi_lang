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
public class Generator
{
	protected static final java.util.Random rand=new java.security.SecureRandom();

	public static double[] generateSample(int dimensionOfFeatures)
	{
		double[] features=new double[dimensionOfFeatures];
		generateSample(features);
		return features;
	}
	public static void generateSample(double[] features)
	{
		for (int i=0;i<features.length;++i)
		{
			features[i]=rand.nextDouble();
		}
	}
	
}
