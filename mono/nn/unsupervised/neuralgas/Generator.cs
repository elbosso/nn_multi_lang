using System;

namespace nn.unsupervised.neuralgas
{
	public class Generator: nn.Generator
	{
		public Node[] generate(int nodeCount, int dimensionOfFeatures)
		{
			Node[] nodes=new Node[nodeCount];
			for(int j=0;j<nodeCount;++j)
			{
				double[] features=new double[dimensionOfFeatures];
				for (int i=0;i<features.GetLength(0);++i)
				{
					features[i]=rand.NextDouble();
				}
				nodes[j]=new Node(features);
			}
			return nodes;
		}
	}
}

