/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.scratch.algorithms.nn;

import java.io.IOException;

/**
 *
 * @author elbosso
 */
public class SVGRenderer
{
	static <T extends de.elbosso.algorithms.nn.FeatureRepresentation> void outNodes(java.lang.String basename, int steps, java.util.Collection<T> features) throws IOException,javax.xml.transform.TransformerException,javax.xml.parsers.ParserConfigurationException
	{
		java.io.File f=java.io.File.createTempFile("aaaa", "bbbb");
		java.io.File dir=f.getParentFile();
		f.deleteOnExit();
		f=new java.io.File(dir,basename+" "+steps+".svg");
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder builder = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.newDocument();
		
		// create the root element node
		org.w3c.dom.Element element = doc.createElement("svg");
		doc.appendChild(element);
		element.setAttribute("xmlns:svg","http://www.w3.org/2000/svg");
		element.setAttribute("xmlns","http://www.w3.org/2000/svg");
		element.setAttribute("width", "1000.0");
		element.setAttribute("height", "1000.0");
		org.w3c.dom.Element group = doc.createElement("g");
		group.setAttribute("transform", "translate(0,1000) scale(1,-1)");
		element.appendChild(group);
		for (de.elbosso.algorithms.nn.FeatureRepresentation feature : features)
		{
			org.w3c.dom.Element path = doc.createElement("circle");
			group.appendChild(path);
			path.setAttribute("cx", ""+(feature.getFeatures()[0]*1000));
			path.setAttribute("cy", ""+(feature.getFeatures()[1]*1000));
			path.setAttribute("r", "23");
			path.setAttribute("style","color:#000000;clip-rule:nonzero;display:inline;overflow:visible;visibility:visible;opacity:0.56999984;color-interpolation:sRGB;color-interpolation-filters:linearRGB;fill:#00ff00;fill-opacity:1;fill-rule:nonzero;stroke:#000000;stroke-width:0.01;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;marker:none;enable-background:accumulate");
		}
		javax.xml.transform.TransformerFactory factory = javax.xml.transform.TransformerFactory.newInstance();
		javax.xml.transform.Transformer trans = factory.newTransformer();
		javax.xml.transform.Source src = new javax.xml.transform.dom.DOMSource(doc);
		java.io.FileOutputStream fos=new java.io.FileOutputStream(f);
		javax.xml.transform.Result res = new javax.xml.transform.stream.StreamResult(fos);
		trans.transform(src, res);
		fos.close();
	}
	static <T extends de.elbosso.algorithms.nn.Edge> void outEdges(java.lang.String basename, int steps, java.util.Collection<T> edges) throws IOException,javax.xml.transform.TransformerException,javax.xml.parsers.ParserConfigurationException
	{
		java.io.File f=java.io.File.createTempFile("aaaa", "bbbb");
		java.io.File dir=f.getParentFile();
		f.deleteOnExit();
		f=new java.io.File(dir,basename+" "+steps+".svg");
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder builder = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.newDocument();
		
		// create the root element node
		org.w3c.dom.Element element = doc.createElement("svg");
		doc.appendChild(element);
		element.setAttribute("xmlns:svg","http://www.w3.org/2000/svg");
		element.setAttribute("xmlns","http://www.w3.org/2000/svg");
		element.setAttribute("width", "1000.0");
		element.setAttribute("height", "1000.0");
		org.w3c.dom.Element group = doc.createElement("g");
		group.setAttribute("transform", "translate(0,1000) scale(1,-1)");
		element.appendChild(group);
		for (de.elbosso.algorithms.nn.Edge edge : edges)
		{
			org.w3c.dom.Element path = doc.createElement("path");
			group.appendChild(path);
			java.lang.StringBuffer sb=new java.lang.StringBuffer("M");
			sb.append(edge.getA().getFeatures()[0]*1000);
			sb.append(" ");
			sb.append(edge.getA().getFeatures()[1]*1000);
			sb.append(" L");
			sb.append(edge.getB().getFeatures()[0]*1000);
			sb.append(" ");
			sb.append(edge.getB().getFeatures()[1]*1000);
			path.setAttribute("d", sb.toString());
			path.setAttribute("style","fill:none;fill-opacity:1;stroke:#000000;stroke-opacity:1;stroke-width:1;stroke-miterlimit:4;stroke-dasharray:none");
//			<path d="M150 0 L75 200 L225 200 Z" />
		}
		javax.xml.transform.TransformerFactory factory = javax.xml.transform.TransformerFactory.newInstance();
		javax.xml.transform.Transformer trans = factory.newTransformer();
		javax.xml.transform.Source src = new javax.xml.transform.dom.DOMSource(doc);
		java.io.FileOutputStream fos=new java.io.FileOutputStream(f);
		javax.xml.transform.Result res = new javax.xml.transform.stream.StreamResult(fos);
		trans.transform(src, res);
		fos.close();
	}
}
