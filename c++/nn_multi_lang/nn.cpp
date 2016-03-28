#include "nn.hpp"

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
        for (int i=0;i<fr.features.size();++i)
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
        for (int i=0;i<sample.size();++i)
        {
            double fac=sample[i]-features[i];
            result+=fac*fac;
        }
        return result;
    }
}
