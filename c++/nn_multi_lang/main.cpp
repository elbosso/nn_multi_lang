#include <iostream>
#include "neuralgas.hpp"
#include <vector>
#include "svggen.hpp"

using namespace std;
using namespace nn;
using namespace svggen;

int main()
{
    std::vector<double> a=nn::Generator::generateSample(2);
    FeatureRepresentation fr(a);
    cout << "Hello world!" << " " << fr <<endl;
    std::vector<neuralgas::Node> nodes=neuralgas::Generator::generate(2,2);
    cout << nodes[0] << endl << nodes[1] <<endl;
 		nodes=neuralgas::Generator::generate(25,2);
		neuralgas::Network network(nodes);
		network.init(10000,1.0,0.001,13,0.01);
		int i=0;
		int plotinterval=2000;
				SvgRenderer<neuralgas::Node>::outNodes(string("neuralgas"),0,network.getNodes());
		for(;i<10000;++i)
		{
 //           cout << i << endl;
			std::vector<double> sample=neuralgas::Generator::generateSample(2);
           cout << i<< " " <<sample[0] << endl;
			network.learn(sample);
			 cout << i<< " " <<network.getNodes()[0].getFeatures()[0] << endl;
			if((i+1)%plotinterval==0)
				SvgRenderer<neuralgas::Node>::outNodes(string("neuralgas"),i+1,network.getNodes());
		}
//		JFreeChartRenderer.outNodes("neuralgas",i+1,java.util.Arrays.asList(network.getNodes()));



    return 0;
}
