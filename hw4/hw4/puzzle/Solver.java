package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public final class Solver {
    private class SearchNode {
        WorldState worldState;
        int moves;
        SearchNode previous;

        public SearchNode(WorldState worldState, int moves, SearchNode searchNode) {
            this.worldState = worldState;
            this.moves = moves;
            this.previous = searchNode;
        }
    }

    private List<WorldState> res = new ArrayList<>();
    private int moves = 0;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> minPQ = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return o1.worldState.estimatedDistanceToGoal() + o1.moves
                        - o2.worldState.estimatedDistanceToGoal() - o2.moves;
            }
        });
        SearchNode initialSearchNode = new SearchNode(initial, 0, null);
        minPQ.insert(initialSearchNode);
        while (!minPQ.isEmpty()) {
            SearchNode x = minPQ.delMin();
            this.res.add(x.worldState);
            if (x.worldState.isGoal()) {
                break;
            }
            this.moves++;

            int nextMoves = x.moves + 1;
            for (WorldState worldState : x.worldState.neighbors()) {
                boolean tag = false;
//                Iterator<SearchNode> searchNodeIterator = minPQ.iterator();
//                while (searchNodeIterator.hasNext()) {
//                    if (searchNodeIterator.next().worldState.equals(worldState)) {
//                        tag = true;
//                        break;
//                    }
//                }
//
//                SearchNode previousSearchNode = x.previous;
//                while (previousSearchNode != null) {
//                    if (previousSearchNode.worldState.equals(worldState)) {
//                        tag = true;
//                        break;
//                    }
//                    previousSearchNode = previousSearchNode.previous;
//                }


                if (!tag) {
                    SearchNode enqueuingSearchNode = new SearchNode(worldState, nextMoves, x);
                    minPQ.insert(enqueuingSearchNode);
                }
            }
        }


    }


    // Returns the minimum number of moves to solve the puzzle starting
    // at the initial WorldState.
    public int moves() {
        return this.moves;
    }

    // Returns a sequence of WorldStates from the initial WorldState
    // to the solution.
    public Iterable<WorldState> solution() {
        return this.res;
    }
}
