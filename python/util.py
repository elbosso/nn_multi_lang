import tempfile
import os
import xml.etree.ElementTree
from xml.dom import minidom

class SvgRenderer:
    'doc'

    def prettify(self,elem):
        """Return a pretty-printed XML string for the Element.
        """
        rough_string = xml.etree.ElementTree.tostring(elem, 'utf-8')
        reparsed = minidom.parseString(rough_string)
        return reparsed.toprettyxml(indent="  ")

    def outNodes(self, basename, steps, features):
        tempd=tempfile.gettempdir();
        tempf=tempd+os.sep+basename+'_'+steps+'.svg'
#        print tempf
        svg = xml.etree.ElementTree.Element('svg')
        svg.set('xmlns:svg','http://www.w3.org/2000/svg')
        svg.set('xmlns','http://www.w3.org/2000/svg')
        svg.set('width','1000.0')
        svg.set('height','1000.0')
        group= xml.etree.ElementTree.Element('g')
        svg.append(group);
        group.set('transform','translate(0,1000) scale(1,-1)')
        for feat in features:
            feature= xml.etree.ElementTree.Element('circle')
            group.append(feature)
            feature.set('cx',`feat.getFeatures()[0]*1000`)
            feature.set('cy',`feat.getFeatures()[1]*1000`)
            feature.set('r','23')
            feature.set('style','color:#000000;clip-rule:nonzero;display:inline;overflow:visible;visibility:visible;opacity:0.56999984;color-interpolation:sRGB;color-interpolation-filters:linearRGB;fill:#00ff00;fill-opacity:1;fill-rule:nonzero;stroke:#000000;stroke-width:0.01;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;marker:none;enable-background:accumulate')
        text_file = open(tempf, 'w')
        text_file.write(self.prettify(svg))
        text_file.close()

    def outEdges(self, basename, steps, features):
        tempd=tempfile.gettempdir();
        tempf=tempd+os.sep+basename+'_'+steps+'.svg'
#        print tempf
        svg = xml.etree.ElementTree.Element('svg')
        svg.set('xmlns:svg','http://www.w3.org/2000/svg')
        svg.set('xmlns','http://www.w3.org/2000/svg')
        svg.set('width','1000.0')
        svg.set('height','1000.0')
        group= xml.etree.ElementTree.Element('g')
        svg.append(group);
        group.set('transform','translate(0,1000) scale(1,-1)')
        for edge in edges:
            path= xml.etree.ElementTree.Element('path')
            group.append(path)
            sb='M '+edge.getA().getFeatures()[0]*1000
            sb=sb+' '+`edge.getA().getFeatures()[1]*1000`+' L '
            sb=sb+edge.getA().getFeatures()[0]*1000+' '
            sb=sb+edge.getA().getFeatures()[1]*1000
            path.set('d',sb)
            path.set('style','fill:none;fill-opacity:1;stroke:#000000;stroke-opacity:1;stroke-width:1;stroke-miterlimit:4;stroke-dasharray:none')
        text_file = open(tempf, 'w')
        text_file.write(self.prettify(svg))
        text_file.close()
