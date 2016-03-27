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
public class Generator extends de.elbosso.algorithms.nn.Generator
{
	
	public static Node[] generate(int nodeCount, int dimensionOfFeatures)
	{
		Node[] nodes=new Node[nodeCount];
		for(int j=0;j<nodeCount;++j)
		{
			double[] features=new double[dimensionOfFeatures];
			for (int i=0;i<features.length;++i)
			{
				features[i]=rand.nextDouble();
			}
			nodes[j]=new Node(features);
		}
		return nodes;
	}
}
