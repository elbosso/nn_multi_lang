/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn.unsupervised.gneuralgas;

import java.util.List;

/**
 *
 * @author elbosso
 */
public class Network
{
	private java.util.List<Node> nodes;
	private java.util.Comparator<Node> comp=new DistComp();
	private java.util.List<Edge> edges;
	private double eb;
	private double en;
	private int lambda;
	private double alpha;
	private int t;
	private double alphamax;
	private double d;

	public Network(int dimensionOfFeatures)
	{
		super();
		this.nodes = new java.util.LinkedList();
		nodes.add(Generator.generate(dimensionOfFeatures));
		nodes.add(Generator.generate(dimensionOfFeatures));
		this.edges=new java.util.LinkedList();
	}
	public void init(double eb, double en, int lambda, double alpha, double alphamax, double d)
	{
		t=0;
		this.alpha=alpha;
		this.eb=eb;
		this.en=en;
		this.alphamax=alphamax;
		this.lambda=lambda;
		this.d=d;
	}
	public double getT()
	{
		return t;
	}
	
	public void learn(double[] sample)
	{
		double featureDistMin=java.lang.Double.MAX_VALUE;
		Node winner=null;
		Node secondbest=null;
		
		for (Node node : nodes)
		{
			double dist=node.computeSquaredFeatureDistance(sample);
			if(dist<featureDistMin)
			{
				winner=node;
				featureDistMin=dist;
			}
		}
		featureDistMin=java.lang.Double.MAX_VALUE;
		for (Node node : nodes)
		{
			if(node.getId()!=winner.getId())
			{
				double dist=node.computeSquaredFeatureDistance(winner.getFeatures());
				if(dist<featureDistMin)
				{
					secondbest=node;
					featureDistMin=dist;
				}
			}
		}
		Edge theEdge=null;
		for (Edge edge: edges)
		{
			if(edge.contains(winner))
			{
				edge.age();
				Node theOther=edge.getTheOther(winner);
				if(theOther.getId()==secondbest.getId())
					theEdge=edge;
				theOther.updateFeatures(en, sample);
			}
		}
		if(theEdge!=null)
			theEdge.rejuvenate();
		else
			edges.add(new Edge(winner,secondbest));
		winner.updateFeatures(eb, sample);
		winner.accumulateError();
		java.util.List<Edge> toberemoved=new java.util.LinkedList();
		for (Edge edge: edges)
		{
			if(edge.getAge()>alphamax)
				toberemoved.add(edge);
		}
//		if(toberemoved.isEmpty()==false)
//			System.out.println("at "+t+" removing "+toberemoved.size()+" edges!");
		edges.removeAll(toberemoved);
		java.util.Set<Node> toberetained=new java.util.HashSet();
		for (Edge edge: edges)
		{
			toberetained.add(edge.getA());
			toberetained.add(edge.getB());
		}
		nodes.retainAll(toberetained);
//		System.out.println(t+" "+timeq+" "+e+" "+lambda);
		t+=1;
		if(t%lambda==0)
		{
//			System.out.println();
			java.util.Collections.sort(nodes, comp);
			Node worst=nodes.get(0);
			Node almostWorst=nodes.get(1);
			double[] newFeat=new double[sample.length];
			for(int i=0;i<sample.length;++i)
			{
				newFeat[i]=worst.getFeatures()[i]+(almostWorst.getFeatures()[i]-worst.getFeatures()[i])/2.0;
			}
			Node newNode=new Node(newFeat);
			nodes.add(newNode);
			edges.add(new Edge(worst,newNode));
			edges.add(new Edge(almostWorst,newNode));
			toberemoved.clear();
			for (Edge edge: edges)
			{
				if((edge.contains(worst))&&(edge.contains(almostWorst)))
					toberemoved.add(edge);
			}
			edges.removeAll(toberemoved);
			worst.decreaseError(alpha);
			almostWorst.decreaseError(alpha);
		}
		for (Node node : nodes)
		{
			node.decreaseError(d);
		}
	}
	class DistComp implements java.util.Comparator<Node>
	{

		public int compare(Node o1, Node o2)
		{
			return Double.compare(o1.getError(), o2.getError())*-1;
		}

	}

	public List<Node> getNodes()
	{
		return nodes;
	}

	public List<Edge> getEdges()
	{
		return edges;
	}
	
}
