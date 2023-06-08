package Pathing;

import java.util.ArrayList;

import core.RTSMain;

public class Node implements Comparable<Node>
{
	//x+y coords for each node
	public int x;
	public int y;
	
	//id for each node
	private int id;
	
	//parent
	public Node parent = null;
	
	//list of nodes bordering current node
	public ArrayList<Edge> neighbors;
	
	//evaluation functions
	public double f = Double.MAX_VALUE;
	public double g = Double.MAX_VALUE;
	
	//hard-coded heuristic
	public double h;
	
	//
	Node(double h, int id, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.h=h;
		this.id=id;
		this.neighbors = new ArrayList<>();
	}
	public Node()
	{
		this.x = -1;
		this.y = -1;
		this.h=0;
		this.id=-1;
		this.neighbors = new ArrayList<>();
	}
	
	//does what the name implies
	public void addBranch(int cost, Node node)
	{
		Edge newEdge = new Edge(cost, node);
		neighbors.add(newEdge);
	}
	
	public double calcHeur(Node target)
	{
		return this.h;
	}
	

	@Override//compare stuff
	public int compareTo(Node n) 
	{
		return Double.compare(this.f, n.f);
	}
	
	//border nodes class
	public static class Edge
	{
		//cost=how expensive it is to move here, uses this value to calc best route
		public int cost;
		public Node node;
		
		Edge(int cost, Node node)
		{
			this.cost=cost;
			this.node=node;
		}
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getId()
	{
		return id;
	}
	public int getRow()
	{
		return ((this.getId()-(this.getId()%RTSMain.getMapSize()))/RTSMain.getMapSize());
	}
	public int getColumn()
	{
		return (this.getId()%RTSMain.getMapSize()-1);
	}
	
	
	public String toString()
	{
		String temp = " " + ((this.getId()-(this.getId()%RTSMain.getMapSize()))/RTSMain.getMapSize()) + " " + (this.getId()%RTSMain.getMapSize()-1);
		return id + " " + temp; 
	}
}
