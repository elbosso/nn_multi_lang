#include "neuralgas.hpp"
#include <math.h>
#include <limits>
#include <algorithm>
#include <iostream>

using namespace nn;
using namespace std;

namespace neuralgas
{
    Node::Node(std::vector<double> features):FeatureRepresentation(features)
    {
    }
    Node::~Node()
    {
    }
    double Node::getSquaredFeaturesDistance()
    {
        return squaredFeaturesDistance;
    }
 	double Node::computeSquaredFeatureDistance(std::vector<double> sample)
	{
		squaredFeaturesDistance=FeatureRepresentation::computeSquaredFeatureDistance(sample);
		return squaredFeaturesDistance;
	}
   void Node::updateFeatures(double e, double lambda,std::vector<double> sample,double k)
    {
        double h=exp(-k/lambda);
//        cout<<(e*h)<<endl;
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
	Network::Network(std::vector<Node> n):nodes(n)
	{
	}
	void Network::init(double _tmax, double _estart, double _eend, double _lambdastart, double _lambdaend)
	{
		t=0;
		this->tmax=_tmax;
		this->estart=_estart;
		this->eend=_eend;
		this->lambdastart=_lambdastart;
		this->lambdaend=_lambdaend;
	}
	double Network::getT()
	{
		return t;
	}
    bool sortImpl(Node left, Node right)
    {
        return left.getSquaredFeaturesDistance()<right.getSquaredFeaturesDistance();
    }
	void Network::learn(std::vector<double> sample)
	{
		double featureDistMin=std::numeric_limits<double>::max();
		cout<<featureDistMin<<endl;
		Node &winner=nodes[0];

		for (unsigned int i=0;i<nodes.size();++i)
		{
			double dist=nodes[i].computeSquaredFeatureDistance(sample);
//			cout << i<< " "<<dist<< " "<<(dist<featureDistMin)<<endl;
			if(dist<featureDistMin)
			{
				winner=nodes[i];
				featureDistMin=dist;
			}
		}
//cout<<winner.getId()<<endl;
cout<<featureDistMin<<" "<<winner.getId()<<" "<<nodes[0].getId()<< endl;

		std::sort(nodes.begin(),nodes.end(),sortImpl);
cout<<winner.getId()<<" "<<nodes[0].getId() << endl;
		double timeq=t/tmax;
		double e=estart*(pow(eend/estart, timeq));
		double lambda=lambdastart*(pow(lambdaend/lambdastart, timeq));
//		cout<<t<<" "<<timeq<<" "<<e<<" "<<lambda<<endl;
		for (unsigned int i=0;i<nodes.size();++i)
		{
			nodes[i].updateFeatures(e, lambda,sample,i);
		}
		t+=1;
	}
	std::vector<Node> Network::getNodes()
	{
		return nodes;
	}
}
