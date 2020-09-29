package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Solver {
    private class SearchNode {
        WorldState worldState;
        int moves;
        SearchNode previous;

        public SearchNode(WorldState worldState, int moves, SearchNode previous) {
            this.worldState = worldState;
            this.moves = moves;
            this.previous = previous;
        }
    }

    private List<WorldState> res = new ArrayList<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> minPQ = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return o1.worldState.estimatedDistanceToGoal() + o1.moves
                        - o2.worldState.estimatedDistanceToGoal() - o2.moves;
            }
        });
        minPQ.insert(new SearchNode(initial, 0, null));
        SearchNode resultSearchNode = null;
        while (!minPQ.isEmpty()) {
            resultSearchNode = minPQ.delMin();
            if (resultSearchNode.worldState.isGoal()) {
                break;
            }
            for (WorldState worldState : resultSearchNode.worldState.neighbors()) {
                if (resultSearchNode.previous == null
                        || !worldState.equals(resultSearchNode.previous.worldState)) {
                    minPQ.insert(new SearchNode(worldState,
                            resultSearchNode.moves + 1,
                            resultSearchNode));
                }
            }
        }
        while (resultSearchNode != null) {
            res.add(0, resultSearchNode.worldState);
            resultSearchNode = resultSearchNode.previous;
        }

    }


    // Returns the minimum number of moves to solve the puzzle starting
    // at the initial WorldState.
    public int moves() {
        return this.res.size() - 1;
    }

    // Returns a sequence of WorldStates from the initial WorldState
    // to the solution.
    public Iterable<WorldState> solution() {
        return this.res;
    }
}

