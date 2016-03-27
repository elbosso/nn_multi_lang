using System;

namespace nn.unsupervised.neuralgas
{
	public class Node: nn.FeatureRepresentation
	{
		private double squaredFeaturesDistance;

		public double SquaredFeaturesDistance
		{
			get
			{
				return squaredFeaturesDistance;
			}
		}

		public Node(double[] features):base(features)
		{
		}
		internal override double computeSquaredFeatureDistance(double[] sample)
		{
			squaredFeaturesDistance=base.computeSquaredFeatureDistance(sample);
			return squaredFeaturesDistance;
		}	
		internal void updateFeatures(double e, double lambda,double[] sample,double k)
		{
			{
				double h=System.Math.Exp(-k/lambda);
				for (int i=0;i<sample.GetLength(0);++i)
				{
					features[i]+=e*h*(sample[i]-features[i]);
				}
			}
		}
	}
}

