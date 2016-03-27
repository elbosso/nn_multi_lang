using System;

namespace nn.unsupervised.kohonen
{
	public class Generator: nn.Generator
	{
		public Node[] generate(int[] nodesperdimension, int dimensionOfFeatures)
		{
			int total=1;
			for (int i=0;i<nodesperdimension.Length;++i)
			{
				total*=nodesperdimension[i];
			}
			Node[] nodes=new Node[total];
			int[] counters=new int[nodesperdimension.Length];
			for(int i=0;i<counters.Length;++i)
			{
				counters[i]=0;
			}
			int loop=0;
			while(true)
			{
				int[] locations=new int[counters.Length];
				Array.Copy(counters,locations, counters.Length);
				double[] features=new double[dimensionOfFeatures];
				for (int i=0;i<features.Length;++i)
				{
					features[i]=rand.NextDouble();
				}
				nodes[loop]=new Node(features,locations);
				++loop;
				counters[0]++;
				for(int i=0;i<counters.Length;++i)
				{
					if(counters[i]==nodesperdimension[i])
					{
						if(i+1<nodesperdimension.Length)
						{
							counters[i+1]++;
							counters[i]=0;
						}
					}
					else
						break;
				}
				if(loop==nodes.Length)
					break;
			}
			return nodes;
		}
		public override double[] generateSample(int dimensionOfFeatures)
		{
			double[] features=base.generateSample(dimensionOfFeatures);
			if(features[1]>features[0])
			{
				features[1]*=features[0];//=1-features[1];
			}
			return features;
		}
	}
}

