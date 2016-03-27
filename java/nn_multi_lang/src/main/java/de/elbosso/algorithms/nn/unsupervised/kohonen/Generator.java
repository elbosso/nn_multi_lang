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
public class Generator extends de.elbosso.algorithms.nn.Generator
{
	
	public static Node[] generate(int[] nodesperdimension, int dimensionOfFeatures)
	{
		int total=1;
		for (int i : nodesperdimension)
		{
			total*=i;
		}
		Node[] nodes=new Node[total];
		int[] counters=new int[nodesperdimension.length];
		for(int i=0;i<counters.length;++i)
		{
			counters[i]=0;
		}
		int loop=0;
		while(true)
		{
			int[] locations=java.util.Arrays.copyOf(counters, counters.length);
			double[] features=new double[dimensionOfFeatures];
			for (int i=0;i<features.length;++i)
			{
				features[i]=rand.nextDouble();
			}
			nodes[loop]=new Node(features,locations);
			++loop;
			counters[0]++;
			for(int i=0;i<counters.length;++i)
			{
				if(counters[i]==nodesperdimension[i])
				{
					if(i+1<nodesperdimension.length)
					{
						counters[i+1]++;
						counters[i]=0;
					}
				}
				else
					break;
			}
			if(loop==nodes.length)
				break;
		}
		return nodes;
	}
	public static void generateSample(double[] features)
	{
		de.elbosso.algorithms.nn.Generator.generateSample(features);
		if(features[1]>features[0])
		{
			features[1]*=features[0];//=1-features[1];
		}
	}
}
