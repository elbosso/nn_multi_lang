#include<iostream>
#include<vector>

namespace nn
{
    class FeatureRepresentation
    {
        private:
        static long runningNumber;
        long id;
        std::vector<double> features;

        public:
        FeatureRepresentation(std::vector<double> &features);
        long getId();
        std::vector<double> getFeatures();
        friend std::ostream& operator<< (std::ostream &out, FeatureRepresentation &fr);

        protected:
        double computeSquaredFeatureDistance(std::vector<double> sample);

    };
}
