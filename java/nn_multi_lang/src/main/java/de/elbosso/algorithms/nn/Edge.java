/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn;

/**
 *
 * @author elbosso
 */
public class Edge<T extends FeatureRepresentation>
{
	private final T a;
	private final T b;

	public Edge(T a, T b)
	{
		super();
		this.a = a;
		this.b = b;
	}
	public boolean contains(T node)
	{
		return ((a.getId()==node.getId())||(b.getId()==node.getId()));
	}
	public T getTheOther(T node)
	{
		T rv=null;
		if(a.getId()==node.getId())
			rv=b;
		else if(b.getId()==node.getId())
			rv=a;
		return rv;
	}
	public T getA()
	{
		return a;
	}

	public T getB()
	{
		return b;
	}
	
}
