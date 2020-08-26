package A4.Graph;

import javafx.scene.Parent;
import javafx.util.Pair;

import java.util.*;

public class Graph {

    boolean[][] adjacency;
    int nbNodes;

    public Graph(int nb) {
        this.nbNodes = nb;
        this.adjacency = new boolean[nb][nb];
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                this.adjacency[i][j] = false;
            }
        }
    }

    public void addEdge (int i, int j){
        adjacency[i][j] = adjacency[j][i] = true;
    }

    public void removeEdge (int i, int j){
        adjacency[i][j] = adjacency[j][i] = false;
    }

    public int nbEdges(){
        //Summation deg(v)=2|E|
        int nbEdges=0;
        int nbSelfLoops=0;
        for(int i=0; i<nbNodes;i++){
            for(int j=-0; j<nbNodes;j++){
                if(adjacency[i][j]){
                   if(i==j) nbSelfLoops++;
                   else nbEdges++;
                }
            }
        }
        return nbSelfLoops+nbEdges/2;
    }

    public boolean cycle(int start){
        //Want to remove start node. Save all neighbors of start first
        List<Integer> neighbors = new ArrayList<>();
        for(int i=0; i<nbNodes; i++){
            if(adjacency[start][i] && i!=start){
                neighbors.add(i);
            }
        }

        //Remove all the edges
        for(int neighbor: neighbors){
            removeEdge(neighbor,start);
        }

        //DFS:
        if(neighbors.size()<2){
            //Add the edges back in:
            for(int neighbor: neighbors){
                addEdge(neighbor,start);
            }
            return false;
        }
        boolean[] visited = new boolean[nbNodes];
        Stack<Integer> s = new Stack<>();

        int root = neighbors.get(0);
        s.push(root);

        while(!s.isEmpty()){
            int u = s.pop();
            if(!visited[u]){
                visited[u] = true;
                for(int i=0;i<nbNodes;i++){
                    if(adjacency[u][i] && u!=i){
                        s.push(i);
                    }
                }
            }
        }

        //Add the edges back in:
        for(int neighbor: neighbors){
            addEdge(neighbor,start);
        }

        //Final check:
        for(int neighbor: neighbors){
            if(!visited[neighbor]){
                return false;
            }
        }
        return true;
    }

    public int shortestPath(int start, int end){
        //Modification on the BFS algorithm:
        boolean[] visited = new boolean[nbNodes];
        int[] dist = new int[nbNodes];
        for(int i=0;i<nbNodes;i++) dist[i] = -1;
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        int distFromStart = 0;
        dist[start] = distFromStart;

        while(!q.isEmpty()){
            int u = q.remove();
            distFromStart +=1;
            for(int i=0;i<nbNodes;i++){
                if(adjacency[u][i] && u!=i){
                    if(!visited[i]){
                        visited[i] = true;
                        q.add(i);
                        dist[i] = distFromStart;
                    }
                }
            }
        }
        if(dist[end]>0) return dist[end];
        else return this.nbNodes+1;
    }


}