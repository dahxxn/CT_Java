package P_20250520;

import java.io.*;
import java.util.*;

// 10 - 10:40 
class Node implements Comparable<Node> {
	int to;
	int weight;
	
	Node(int to, int weight){
		this.to = to; 
		this.weight = weight; 
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight; 
	}
}

public class G4_1753 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int V;
	static int E;
	static int K;

	static int[] dist;

	static HashMap<Integer, List<Node>> graph = new HashMap<>();

	public static void main(String[] args) throws IOException {
		input();
		findShortest();

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
		        System.out.println("INF");
			
		    else
		        System.out.println(dist[i]);
		}

	}

	public static void findShortest() {
		// K 에서 시작
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Arrays.fill(dist, Integer.MAX_VALUE); 
		dist[K] = 0;
		queue.offer(new Node(K,0)); 

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int now = node.to; 
			
			if(dist[now] < node.weight) continue; 
			
			List<Node> nodeList = graph.getOrDefault(now, new ArrayList<>());

			for (Node next : nodeList) {
				int nextDist = dist[now] + next.weight; 
				
				if(nextDist < dist[next.to]) { // 최단 경로를 찾은 경우 
					dist[next.to] = nextDist; 
					queue.offer(new Node(next.to, nextDist)); 
				}
				
			}
		}

	}

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeNum = Integer.parseInt(st.nextToken());

			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			Node node = new Node(to, weight);

			List<Node> nodeList = graph.getOrDefault(nodeNum, new ArrayList<>());
			nodeList.add(node);

			graph.put(nodeNum, nodeList);
		}

		br.close();

		dist = new int[V + 1];

	}

}
