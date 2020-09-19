package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        this.maze = m;
        this.s = maze.xyTo1D(sourceX, sourceY);
        this.t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> fringe = new LinkedList<>();
        fringe.add(this.s);
        marked[this.s] = true;
        announce();
        if (this.s == this.t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        while (!fringe.isEmpty()) {
            int temp = fringe.poll();
            for (int v : maze.adj(temp)) {
                if (!marked[v]) {
                    fringe.add(v);
                    edgeTo[v] = temp;
                    distTo[v] = distTo[temp] + 1;
                    marked[v] = true;
                    announce();
                    if (v == this.t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

