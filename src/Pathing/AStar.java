package Pathing;

import java.util.ArrayList;
import java.util.Collections;

import com.almasb.fxgl.entity.Entity;

import core.RTSMain;

public class AStar 
{
	private static ArrayList<Integer> coordsList = new ArrayList<>();
	private static ArrayList<Node> closed = new ArrayList<>();//list of closed/checked nodes
	private static ArrayList<Node> open = new ArrayList<>();//list of open/to be checked node
//	private static Node target = new Node();
//	private static Node start =  new Node();
	//the path-finding math that i'm not entirely sure how it works
	public static Node aStar(Node newStart, Node newTarget)
	{		
		
		System.out.println(newStart + " " + newTarget);

		
		Node[][] tempNMap = RTSMain.getNMap();
		
		newStart.f = newStart.g + newStart.calcHeur(newTarget);
		open.add(newStart);
		
		

		//reserved for checking for buildings/tiles you cant cross
		Boolean[][] tempMap = RTSMain.getTerrainMap();
		
//		for(int i = 0; i<RTSMain.getMapSize(); i++)
//		{
//			for(int j = 0; j<RTSMain.getMapSize(); j++)
//			{
//				if(!tempMap[i][j])
//				{
//					closed.add(tempNMap[i][j]);
//				}
//			}
//		}

		while(!open.isEmpty())
		{
			Node n = open.get(0);
			if(n == newTarget)
			{
				return n;
			}
			
			for(Node.Edge edge:n.neighbors)
			{
				Node m = edge.node;
				double totalCost = n.g + edge.cost;
				
				if(!open.contains(m)&&!closed.contains(m))
				{
					m.parent = n;
					m.g = totalCost;
					m.f = m.g + m.calcHeur(newTarget);
					open.add(m);
				}
				else
				{
					if(totalCost<m.g)
					{
						m.parent = n;
						m.g = totalCost;
						m.f = m.g + m.calcHeur(newTarget);
						
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
		open.clear();
		closed.clear();
		return null;
	}
	
	//prints the path AStar chooses
	
	public static ArrayList<Integer> printPath(Node newStart,Node newTarget)
	{
		aStar(newStart, newTarget);
		System.out.println(newStart + " " + newTarget);
		coordsList = new ArrayList<Integer>();
		
		Node n = newTarget;
		
		if(n==null)
			return null;
		
		while(n.parent != null)
		{
			//column num
			if(n.getColumn()>0)
				coordsList.add(n.getColumn());
			else
				coordsList.add(0);
			//row num
			coordsList.add(n.getRow());
			n=n.parent;
			
			System.out.println("sup");
		}
		
		
		
		//column num
		if(n.getId()%RTSMain.getMapSize()-1>0)
			coordsList.add(n.getColumn());
		else
			coordsList.add(0);
		//row num
		coordsList.add(n.getRow());
		
		Collections.reverse(coordsList);
		
		return coordsList;
	}
//	public static void resetTargets()
//	{
//		target = null;
//		start = null;
//	}
public static void main(String[] args)
{
	Node[][] nodeList = NodeMaker.nodeMaker(new Node[21][21]);
	
	ArrayList<Integer> intList = printPath(nodeList[18][19],nodeList[7][8]);
	System.out.println(intList);

	ArrayList<Integer> intList2 = printPath(nodeList[10][12],nodeList[4][9]);
	System.out.println(intList2);
	
	ArrayList<Integer> intList3 = printPath(nodeList[4][9],nodeList[10][15]);
	System.out.println(intList3);
}

}