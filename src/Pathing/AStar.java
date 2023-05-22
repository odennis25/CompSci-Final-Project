package Pathing;

import java.util.ArrayList;
import java.util.Collections;

public class AStar 
{
	//the path-finding math that i'm not entirely sure how it works
	public static Node aStar(Node start, Node target)
	{
		ArrayList<Node> closed = new ArrayList<>();//list of closed/checked nodes
		ArrayList<Node> open = new ArrayList<>();//list of open/to be checked node
		
		start.f = start.g + start.calcHeur(target);
		open.add(start);
		
		//reserved for checking for buildings/tiles you cant cross
		//
		//
		//
		//
		
		while(!open.isEmpty())
		{
			Node n = open.get(0);
			if(n == target)
				return n;
			
			for(Node.Edge edge:n.neighbors)
			{
				Node m = edge.node;
				double totalCost = n.g + edge.cost;
				
				if(!open.contains(m)&&!closed.contains(m))
				{
					m.parent = n;
					m.g = totalCost;
					m.f = m.g + m.calcHeur(target);
					open.add(m);
				}
				else
				{
					if(totalCost<m.g)
					{
						m.parent = n;
						m.g = totalCost;
						m.f = m.g + m.calcHeur(target);
						
						if(closed.contains(m))
						{
							closed.remove(m);
							open.add(m);
						}
					}
				}
			}
			open.remove(n);
			closed.add(n);
		}
		
		return null;
	}
	
	//prints the path AStar chooses
	public static void printPath(Node target)
	{
		Node n = target;
		
		if(n==null)
			return;
		
		ArrayList<Integer> ids = new ArrayList<>();
		
		while(n.parent != null)
		{
			ids.add(n.getId());
			n=n.parent;
		}
		ids.add(n.getId());
		Collections.reverse(ids);
		
		for(int id:ids)
		{
			System.out.println(id+" ");
		}
		System.out.println();
		
	}
	

}
