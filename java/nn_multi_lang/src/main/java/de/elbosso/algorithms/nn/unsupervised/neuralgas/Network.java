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
public class Network
{
	private Node[] nodes;
	private java.util.Comparator<Node> comp=new DistComp();
	private double tmax;
	private double estart;
	private double eend;
	private double lambdastart;
	private double lambdaend;
	private double t;

	public Network(Node[] nodes)
	{
		super();
		this.nodes = nodes;
	}
	public void init(double tmax, double estart, double eend, double lambdastart, double lambdaend)
	{
		t=0;
		this.tmax=tmax;
		this.estart=estart;
		this.eend=eend;
		this.lambdastart=lambdastart;
		this.lambdaend=lambdaend;
	}
	public double getT()
	{
		return t;
	}
	
	public void learn(double[] sample)
	{
		double featureDistMin=java.lang.Double.MAX_VALUE;
		Node winner=null;
		
		for (Node node : nodes)
		{
			double dist=node.computeSquaredFeatureDistance(sample);
			if(dist<featureDistMin)
			{
				winner=node;
				featureDistMin=dist;
			}
		}
		java.util.Arrays.sort(nodes, comp);
		double timeq=t/tmax;
		double e=estart*(java.lang.Math.pow(eend/estart, timeq));
		double lambda=lambdastart*(java.lang.Math.pow(lambdaend/lambdastart, timeq));
//		System.out.println(t+" "+timeq+" "+e+" "+lambda);
		double i=0;
		for (Node node : nodes)
		{
			node.updateFeatures(e, lambda,sample,i);
			++i;
		}
		t+=1;
	}
	class DistComp implements java.util.Comparator<Node>
	{

		public int compare(Node o1, Node o2)
		{
			return Double.compare(o1.getSquaredFeaturesDistance(), o2.getSquaredFeaturesDistance());
		}

	}

	public Node[] getNodes()
	{
		return nodes;
	}
	
}
