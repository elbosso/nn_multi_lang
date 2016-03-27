/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.algorithms.nn.unsupervised.gneuralgas;

/**
 *
 * @author elbosso
 */
public class Edge extends de.elbosso.algorithms.nn.Edge<Node>
{
	private long age;

	public Edge(Node a, Node b)
	{
		super(a,b);
	}
	void age()
	{
		++age;
	}
	void rejuvenate()
	{
		age=0;
	}

	public long getAge()
	{
		return age;
	}
}
