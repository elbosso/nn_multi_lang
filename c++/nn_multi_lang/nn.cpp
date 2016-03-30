#include "nn.hpp"
#include <stdlib.h>

namespace nn
{
    long FeatureRepresentation::runningNumber=0;

    FeatureRepresentation::FeatureRepresentation(std::vector<double> &fs):id(++FeatureRepresentation::runningNumber),features(fs)
    {
    }

    long FeatureRepresentation::getId()
    {
        return id;
    }
    std::vector<double> FeatureRepresentation::getFeatures()
    {
        return features;
    }
    std::ostream& operator<< (std::ostream &out, FeatureRepresentation &fr)
    {
        for (unsigned int i=0;i<fr.features.size();++i)
        {
            out << fr.features[i];
            out << "\t";
        }
        out << fr.id;
        out << "\t";

        return out;
    }
    double FeatureRepresentation::computeSquaredFeatureDistance(std::vector<double> sample)
    {
        double result=0;
        for (unsigned int i=0;i<sample.size();++i)
        {
            double fac=sample[i]-features[i];
            result+=fac*fac;
        }
        return result;
    }
	template <typename T>
    Edge<T>::Edge(T left, T right):a(left),b(right)
    {
    }
	template <typename T>
 	bool Edge<T>::contains(T node)
	{
		return ((a.getId()==node.getId())||(b.getId()==node.getId()));
	}
	template <typename T>
	T Edge<T>::getTheOther(T node)
	{
		T rv=NULL;
		if(a.getId()==node.getId())
			rv=b;
		else if(b.getId()==node.getId())
			rv=a;
		return rv;
	}
	template <typename T>
	T Edge<T>::getA()
	{
		return a;
	}
    template <typename T>
	T Edge<T>::getB()
	{
        return b;
	}
	std::vector<double> Generator::generateSample(int dimensionOfFeatures)
	{
		std::vector<double> features(dimensionOfFeatures,0.0);
		generateSample(features);
		return features;
	}
	void Generator::generateSample(std::vector<double> &features)
	{
 		for (unsigned int i=0;i<features.size();++i)
		{
            double r=rand() / (RAND_MAX + 1.);
			features[i]=r;
		}
	}

}
