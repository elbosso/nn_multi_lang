using System;

namespace nn.unsupervised.kohonen
{
	public class Node : nn.FeatureRepresentation
	{
		private int[] locations;
		private double distanceFromWinner;
		public int[] Locations
		{
			get
			{
				return locations;
			}
		}
		public double DistanceSquaredFromWinner
		{
			get
			{
				return distanceFromWinner;
			}
		}


		public Node (double[] features, int[] locations):base(features)
		{
			this.locations=locations;
		}
		public override string ToString()
		{
			System.Text.StringBuilder sb=new System.Text.StringBuilder();
			foreach (int location in locations)
			{
				sb.Append(location);
				sb.Append("\t");
			}
			//		sb.append(":");
			sb.Append(base.ToString());
			return sb.ToString();
		}
		internal override double computeSquaredFeatureDistance(double[] sample)
		{
			double result=0;
			for (int i=0;i<sample.Length;++i)
			{
				double fac=sample[i]-base.Features[i];
				result+=fac*fac;
			}
			return result;
		}
		internal void updateDistanceSquaredFromWinner(Node winner)
		{
			double result=0;
			for (int i=0;i<Locations.Length;++i)
			{
				double fac=winner.Locations[i]-locations[i];
				result+=fac*fac;
			}
			distanceFromWinner=result;
		}
		internal bool updateFeatures(double e, double deltasquared,double[] sample)
		{
			bool rv=DistanceSquaredFromWinner<deltasquared;
			if(rv==true)
			{
				double h=System.Math.Exp(-DistanceSquaredFromWinner/(2*deltasquared));
				for (int i=0;i<sample.Length;++i)
				{
					features[i]+=e*h*(sample[i]-features[i]);
				}
			}
			return rv;
		}
	}
}

