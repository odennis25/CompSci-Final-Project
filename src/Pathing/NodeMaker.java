package Pathing; 

import core.RTSMain;

public class NodeMaker 
{
	
	public static Node[][] NodeMaker()
	{
		//makes all the nodes
		Node[][] temp = new Node[21][21];
		int idCount = 1;
		
		
		for(int i = 0;i<RTSMain.getNMap().length;i++)
		{
			for(int j  = 0;j<RTSMain.getNMap().length;j++)
			{
				
				temp[i][j] = new Node(1,idCount,1050-(50*i),1050-(50*j));
				System.out.print("[" + temp[i][j].getId() + "] ");
				idCount++;
			}
			System.out.println();
		}
		
		System.out.println(temp[10][12]);
		
		
		
		
		//gives the nodes their edges
		for(int i = 0; i<RTSMain.getNMap().length; i++)
		{
			for(int j = 0; j<RTSMain.getNMap().length;j++)
			{
				if(temp[i][j].getId()<=21||
					temp[i][j].getId()>=421||
					temp[i][j].getId()%21==0||
					(temp[i][j].getId()-1)%21==0)
				{
					System.out.print("{" + temp[i][j].getId() + "} ");
				}
				else
					System.out.println("[" + temp[i][j].getId() + "] ");
			}
		}
		
		return temp;
	}

public static void main(String[] args)
{
	NodeMaker();
}
}