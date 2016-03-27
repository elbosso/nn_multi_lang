using System;

namespace nn
{
	class MainClass
	{
		public static void Main (string[] args)
		{
//			mainNeuralGas(args);
			mainKohonen(args);
		}
		public static void mainNeuralGas(String[] args) 
		{
			nn.unsupervised.neuralgas.Generator generator=new nn.unsupervised.neuralgas.Generator();
			nn.unsupervised.neuralgas.Node[] nodes=generator.generate(25,2);
			nn.unsupervised.neuralgas.Network network=new nn.unsupervised.neuralgas.Network(nodes);
			network.init(10000,1.0,0.001,13,0.01);
			int i=0;
			int plotinterval=2000;
			//			de.netsysit.util.StopWatch sw=new de.netsysit.util.StopWatch(true);
			for(;i<10000;++i)
			{
				double[] sample=generator.generateSample(2);
				network.learn(sample);
				if((i+1)%plotinterval==0)
					GnuplotRenderer<FeatureRepresentation,Edge<FeatureRepresentation> >.outNodes("neuralgas",i+1,new System.Collections.Generic.List<FeatureRepresentation>(network.Nodes));
			}
			//			sw.measure();
			//			System.out.println(sw.getTimeSpan());
			SvgRenderer<FeatureRepresentation,Edge<FeatureRepresentation> >.outNodes("neuralgas",i+1,new System.Collections.Generic.List<FeatureRepresentation>(network.Nodes));
		}
		public static void mainKohonen(String[] args) 
		{
			nn.unsupervised.kohonen.Generator generator=new nn.unsupervised.kohonen.Generator();
			nn.unsupervised.kohonen.Node[] nodes=generator.generate(new int[]{5,5},2);
			nn.unsupervised.kohonen.Network network=new nn.unsupervised.kohonen.Network(nodes);
			network.init(10000,1.0,0.001,13,0.01);
			int i=0;
			int plotinterval=2000;
			//			de.netsysit.util.StopWatch sw=new de.netsysit.util.StopWatch(true);
			for(;i<10000;++i)
			{
				double[] sample=generator.generateSample(2);
				network.learn(sample);
				if((i+1)%plotinterval==0)
					SvgRenderer<nn.unsupervised.kohonen.Node,Edge<nn.unsupervised.kohonen.Node> >.outEdges("kohonen",i+1,new System.Collections.Generic.List<Edge<nn.unsupervised.kohonen.Node> >(network.Edges));
			}
			//			sw.measure();
			//			System.out.println(sw.getTimeSpan());
			SvgRenderer<nn.unsupervised.kohonen.Node,Edge<nn.unsupervised.kohonen.Node> >.outEdges("kohonen",i+1,new System.Collections.Generic.List<Edge<nn.unsupervised.kohonen.Node> >(network.Edges));
		}

	}
}
