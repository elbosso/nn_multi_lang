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
public class GnuplotRenderer
{
	static <T extends de.elbosso.algorithms.nn.FeatureRepresentation> void outNodes(java.lang.String basename, int steps, java.util.Collection<T> features) throws IOException
	{
		java.io.File f=java.io.File.createTempFile("aaaa", "bbbb");
		java.io.File dir=f.getParentFile();
		f.deleteOnExit();
		f=new java.io.File(dir,basename+" "+steps+".dat");
		java.io.PrintWriter pw=new java.io.PrintWriter(f);
		for (de.elbosso.algorithms.nn.FeatureRepresentation feature : features)
		{
			pw.println(feature);
		}
		pw.close();
	}
	static <T extends de.elbosso.algorithms.nn.Edge> void outEdges(java.lang.String basename, int steps, java.util.Collection<T> edges) throws IOException
	{
		java.io.File f=java.io.File.createTempFile("aaaa", "bbbb");
		java.io.File dir=f.getParentFile();
		f.deleteOnExit();
		f=new java.io.File(dir,basename+" "+steps+".dat");
		java.io.PrintWriter pw=new java.io.PrintWriter(f);
		for (de.elbosso.algorithms.nn.Edge edge : edges)
		{
			pw.println(edge.getA());
			pw.println(edge.getB());
			pw.println();
		}
		pw.close();
	}
}
