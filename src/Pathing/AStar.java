package Pathing;

import java.util.ArrayList;
import java.util.Collections;

import com.almasb.fxgl.entity.Entity;

import core.RTSMain;

public class AStar //WHEN YOU DO PATHING CALL BOTH ASTAR AND PRINT PATH TO RECIEVE THE ARRAY OF NODE IDS
{
	//the path-finding math that i'm not entirely sure how it works
	public static Node aStar(Node start, Node target)
	{
		ArrayList<Node> closed = new ArrayList<>();//list of closed/checked nodes
		ArrayList<Node> open = new ArrayList<>();//list of open/to be checked node
		
		Node[][] tempNMap = RTSMain.getNMap();
		
		start.f = start.g + start.calcHeur(target);
		open.add(start);
		
		//reserved for checking for buildings/tiles you cant cross
		Boolean[][] tempMap = RTSMain.getTerrainMap();
<<<<<<< HEAD
		Node[][] tempNMap = RTSMain.getNMap();
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
=======
		
		for(int i = 0; i<RTSMain.getMapSize(); i++)
		{
			for(int j = 0; j<RTSMain.getMapSize(); j++)
			{
				if(!tempMap[i][j])
				{
					closed.add(tempNMap[i][j]);
				}
			}
		}
>>>>>>> 49cc3c900cae8270a75a93c630fdb5f4f8304351
		
		
		
		
		
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
	public static ArrayList<Integer> printPath(Node target)
	{
		Node n = target;
		
		if(n==null)
			return null;
		
		ArrayList<Integer> coordsList = new ArrayList<>();
		
		while(n.parent != null)
		{
<<<<<<< HEAD
			coordsList.add(n.getY()/RTSMain.getBlockSize()-1);
			coordsList.add(n.getX()/RTSMain.getBlockSize()-1);
=======
			//column num
			coordsList.add(n.getId()%RTSMain.getMapSize()-1);
			//row num
			coordsList.add(((n.getId()-(n.getId()%RTSMain.getMapSize()))/RTSMain.getMapSize()));
>>>>>>> 49cc3c900cae8270a75a93c630fdb5f4f8304351
			n=n.parent;
			
			System.out.println("sup");
		}
<<<<<<< HEAD
		coordsList.add(n.getY()/RTSMain.getBlockSize()-1);
		coordsList.add(n.getX()/RTSMain.getBlockSize()-1);
=======
		
		
		
		//column num
		coordsList.add(n.getId()%RTSMain.getMapSize()-1);
		//row num
		coordsList.add(((n.getId()-(n.getId()%RTSMain.getMapSize()))/RTSMain.getMapSize()));
		
>>>>>>> 49cc3c900cae8270a75a93c630fdb5f4f8304351
		Collections.reverse(coordsList);
		System.out.println(coordsList);
		
		return coordsList;
		
	}
public static void main(String[] args)
{
	Node[][] nodeList = NodeMaker.nodeMaker(new Node[21][21]);
<<<<<<< HEAD
	aStar(nodeList[0][0],nodeList[12][10]);
	ArrayList<Integer> intList = printPath(nodeList[12][10]);
=======
	aStar(nodeList[2][3],nodeList[7][8]);
	ArrayList<Integer> intList = printPath(nodeList[7][8]);
	System.out.println(intList);
>>>>>>> 49cc3c900cae8270a75a93c630fdb5f4f8304351
	
}

}
