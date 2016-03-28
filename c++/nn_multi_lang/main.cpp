#include <iostream>
#include "nn.hpp"

using namespace std;
using namespace nn;

int main()
{
    std::vector<double> a(2,0.0);
    FeatureRepresentation fr(a);
    cout << "Hello world!" << " " << fr <<endl;
    return 0;
}
