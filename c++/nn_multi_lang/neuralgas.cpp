#include "neuralgas.h"
#include <math.h>

using namespace nn;

namespace neuralgas
{
    Node::Node(std::vector<double> features):FeatureRepresentation(features)
    {
    }
    Node::~Node()
    {
    }
    double Node::computeSquaredFeatureDistance(std::vector<double> sample)
    {
    return 0.0;
    }
    double Node::getSquaredFeaturesDistance()
    {
        return squaredFeaturesDistance;
    }
    void Node::updateFeatures(double e, double lambda,std::vector<double> sample,double k)
    {
        double h=exp(-k/lambda);
        for (unsigned int i=0;i<sample.size();++i)
        {
            features[i]+=e*h*(sample[i]-features[i]);
        }
    }
 	std::vector<Node> Generator::generate(unsigned int nodeCount, unsigned int dimensionOfFeatures)
	{
		std::vector<Node> nodes;
		for(unsigned int j=0;j<nodeCount;++j)
		{
			std::vector<double> features=nn::Generator::generateSample(dimensionOfFeatures);
			nodes.push_back(Node(features));
		}
		return nodes;
	}
}
