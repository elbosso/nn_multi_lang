#include<iostream>
#include<vector>
//only c++11
//#include <type_traits>

namespace nn
{
    class FeatureRepresentation
    {
        private:
        static long runningNumber;
        long id;

        public:
        FeatureRepresentation(std::vector<double> &features);
        long getId();
        std::vector<double> getFeatures();
        friend std::ostream& operator<< (std::ostream &out, FeatureRepresentation &fr);

        protected:
        double computeSquaredFeatureDistance(std::vector<double> sample);
        std::vector<double> features;

    };
    template <typename T>
    class Edge
    {
 //only c++11
//       static_assert(std::is_base_of<FeatureRepresentation, T>::value, "T must inherit from FeatureRepresentation");
        private:
            T a;
            T b;
        public:
            Edge(T left, T right);
            bool contains(T node);
            T getTheOther(T node);
            T getA();
            T getB();
    };
    class Generator
    {
        public:
            static std::vector<double> generateSample(int dimensionOfFeatures);
            static void generateSample(std::vector<double> &features);
    };
}
