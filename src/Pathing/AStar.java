package Pathing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class AStar 
{

    public static ArrayList<Node> findPath(Node startNode, Node goalNode) 
    {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        ArrayList<Node> closedSet = new ArrayList<>();

        startNode.g = 0;
        startNode.f = startNode.g + startNode.calcHeur(goalNode);
        openSet.add(startNode);

        while (!openSet.isEmpty()) 
        {
            Node current = openSet.poll();

            if (current == goalNode) 
            {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node.Edge neighborEdge : current.neighbors) 
            {
                Node neighbor = neighborEdge.node;

                if (closedSet.contains(neighbor)) 
                {
                    continue;
                }

                double tentativeG = current.g + neighborEdge.cost;

                if (!openSet.contains(neighbor)) 
                {
                    openSet.add(neighbor);
                } 
                else if (tentativeG >= neighbor.g) 
                {
                    continue;
                }

                neighbor.parent = current;
                neighbor.g = tentativeG;
                neighbor.f = neighbor.g + neighbor.calcHeur(goalNode);
            }
        }

        // No path found
        return null;
    }
    //prints/returns the path given by A*
    public static void printPath(Node startNode, Node goalNode) 
    {
        // Reset node states
        resetNodeState(startNode);
        resetNodeState(goalNode);

        ArrayList<Node> path = findPath(startNode, goalNode);
        if (path != null) 
        {
            System.out.println("Path found:");
            for (Node node : path) 
            {
                System.out.println(node);
            }
        } 
        else 
        {
            System.out.println("No path found.");
        }
    }
    //resets the state of every node for another A* call
    private static void resetNodeState(Node node) 
    {
        node.parent = null;
        node.f = Double.MAX_VALUE;
        node.g = Double.MAX_VALUE;
    }
    //reconstructs the path made by A*
    private static ArrayList<Node> reconstructPath(Node node) 
    {
        ArrayList<Node> path = new ArrayList<>();

        while (node != null) 
        {
            path.add(node);
            node = node.parent;
        }

        Collections.reverse(path);
        return path;
    }

    // test junk
    public static void main(String[] args) 
    {
    	Node[][] nodeMap = NodeMaker.nodeMaker(new Node[21][21]);
    	
    	Node start = nodeMap[12][1];
    	Node target = nodeMap [6][12];
    	
    	printPath(start, target);
    	
    	start = nodeMap[6][9];
    	target = nodeMap[17][4];
    	
    	printPath(start, target);
    }
}