package P_20250516;

import java.io.*;
import java.util.*;

public class S1_1991 {

    static class Node {
        String data;
        Node leftChild;
        Node rightChild;

        public Node(String data) {
            this.data = data;
        }
    }

    static HashMap<String, Node> tree = new HashMap<>();
    static StringBuilder preorderResult = new StringBuilder();
    static StringBuilder inorderResult = new StringBuilder();
    static StringBuilder postorderResult = new StringBuilder();

    public static void main(String[] args) throws IOException {
        inputTree();
        printTree();
    }

    public static void printTree() {
        Node root = tree.get("A");

        preorder(root);
        inorder(root);
        postorder(root);

        System.out.println(preorderResult.toString());
        System.out.println(inorderResult.toString());
        System.out.println(postorderResult.toString());
    }

    public static void preorder(Node node) {
        if (node == null) return;
        preorderResult.append(node.data);
        preorder(node.leftChild);
        preorder(node.rightChild);
    }

    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.leftChild);
        inorderResult.append(node.data);
        inorder(node.rightChild);
    }

    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.leftChild);
        postorder(node.rightChild);
        postorderResult.append(node.data);
    }

    public static void inputTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String parentData = st.nextToken();
            String leftData = st.nextToken();
            String rightData = st.nextToken();

            // 현재 노드가 없으면 새로 생성
            Node parent = tree.getOrDefault(parentData, new Node(parentData));
            // 왼쪽 자식 처리
            if (!leftData.equals(".")) {
                Node left = tree.getOrDefault(leftData, new Node(leftData));
                parent.leftChild = left;
                tree.put(leftData, left); // 자식도 트리에 넣음
            }
            // 오른쪽 자식 처리
            if (!rightData.equals(".")) {
                Node right = tree.getOrDefault(rightData, new Node(rightData));
                parent.rightChild = right;
                tree.put(rightData, right); // 자식도 트리에 넣음
            }

            // 부모 노드를 트리에 저장 (자식까지 연결된 최신 객체로)
            tree.put(parentData, parent);
        }
    }
}
