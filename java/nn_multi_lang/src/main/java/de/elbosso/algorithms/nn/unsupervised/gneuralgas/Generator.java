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
public class Generator extends de.elbosso.algorithms.nn.Generator
{
	
	public static Node generate(int dimensionOfFeatures)
	{
			double[] features=new double[dimensionOfFeatures];
			for (int i=0;i<features.length;++i)
			{
				features[i]=rand.nextDouble();
			}
			return new Node(features);
	}
	public static void generateSample(double[] features)
	{
		de.elbosso.algorithms.nn.Generator.generateSample(features);
		if(features[0]>0.75)
		{
			double angle=rand.nextDouble()*2*java.lang.Math.PI;
			features[0]+=0.25+1.0+java.lang.Math.cos(angle);
			features[1]=java.lang.Math.sin(angle);
			if(features.length>2)
				features[2]=0.5;
		}
		else if(features[0]>0.5)
		{
			features[0]=(features[0]-0.5)*2+features[0];
			features[1]=0.5+0.1*rand.nextDouble();
			if(features.length>2)
				features[2]=0.5;
		}
	}
}
