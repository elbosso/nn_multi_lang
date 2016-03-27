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
public class Network
{
	private Node[] nodes;
	private java.util.Comparator<Node> comp=new DistComp();
	private double tmax;
	private double estart;
	private double eend;
	private double deltastart;
	private double deltaend;
	private double t;
	private java.util.List<de.elbosso.algorithms.nn.Edge> edges;

	public Network(Node[] nodes)
	{
		super();
		this.nodes = nodes;
	}
	public void init(double tmax, double estart, double eend, double deltastart, double deltaend)
	{
		t=0;
		this.tmax=tmax;
		this.estart=estart;
		this.eend=eend;
		this.deltastart=deltastart;
		this.deltaend=deltaend;
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
//		for(int i=0;i<minsearchers.length;++i)
//		{
//			minsearchers[i].init(sample);
//		}
//		minimumch.put(java.util.Arrays.asList(nodes));
//		while(minimumch.isEmpty()==false)
//		{
////			System.out.print(".");
//			java.lang.Thread.currentThread().yield();
//		}
//		featureDistMin=minsearchers[0].getMin();
//		winner=minsearchers[0].getMinode();
////		System.out.println(winner);
//		for(int i=1;i<minsearchers.length;++i)
//		{
//			if((winner==null)||(minsearchers[i].getMin()<featureDistMin))
//			{
//				if(minsearchers[i].getMinode()!=null)
//				{
//					winner=minsearchers[i].getMinode();	
//					featureDistMin=minsearchers[i].getMin();
//				}
//			}
//		}
		for (Node node : nodes)
		{
			node.updateDistanceSquaredFromWinner(winner);
		}
		java.util.Arrays.sort(nodes, comp);
		double timeq=t/tmax;
		double e=estart*(java.lang.Math.pow(eend/estart, timeq));
		double delta=deltastart*(java.lang.Math.pow(deltaend/deltastart, timeq));
//		System.out.println(t+" "+timeq+" "+e+" "+delta);
		delta*=delta;
		for (Node node : nodes)
		{
			if(node.updateFeatures(e, delta,sample)==false)
				break;
		}
		
		t+=1;
	}
	class DistComp implements java.util.Comparator<Node>
	{

		public int compare(Node o1, Node o2)
		{
			return Double.compare(o1.getDistanceSquaredFromWinner(), o2.getDistanceSquaredFromWinner());
		}

	}
	public java.util.List<de.elbosso.algorithms.nn.Edge> getEdges()
	{
		if(edges==null)
		{
			edges=new java.util.LinkedList();
			for(Node node:nodes)
			{
				int[] locations=node.getLocations();
				for(int i=0;i<locations.length;++i)
				{
					if(locations[i]>0)
					{
						Node theOther=searchNeighbor(node,i);
						if(theOther!=null)
							edges.add(new de.elbosso.algorithms.nn.Edge(node,theOther));
//						else 
//						{
//							System.out.println(node+" "+i);
//							System.exit(-1);
//						}
//						System.out.println("##");
					}
				}
			}
		}
		return edges;
	}
	private Node searchNeighbor(Node me,int index)
	{
		Node rv=null;
		for(Node node:nodes)
		{
//			System.out.println("--");
			int[] locations=node.getLocations();
			rv=node;
			for(int i=0;i<locations.length;++i)
			{
//				System.out.println(i+" "+index+" "+me.getLocations()[i]+" "+locations[i]);
				if(i!=index)
				{
					if(me.getLocations()[i]!=locations[i])
					{
						rv=null;
						break;
					}
				}
				else
				{
					if(me.getLocations()[i]-1!=locations[i])
					{
						rv=null;
						break;
					}
				}
			}
			if(rv!=null)
				break;
		}
		return rv;
	}
	
}
