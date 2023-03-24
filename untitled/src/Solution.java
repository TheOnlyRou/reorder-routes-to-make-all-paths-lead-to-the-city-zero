import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<int[]>> graph = new HashMap<>();

        // Build undirected graph
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];

            // Add edge to "from" node's outgoing edges
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(new int[]{to, 1});

            // Add edge to "to" node's outgoing edges (reversed)
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(to).add(new int[]{from, -1});
        }

        // Perform DFS from node 0
        return dfs(visited, graph, 0);
    }

    private int dfs(Set<Integer> visited, Map<Integer, List<int[]>> graph, int node) {
        visited.add(node);

        int cost = 0;
        List<int[]> neighbors = graph.get(node);
        for (int[] neighbor : neighbors) {
            int to = neighbor[0];
            int dir = neighbor[1];

            if (!visited.contains(to)) {
                cost += (dir == 1) ? 1 : 0; // Add cost of edge (to, node)
                cost += dfs(visited, graph, to); // Recur on neighbor node
            }
        }

        return cost;
    }
}
