package P_20250522;

import java.io.*;
import java.util.*;

// 7:40 - 
class edge implements Comparable<edge> {
	int node;
	int weight;

	public edge(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compareTo(edge o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
}

public class G4_1504 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int E;

	static HashMap<Integer, List<edge>> tree;
	static int[] dist;

	static int v1;
	static int v2;

	public static void main(String[] args) throws IOException {
		input();

		// 1 -> v1 -> v2 -> N
		int path1 = sumPath(findShortestValue(1, v1, 0), findShortestValue(v1, v2, 0), findShortestValue(v2, N, 0));

		// 1 -> v2 -> v1 -> N
		int path2 = sumPath(findShortestValue(1, v2, 0), findShortestValue(v2, v1, 0), findShortestValue(v1, N, 0));

		int result = Math.min(path1, path2);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	public static int sumPath(int... paths) {
		for (int p : paths) {
			if (p == -1)
				return Integer.MAX_VALUE;
		}

		int sum = 0;
		
		for (int p : paths)
			sum += p;
		
		return sum;
	}

	public static void searchShortest(int start, int startWeight) {
		dist = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<edge> queue = new PriorityQueue<>();
		queue.offer(new edge(start, startWeight));
		dist[start] = startWeight;

		while (!queue.isEmpty()) {
			edge cur = queue.poll();

			int curDist = dist[cur.node];

			if (curDist < cur.weight)
				continue;

			List<edge> nextNodes = tree.getOrDefault(cur.node, new ArrayList<>());

			for (edge next : nextNodes) {
				int nextNode = next.node;
				int weight = next.weight;

				int nextDist = curDist + weight;

				// 최단 거리를 찾은 경우에
				if (nextDist < dist[nextNode]) {
					dist[nextNode] = nextDist;
					queue.add(new edge(nextNode, nextDist));
				}
			}
		}
	}

	public static int findShortestValue(int start, int end, int startWeight) {
		searchShortest(start, startWeight);

		return dist[end] < Integer.MAX_VALUE ? dist[end] : -1;
	}

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 트리 초기화
		tree = new HashMap<>();

		// 트리 설정
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edge e1 = new edge(b, c);
			edge e2 = new edge(a, c);

			List<edge> aEdges = tree.getOrDefault(a, new ArrayList<>());
			aEdges.add(e1);

			List<edge> bEdges = tree.getOrDefault(b, new ArrayList<>());
			bEdges.add(e2);

			tree.put(a, aEdges);
			tree.put(b, bEdges);
		}

		// 거쳐야 할 두 정점 입력
		st = new StringTokenizer(br.readLine());

		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		br.close();
	}

}
