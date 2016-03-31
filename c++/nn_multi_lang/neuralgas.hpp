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
            double computeSquaredFeatureDistance(std::vector<double> sample);
        private:
    };
    class Generator: public nn::Generator
    {
        public:
            static std::vector<Node> generate(unsigned int nodeCount, unsigned int dimensionOfFeatures) ;
    };
    class Network
    {
        private:
            std::vector<Node> nodes;
            double tmax;
            double estart;
            double eend;
            double lambdastart;
            double lambdaend;
            double t;

        public:
            Network(std::vector<Node> nodes);
            void init(double tmax, double estart, double eend, double lambdastart, double lambdaend);
            double getT();
            void learn(std::vector<double> sample);
            std::vector<Node> getNodes();

    };
}
#endif // NEURALGAS_H
