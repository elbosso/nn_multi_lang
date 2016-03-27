using System;

namespace nn
{
	public class Generator
	{
		protected static Random rand=new Random();

		public virtual double[] generateSample(int dimensionOfFeatures)
		{
			double[] features=new double[dimensionOfFeatures];
			for (int i=0;i<features.GetLength(0);++i)
			{
				features[i]=rand.NextDouble();
			}
			return features;
		}
	}
}

