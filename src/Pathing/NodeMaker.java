package Pathing; 

import java.util.ArrayList;

import core.RTSMain;

public class NodeMaker 
{
	
	public static Node[][] nodeMaker(Node[][] nodeMap)
	{
		//makes all the nodes
		Node[][] temp = nodeMap;
		int idCount = 1;
		
		
		for(int i = 0;i<RTSMain.getNMap().length;i++)
		{
			for(int j  = 0;j<RTSMain.getNMap().length;j++)
			{
				
				temp[i][j] = new Node(1,idCount,(RTSMain.getNMap().length*50)-(50*i),(RTSMain.getNMap().length*50)-(50*j));
				//System.out.print("[" + temp[i][j].getId() + "] ");
				idCount++;
			}
			//System.out.println();
		}
		
		//System.out.println(temp[10][12]);
		
		
		
		
		//gives the nodes their edges
		for(int i = 0; i<RTSMain.getNMap().length; i++)
		{
			for(int j = 0; j<RTSMain.getNMap().length;j++)
			{
				if(temp[i][j].getId()<=RTSMain.getNMap().length||
					temp[i][j].getId()>=temp[RTSMain.getNMap().length-1][0].getId()||
					temp[i][j].getId()%RTSMain.getNMap().length==0||
					(temp[i][j].getId()-1)%RTSMain.getNMap().length==0)
				{
					//System.out.print("{" + temp[i][j].getId() + "} ");
					//checks if bottom left/right corner
					if(temp[i][j].getId()==temp[RTSMain.getNMap().length-1][0].getId()||temp[i][j].getId()==temp[RTSMain.getNMap().length-1][RTSMain.getNMap().length-1].getId())
					{
						if(temp[i][j].getId()==temp[RTSMain.getNMap().length-1][RTSMain.getNMap().length-1].getId())
						{
							temp[i][j].addBranch(1,temp[i-1][j]);
							temp[i][j].addBranch(1,temp[i-1][j-1]);
							temp[i][j].addBranch(1,temp[i][j-1]);
						}
						else
						{
							temp[i][j].addBranch(1,temp[i-1][j]);
							temp[i][j].addBranch(1,temp[i-1][j+1]);
							temp[i][j].addBranch(1,temp[i][j+1]);
						}
					}
					//checks if top right/left row
					else if(temp[i][j].getId()==1||temp[i][j].getId()==temp[0][RTSMain.getNMap().length-1].getId())
					{//assigns nodes to said corners
						if(temp[i][j].getId()==1)
						{
							temp[i][j].addBranch(1,temp[i][j+1]);//not sure why 1 links to 25 but im gonna try and fix it later
							temp[i][j].addBranch(1,temp[i+1][j+1]);
							temp[i][j].addBranch(1,temp[i+1][j]);
						}
						else
						{
							temp[i][j].addBranch(1,temp[i][j-1]);
							temp[i][j].addBranch(1,temp[i+1][j-1]);
							temp[i][j].addBranch(1,temp[i+1][j]);
						}
					}
					//checks if first row//
					else if(temp[i][j].getId()<=temp[0][RTSMain.getNMap().length-1].getId())
					{
								//assigns nodes to top row
							
								//left and right nodes
								temp[0][i].addBranch(1,temp[0][j-1]);
								temp[0][i].addBranch(1,temp[0][j+1]);
								
								//lower 3 nodes
								temp[0][i].addBranch(1,temp[1][j-1]);
								temp[0][i].addBranch(1,temp[1][j+1]);
								temp[0][i].addBranch(1,temp[1][j]);
					}
					//checks if bottom row//
					else if(temp[i][j].getId()>=temp[RTSMain.getNMap().length-1][RTSMain.getNMap().length-1].getId())
					{
								//left and right nodes
							temp[RTSMain.getNMap().length-1][i].addBranch(1,temp[RTSMain.getNMap().length-1][j-1]);
							temp[RTSMain.getNMap().length-1][i].addBranch(1,temp[RTSMain.getNMap().length-1][j+1]);
						
							//lower 3 nodes
							temp[RTSMain.getNMap().length-1][i].addBranch(1,temp[RTSMain.getNMap().length-2][j-1]);
							temp[RTSMain.getNMap().length-1][i].addBranch(1,temp[RTSMain.getNMap().length-2][j+1]);
							temp[RTSMain.getNMap().length-1][i].addBranch(1,temp[RTSMain.getNMap().length-2][j]);
					}
					//checks if left row//
					else if((temp[i][j].getId()-1)%RTSMain.getNMap().length==0)
					{
							//up and bottom nodes
							temp[i][0].addBranch(1, temp[i-1][0]);
							temp[i][0].addBranch(1, temp[i+1][0]);
							
							//right 3 nodes
							temp[i][0].addBranch(1, temp[i][1]);
							temp[i][0].addBranch(1, temp[i-1][1]);
							temp[i][0].addBranch(1, temp[i+1][1]);
					}
					
					//checks if right row//
					else if(temp[i][j].getId()%RTSMain.getNMap().length==0)
					{
							//up and bottom nodes
							temp[i][20].addBranch(1, temp[i-1][RTSMain.getNMap().length-1]);
							temp[i][20].addBranch(1, temp[i+1][RTSMain.getNMap().length-1]);
							
							//left 3 nodes
							temp[i][20].addBranch(1, temp[i-1][RTSMain.getNMap().length-2]);
							temp[i][20].addBranch(1, temp[i][RTSMain.getNMap().length-2]);
							temp[i][20].addBranch(1, temp[i+1][RTSMain.getNMap().length-2]);
					}
				}
				else
				{
					//adds the edges to  non-border  nodes
					//System.out.print("[" + temp[i][j].getId() + "] ");
					//up and bottom nodes
					temp[i][j].addBranch(1, temp[i-1][j]);
					temp[i][j].addBranch(1, temp[i+1][j]);
					//Right 3 nodes
					temp[i][j].addBranch(1, temp[i][j+1]);
					temp[i][j].addBranch(1, temp[i+1][j+1]);
					temp[i][j].addBranch(1, temp[i-1][j+1]);
					//left 3 nodes
					temp[i][j].addBranch(1, temp[i][j-1]);
					temp[i][j].addBranch(1, temp[i+1][j-1]);
					temp[i][j].addBranch(1, temp[i-1][j-1]);
				}
			}
			//System.out.println();
		}
			
		return temp;
	}

public static void main(String[] args)
{
	Node[][] temp = nodeMaker(RTSMain.getNMap());
	AStar.aStar(temp[0][0], temp[4][5]);
	ArrayList<Integer> ids = AStar.printPath(temp[0][0],temp[4][5]);
	for(int id:ids)
		{
			System.out.println(id+" ");
		}
		System.out.println();
}



}