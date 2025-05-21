import java.util.*;

public class Client {
    // Insert test cases here
    static List<List<Integer>> A = Arrays.asList(
        Arrays.asList(1, 0, 0, 0, 1),
    Arrays.asList(1, 1, 1, 0, 1),
    Arrays.asList(0, 0, 1, 1, 1),
    Arrays.asList(0, 0, 0, 0, 1),
    Arrays.asList(1, 1, 1, 1, 1)
    );
    
    

    public static void main(String[] args) {
        runPathfinding();
    }

    public static void runPathfinding() {
        List<String> path = findPath(A);
        System.out.println("Final Path Coordinates:");
        System.out.println(path);
        System.out.println("Map View of Path:");
        printPathOnMap(A, path);
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
            if (dfs(map, visited, start[0], start[1], startWall, path, null, 0)) {
                return path;
            }
        }

        return Collections.emptyList();
    }

    private static boolean dfs(List<List<Integer>> map, boolean[][] visited,
                               int r, int c, int startWall,
                               List<String> path, int[] prevDir, int turnCount) {
        int R = map.size(), C = map.get(0).size();

        if (r < 0 || r >= R || c < 0 || c >= C) return false;
        if (visited[r][c] || map.get(r).get(c) != 1) return false;

        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        int wall = wallOf(r, c, R, C);
        if (path.size() > 1 && wall != -1 && wall != startWall && turnCount >= 1) {
            return true;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int newTurnCount = turnCount;
            if (prevDir != null && (dir[0] != prevDir[0] || dir[1] != prevDir[1])) {
                newTurnCount++;
            }

            if (dfs(map, visited, r + dir[0], c + dir[1], startWall, path, dir, newTurnCount)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private static int wallOf(int r, int c, int R, int C) {
        if (r == 0)       
            return 0;
        if (r == R - 1)   
            return 1;
        if (c == 0)       
            return 2;
        if (c == C - 1)   
            return 3;
        return -1;
    }

    public static void printPathOnMap(List<List<Integer>> map, List<String> path) {
        int R = map.size(), C = map.get(0).size();
        String[][] display = new String[R][C];
        for (String[] row : display) Arrays.fill(row, " ");

        for (String coord : path) {
            int r = Integer.parseInt(coord.substring(coord.indexOf('[') + 1, coord.indexOf(']')));
            int c = Integer.parseInt(coord.substring(coord.lastIndexOf('[') + 1, coord.lastIndexOf(']')));
            display[r][c] = "1";
        }

        for (String[] row : display) {
            System.out.println(Arrays.toString(row));
        }
    }
}
