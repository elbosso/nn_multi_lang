using System;

namespace nn.unsupervised.neuralgas
{
	public class Network
	{
		private Node[] nodes;
		private System.Collections.Generic.IComparer<Node> comp=new DistComp();
		private double tmax;
		private double estart;
		private double eend;
		private double lambdastart;
		private double lambdaend;
		private double t;

		public Node[] Nodes
		{
			get
			{
				return nodes;
			}
		}


		public Network(Node[] nodes)
		{
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
			Array.Sort(nodes, comp);
			double timeq=t/tmax;
			double e=estart*(Math.Pow(eend/estart, timeq));
			double lambda=lambdastart*(Math.Pow(lambdaend/lambdastart, timeq));
			//		System.out.println(t+" "+timeq+" "+e+" "+lambda);
			double i=0;
			foreach (Node node in nodes)
			{
				node.updateFeatures(e, lambda,sample,i);
				++i;
			}
			t+=1;
		}
		class DistComp : System.Collections.Generic.IComparer<Node>
		{

			public int Compare(Node o1, Node o2)
			{
				return ((IComparable)o1.SquaredFeaturesDistance).CompareTo(o2.SquaredFeaturesDistance);
			}

		}

	}}

