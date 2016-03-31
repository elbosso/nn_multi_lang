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
    template <typename T>
    class SvgRenderer
    {
        public:
            static void outNodes(string basename, int steps,std::vector<T> features);
        private:
            static void Serialiser_XML_writer(xercesc::DOMDocument* pDOMDocument, char* FullFilePath );

    };

    template <typename T>
void SvgRenderer<T>::Serialiser_XML_writer(xercesc::DOMDocument* pDOMDocument, char* FullFilePath )
{
DOMImplementation *pImplement = NULL;
DOMLSSerializer *pSerializer = NULL; // @DOMWriter
LocalFileFormatTarget *pTarget = NULL;

//Return the first registered implementation that has the desired features. In this case, we are after
//a DOM implementation that has the LS feature... or Load/Save.

pImplement = DOMImplementationRegistry::getDOMImplementation(XMLString::transcode("LS"));

//From the DOMImplementation, create a DOMWriter.
//DOMWriters are used to serialize a DOM tree [back] into an XML document.

pSerializer = ((DOMImplementationLS*)pImplement)->createLSSerializer();
DOMLSOutput *pOutput = ((DOMImplementationLS*)pImplement)->createLSOutput();
DOMConfiguration *pConfiguration = pSerializer->getDomConfig();

// Have a nice output
if (pConfiguration->canSetParameter(XMLUni::fgDOMWRTFormatPrettyPrint, true))
pConfiguration->setParameter(XMLUni::fgDOMWRTFormatPrettyPrint, true);
pTarget = new LocalFileFormatTarget(FullFilePath);
pOutput->setByteStream(pTarget);

//pSerializer->write(pDOMDocument->getDocumentElement(), pOutput); // missing header "" if used
pSerializer->write(pDOMDocument, pOutput);

delete pTarget;
pOutput->release();
pSerializer->release();
}

     template <typename T>
    void SvgRenderer<T>::outNodes(string basename, int steps,std::vector<T> features)
    {
    XMLPlatformUtils::Initialize();
// Pointer to our DOMImplementation.
DOMImplementation * pDOMImplementation = NULL;

// Get the DOM Implementation (used for creating DOMDocuments).
// Also see: http://www.w3.org/TR/2000/REC-DOM-Level-2-Core-20001113/core.html
pDOMImplementation =
  DOMImplementationRegistry::getDOMImplementation(XMLString::transcode("core"));

DOMDocument * pDOMDocument = NULL;

pDOMDocument = pDOMImplementation->createDocument(XMLString::transcode("http://www.w3.org/2000/svg"), XMLString::transcode("svg"),NULL);


DOMElement * pRootElement = NULL;
pRootElement = pDOMDocument->getDocumentElement();

//pRootElement->setAttribute(XMLString::transcode(""),XMLString::transcode("") );
pRootElement->setAttribute(XMLString::transcode("width"),XMLString::transcode("1000.0") );
pRootElement->setAttribute(XMLString::transcode("height"),XMLString::transcode("1000.0") );

DOMElement * group = NULL;
group = pDOMDocument->createElementNS(XMLString::transcode("http://www.w3.org/2000/svg"),XMLString::transcode("g"));
group->setAttribute(XMLString::transcode("transform"),XMLString::transcode("translate(0,1000) scale(1,-1)") );

    pRootElement->appendChild(group);
		for (int i=0;i<features.size();++i)
		{
			 cout << i<< " " <<features[i].getFeatures()[0] << " " <<features[i].getFeatures()[1]<< endl;
DOMElement * path = NULL;
path = pDOMDocument->createElementNS(XMLString::transcode("http://www.w3.org/2000/svg"),XMLString::transcode("circle"));
 group->appendChild(path);
 char buf[256] ;
 sprintf(buf,"%f",features[i].getFeatures()[0]*1000);
 path->setAttribute(XMLString::transcode("cx"),XMLString::transcode(buf) );
 sprintf(buf,"%f",features[i].getFeatures()[1]*1000);
 path->setAttribute(XMLString::transcode("cy"),XMLString::transcode(buf) );
 path->setAttribute(XMLString::transcode("r"),XMLString::transcode("23") );
 path->setAttribute(XMLString::transcode("style"),XMLString::transcode("color:#000000;clip-rule:nonzero;display:inline;overflow:visible;visibility:visible;opacity:0.56999984;color-interpolation:sRGB;color-interpolation-filters:linearRGB;fill:#00ff00;fill-opacity:1;fill-rule:nonzero;stroke:#000000;stroke-width:0.01;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;marker:none;enable-background:accumulate") );
		}
 char path[1024];
 sprintf(path,"/tmp/%s_%d.svg",basename.c_str(),steps);
    Serialiser_XML_writer(pDOMDocument,path);

 XMLPlatformUtils::Terminate();

    }

}
#endif // SVGGEN_HPP_INCLUDED
