/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.scratch.algorithms.nn;

/**
 *
 * @author elbosso
 */
public class MainClass
{
	public static void main(String[] args) throws java.io.IOException,javax.xml.transform.TransformerException,javax.xml.parsers.ParserConfigurationException
	{
		long start=System.nanoTime();
		long now=start;
		long counter=0;
		while(now-start==0)
		{
			now=System.nanoTime();
			++counter;
		}
		System.out.println(counter+" "+(now-start));
//		mainKohonen(args);
		mainNeuralGas(args);
//		mainGrowingNeuralGas(args);
	}
	public static void mainGrowingNeuralGas(String[] args) throws java.io.IOException
	{
		de.elbosso.algorithms.nn.unsupervised.gneuralgas.Network network=new de.elbosso.algorithms.nn.unsupervised.gneuralgas.Network(3);
		network.init(0.2, 0.006,1450,0.5,50,0.995);
		int i=0;
		int plotinterval=20000;
		for(;i<100000;++i)
		{
			double[] sample=de.elbosso.algorithms.nn.unsupervised.gneuralgas.Generator.generateSample(3);
			network.learn(sample);
//			System.out.println(i);
			if((i+1)%plotinterval==0)
				GnuplotRenderer.outEdges("gneuralgas",i+1,network.getEdges());
		}
		JFreeChartRenderer.outEdges("gneuralgas",i,network.getEdges());
	}
	public static void mainNeuralGas(String[] args) throws java.io.IOException,javax.xml.transform.TransformerException,javax.xml.parsers.ParserConfigurationException
	{
		de.elbosso.algorithms.nn.unsupervised.neuralgas.Node[] nodes=de.elbosso.algorithms.nn.unsupervised.neuralgas.Generator.generate(25,2);
		de.elbosso.algorithms.nn.unsupervised.neuralgas.Network network=new de.elbosso.algorithms.nn.unsupervised.neuralgas.Network(nodes);
		network.init(10000,1.0,0.001,13,0.01);
		int i=0;
		int plotinterval=2000;
		for(;i<10000;++i)
		{
			double[] sample=de.elbosso.algorithms.nn.unsupervised.neuralgas.Generator.generateSample(2);
			network.learn(sample);
			if((i+1)%plotinterval==0)
				SVGRenderer.outNodes("neuralgas",i+1,java.util.Arrays.asList(network.getNodes()));
		}
		JFreeChartRenderer.outNodes("neuralgas",i+1,java.util.Arrays.asList(network.getNodes()));
	}
	public static void mainKohonen(String[] args) throws java.io.IOException,javax.xml.transform.TransformerException,javax.xml.parsers.ParserConfigurationException
	{
		int[] nodesperdimension={3,3};
		de.elbosso.algorithms.nn.unsupervised.kohonen.Node[] nodes=de.elbosso.algorithms.nn.unsupervised.kohonen.Generator.generate(nodesperdimension,2);
		de.elbosso.algorithms.nn.unsupervised.kohonen.Network network=new de.elbosso.algorithms.nn.unsupervised.kohonen.Network(nodes);
		network.init(10000,0.8,0.03,5.1,1.2);
		int i=0;
		int plotinterval=2000;
		for(;i<10000;++i)
		{
			double[] sample=de.elbosso.algorithms.nn.unsupervised.kohonen.Generator.generateSample(2);
			network.learn(sample);
			if((i+1)%plotinterval==0)
				SVGRenderer.outEdges("kohonen",i+1,network.getEdges());
		}
		JFreeChartRenderer.outEdges("kohonen",i,network.getEdges());
	}
}
