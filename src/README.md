This project is about an undirected unweighted graph that consists a set of vertices and edges which connect the vertices. 
In the project there are two main classes that together represents this graph: NodeData and DGraph.

1. NodeData class: This class implements node_data interface. It represents a single vertice in the graph. A single vertice has:
id (key) - a specific number that signifies it in the graph,  info - a string that gives this vertice’s information.
tag - temporal data (0/1) which can be used by algorithms.
Every node holds his neighbors in HashMap data structure (by given key and gets it’s vertice)  
 -it represents all the edges which starts or ends at this node.   

DGraph class: this class represents the graph and implements graph interface. this graph is implemented by:
A HashMap that contains all the vertices in the graph (by given key and gets it’s vertice) . 
The basic operations like add node remove node are take O(1) with this data structure, so it's effective.  
it also has a modifyCount that updates whenever action on the graph is taken.
edgeSize - return the number of edges in the graph by a local variable that increased or decreased in the methods respectively. (time complexity: O(1)) 
 
 
The graph_Algo class implements graph_algorithms interface. this class has functions that activate on the graph.
 
 * init - shallow copy (pointer) to the given graph. 

 * copy - create a new graph and adding new nodes that have the same keys and neighbors like in the current graph.
 
 * isConnected - To check connectivity of a graph, we will try to traverse all nodes using BFS traversal algorithm. 
 After completing the traversal, if there is any node, which is not visited, then the graph is not connected. because the definition of 
 connected graph is that there is a valid path from every node to every other node.
 
 * shortestPathDist- The claim for BFS is that the first time a node is discovered during the traversal, that distance from the source would give us the shortest path.
 I used a queue because the FIFO idea that the first node to come in the queue would be the first to discover his neighbors and so on with his neighbors, it maintains the order of the nodes in the graph from
 source node ,and very efficient data structure.  I use the tag field in node_data to mark that a specific node was visited.
 
 * ShortestPath: Used backtracking with DFS to traverse all paths possible to the destination node while taking the shortest 
 one. The base case for this function is first off checking the curr node was already in our path meaning we've circled back therefore we return NULL 
 to signify no path was found. Secondly if curr node equals the dest node then we return the curr path. Lastly if all other conditions did not hold
 then add curr node to curr path, then for each neighbor calculate recursively the path to dest. Save the shortest path from all recursive calls. 
 Before returning the shortest path remove curr node from curr path to let the node previous in the call stack a chance to have a clean path to work with.
 Return the shortest path.
                       
 