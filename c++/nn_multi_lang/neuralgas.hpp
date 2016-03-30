#ifndef NEURALGAS_H
#define NEURALGAS_H

#include "nn.hpp"

namespace neuralgas
{
    class Node: public nn::FeatureRepresentation
    {
        private:
           	double squaredFeaturesDistance;


        public:
            Node(std::vector<double> features);
            virtual ~Node();
            double getSquaredFeaturesDistance();
            void updateFeatures(double e, double lambda,std::vector<double> sample,double k);
        protected:
            double computeSquaredFeatureDistance(std::vector<double> sample);
        private:
    };
    class Generator: nn::Generator
    {
        public:
            static std::vector<Node> generate(unsigned int nodeCount, unsigned int dimensionOfFeatures) ;
    };
}
#endif // NEURALGAS_H
