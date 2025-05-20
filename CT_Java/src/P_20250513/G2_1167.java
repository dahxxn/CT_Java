package P_20250513;

import java.io.*;
import java.util.*;

public class G2_1167 {
    static int N;
    static List<List<Edge>> tree = new ArrayList<>();

    // 간선을 표현하는 클래스
    public static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        inputAndSetTree();
        int diameter = getTreeDiameter();
        System.out.println(diameter);
    }

    public static void inputAndSetTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 1번부터 N번까지 사용하므로 N+1 크기
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());

            while (true) {
                int v = Integer.parseInt(st.nextToken());

                if (v == -1)
                    break;

                int w = Integer.parseInt(st.nextToken());

                // 양방향 간선 추가
                tree.get(u).add(new Edge(v, w));
            }

        }

        br.close();
    }

    public static int getTreeDiameter() {
        int farthestFromStart = bfs(1)[0];

        int[] result = bfs(farthestFromStart);

        // result[1]이 최대 거리
        return result[1];
    }

    public static int[] bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        int maxDist = 0;
        int farNode = start;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Edge e : tree.get(curr)) {
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    dist[e.to] = dist[curr] + e.weight;
                    queue.add(e.to);
                    if (dist[e.to] > maxDist) {
                        maxDist = dist[e.to];
                        farNode = e.to;
                    }
                }
            }
        }
        return new int[] { farNode, maxDist };
    }
}
