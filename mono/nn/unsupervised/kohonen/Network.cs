using System;

namespace nn.unsupervised.kohonen
{
	public class Network
	{
		private Node[] nodes;
		private System.Collections.Generic.IComparer<Node> comp=new DistComp();
		private double tmax;
		private double estart;
		private double eend;
		private double deltastart;
		private double deltaend;
		private double t;
		private System.Collections.Generic.List<nn.Edge<Node> > edges;
		public System.Collections.Generic.List<nn.Edge<Node> > Edges
		{
			get
			{
				if(edges==null)
				{
					edges=new System.Collections.Generic.List<nn.Edge<Node> >();
					foreach(Node node in nodes)
					{
						int[] locations=node.Locations;
						for(int i=0;i<locations.Length;++i)
						{
							if(locations[i]>0)
							{
								Node theOther=searchNeighbor(node,i);
								if(theOther!=null)
									edges.Add(new nn.Edge<Node>(node,theOther));
							}
						}
					}
				}
				return edges;
			}
		}
		public Network (Node[] nodes)
		{
			this.nodes=nodes;
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
		public void learn(double[] sample)
		{
			double featureDistMin=Double.MaxValue;
			Node winner=null;

			foreach (Node node in nodes)
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
			foreach (Node node in nodes)
			{
				node.updateDistanceSquaredFromWinner(winner);
			}
			Array.Sort(nodes, comp);
			double timeq=t/tmax;
			double e=estart*(System.Math.Pow(eend/estart, timeq));
			double delta=deltastart*(System.Math.Pow(deltaend/deltastart, timeq));
			//		System.out.println(t+" "+timeq+" "+e+" "+delta);
			delta*=delta;
			foreach (Node node in nodes)
			{
				if(node.updateFeatures(e, delta,sample)==false)
					break;
			}

			t+=1;
		}
		class DistComp : System.Collections.Generic.IComparer<Node>
		{
			public int Compare(Node o1, Node o2)
			{
				return ((IComparable)o1.DistanceSquaredFromWinner).CompareTo(o2.DistanceSquaredFromWinner);
			}
		}
		private Node searchNeighbor(Node me,int index)
		{
			Node rv=null;
			foreach (Node node in nodes)
			{
				//			System.out.println("--");
				int[] locations=node.Locations;
				rv=node;
				for(int i=0;i<locations.Length;++i)
				{
					//				System.out.println(i+" "+index+" "+me.getLocations()[i]+" "+locations[i]);
					if(i!=index)
					{
						if(me.Locations[i]!=locations[i])
						{
							rv=null;
							break;
						}
					}
					else
					{
						if(me.Locations[i]-1!=locations[i])
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
}

