package P_20250517;

import java.io.*;
import java.util.*;

public class G3_11437 {
	static int N, M;
	static List<List<Integer>> tree = new ArrayList<>();
	static int[] parent;
	static int[] depth;
	static StringBuilder sb; 

	public static void main(String[] args) throws IOException {
		inputAndSetTree(); 
		
		System.out.print(sb);
	}

	public static void inputAndSetTree() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		
		StringTokenizer st ; 

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			tree.get(node2).add(node1);
			tree.get(node1).add(node2);
		}

		// 부모 및 깊이 설정
		parent = new int[N + 1];
		depth = new int[N + 1];
		dfs(1, 0);
		
		
		M = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			sb.append(lca(node1, node2)).append("\n");
		}

	}

	// DFS로 parent[], depth[] 채우기
	static void dfs(int curr, int par) {
		parent[curr] = par;
		depth[curr] = depth[par] + 1;

		for (int next : tree.get(curr)) {
			if (next != par) {
				dfs(next, curr);
			}
		}
	}

	static int lca(int a, int b) {
		while (depth[a] > depth[b])
			a = parent[a];
		while (depth[b] > depth[a])
			b = parent[b];

		// 동시에 위로 이동
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}

		return a;
	}
}

//import java.util.*;
//
//// LCA 
//class Node{
//	int data; 
//	Node parent; 
//	List<Node> childs; 
//	
//	public Node(int data) {
//		this.data = data; 
//		this.childs = new ArrayList<>(); 
//	}
//}
//
//public class Main {
//	static int N; 
//	static int M; 
//	
//	static HashMap<Integer, Node> tree = new HashMap<>(); 
//	static List<List<Integer>> mList = new ArrayList<>(); 
//	
//	public static void main(String[] args) throws IOException{
//		inputAndSetTree(); 
//		findResult(); 
//	}
//	
//	public static void inputAndSetTree() throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine()); 
//		StringTokenizer st; 
//		
//		// 처음에 루트 노드만 존재 
//		Node root = new Node(1); 
//		tree.put(1, root);
//		
//		List<List<Integer>> edgesInfo = new ArrayList<>(); 
//		for(int i = 0 ; i<N+1 ; i++) {
//			edgesInfo.add(new ArrayList<>()); 
//		}
//		
//		
//		for(int i = 0 ; i<N-1 ; i++) {
//			String line = br.readLine(); 
//			st = new StringTokenizer(line); 
//			
//			int node1 = Integer.parseInt(st.nextToken());
//			int node2 = Integer.parseInt(st.nextToken()); 
//			
//			edgesInfo.get(node1).add(node2); 
//			edgesInfo.get(node2).add(node1); 
//		}
//		
//		setTree(edgesInfo); // 트리 구성 완료 
//		
//		M = Integer.parseInt(br.readLine()); 
//		
//		for(int i = 0 ; i<M ; i++) {
//			String line = br.readLine(); 
//			
//			st = new StringTokenizer(line); 
//			
//			int node1 = Integer.parseInt(st.nextToken());
//			int node2 = Integer.parseInt(st.nextToken()); 
//			System.out.println(findCommonParent(tree.get(node1), tree.get(node2))); 
//
////			List<Integer> m = new ArrayList<>(); 
////			m.add(node1); 
////			m.add(node2); 
////			
////			mList.add(m); 
//		}
//		
//		br.close(); 
//		
//	}
//	
//	
//	public static void setTree(List<List<Integer>> edgesInfo) {
//		
//		Queue<Integer> queue = new LinkedList<>(); 
//		boolean[] visited = new boolean[N+1]; 
//		
//		queue.add(1); // 루트가 1 
//		
//		visited[1] = true; 
//		
//		tree.put(1, new Node(1)); 
//		
//		while(!queue.isEmpty()) {
//			int currentNode = queue.poll(); 
//			
//			Node node = tree.get(currentNode); 
//			
//			for(int child : edgesInfo.get(currentNode)) {
//				if(!visited[child]) {
//					visited[child] = true; 
//					Node childNode = new Node(child); 
//					childNode.parent = node; 
//					node.childs.add(childNode); 
//					
//					tree.put(child, childNode); 
//					tree.put(node.data, node); 
//					queue.offer(child); 	
//				}
//			}
//			
//		}
//		
//	}
//	
//	
//	public static void findResult() {
//		for(List<Integer> pair : mList) {
//			int node1= pair.get(0); 
//			int node2= pair.get(1); 
//			
//			//System.out.println("현재 찾으려는 노드 들 : " + node1 + " , " + node2); 
//			
//			System.out.println(findCommonParent(tree.get(node1), tree.get(node2))); 
//		}
//	}
//	
//	public static int findCommonParent(Node node1, Node node2) {
//	    Set<Integer> ancestors = new HashSet<>();
//
//		 // node1의 모든 조상 기록
//	    while (node1 != null) {
//	        ancestors.add(node1.data);
//	        node1 = node1.parent;
//	    }
//	    
//	    // node2의 조상 중 가장 먼저 나오는 node1의 조상 반환
//	    while (node2 != null) {
//	        if (ancestors.contains(node2.data)) {
//	            return node2.data;
//	        }
//	        node2 = node2.parent;
//	    }
//	    
//	    return 1; // 이론상 도달하지 않음 (모든 노드의 루트는 1이기 때문에)
//	}
//
//}
//
