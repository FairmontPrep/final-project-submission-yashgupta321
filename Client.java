import java.util.*;

public class Client {
    // Paste your map here, integer only 
    static List<List<Integer>> A = Arrays.asList(
        Arrays.asList(1, 0, 0, 1, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 1, 0, 0, 1, 0),
        Arrays.asList(9, 0, 0, 1, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 1, 2, 0, 0, 0),
        Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1)
    );

    public static void main(String[] args) {
        runPathfinding();  
    }

    public static void runPathfinding() {
        List<String> path = findPath(A);
        System.out.println("Final Path Coordinates:");
        System.out.println(path);
        System.out.println("Map View of Path:");
    }

    public static List<String> findPath(List<List<Integer>> map) {
        int R = map.size(), C = map.get(0).size();
        List<int[]> edgeOnes = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            if (map.get(r).get(0) == 1)       edgeOnes.add(new int[]{r, 0});
            if (map.get(r).get(C - 1) == 1)   edgeOnes.add(new int[]{r, C - 1});
        }
        for (int c = 0; c < C; c++) {
            if (map.get(0).get(c) == 1)       edgeOnes.add(new int[]{0, c});
            if (map.get(R - 1).get(c) == 1)   edgeOnes.add(new int[]{R - 1, c});
        }

        for (int[] start : edgeOnes) {
            boolean[][] visited = new boolean[R][C];
            List<String> path = new ArrayList<>();
            int startWall = wallOf(start[0], start[1], R, C);
            if (dfs(map, visited, start[0], start[1], startWall, path)) {
                return path;
            }
        }

        return Collections.emptyList(); 

    

    private static int wallOf(int r, int c, int R, int C) {
        if (r == 0)       return 0;
        if (r == R - 1)   return 1;
        if (c == 0)       return 2;
        if (c == C - 1)   return 3;
        return -1;
    }

    
}
