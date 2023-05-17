package Pathing;

import core.RTSMain;

public class NodeMaker 
{
	public static Node[][] NodeMaker()
	{
		int idC = 1;
		Node[][] temp = new Node[21][21];
		for(int i = 0;i<RTSMain.getNMap().length;i++)
		{
			for(int j  = 0;j<RTSMain.getNMap().length;j++)
			{
				temp[i][j] = new Node(1,idC,1050-(50*i),1050-(50*j));
				idC++;
				System.out.println(temp[i][j]);
			}
		}
		
		
		return temp;
	}

public static void main(String[] args)
{
	NodeMaker();
}
}