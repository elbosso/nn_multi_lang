#include <iostream>
#include "neuralgas.hpp"

using namespace std;
using namespace nn;

int main()
{
    std::vector<double> a=nn::Generator::generateSample(2);
    FeatureRepresentation fr(a);
    cout << "Hello world!" << " " << fr <<endl;
    std::vector<neuralgas::Node> nodes=neuralgas::Generator::generate(2,2);
    cout << nodes[0] << endl << nodes[1] <<endl;
    return 0;
}
