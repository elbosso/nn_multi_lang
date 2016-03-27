using System;

namespace nn
{
	public class FeatureRepresentation
	{
		private static long runningnumber;
		private long id=++runningnumber;
		protected double[] features;

		public long Id
		{
			get
			{
				return id;
			}
		}
		public double[] Features
		{
			get
			{
				return features;
			}
		}

		public FeatureRepresentation(double[] features)
		{
			this.features = features;
		}
		internal virtual double computeSquaredFeatureDistance(double[] sample)
		{
			double result=0;
			for (int i=0;i<sample.GetLength(0);++i)
			{
				double fac=sample[i]-features[i];
				result+=fac*fac;
			}
			return result;
		}
		public override String ToString()
		{
			System.Text.StringBuilder sb=new System.Text.StringBuilder();
			foreach (double feature in features)
			{
				sb.Append(feature);
				sb.Append("\t");
			}
			sb.Append(id);
			sb.Append("\t");
			return sb.ToString();
		}
	}
}

