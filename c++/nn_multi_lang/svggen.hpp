#ifndef SVGGEN_HPP_INCLUDED
#define SVGGEN_HPP_INCLUDED

#include "neuralgas.hpp"
#include <vector>
#include <xercesc/util/PlatformUtils.hpp>
#include <xercesc/dom/DOM.hpp>
#include <xercesc/framework/StdOutFormatTarget.hpp>
#include <xercesc/framework/LocalFileFormatTarget.hpp>
#include <time.h>
#include <string>
#include <sstream>
#include <stdio.h>

using namespace nn;
using namespace neuralgas;
using namespace std;
//below macro gives all the required namespaces for Xerces
XERCES_CPP_NAMESPACE_USE

namespace svggen
{
    class SvgRenderer
    {
        public:
            static void outNodes(string basename, int steps, std::vector<neuralgas::Node> features);
        private:
            static void Serialiser_XML_writer(xercesc::DOMDocument* pDOMDocument, char* FullFilePath );

    };
}
#endif // SVGGEN_HPP_INCLUDED
