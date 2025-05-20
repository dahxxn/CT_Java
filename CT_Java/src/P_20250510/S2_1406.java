package P_20250510;

import java.io.*;
import java.util.*;

// 에디터 10 - 10:27
public class S2_1406 {
    static String sentence;
    static int N;
    static Stack<Character> left = new Stack<>();
    static Stack<Character> right = new Stack<>();

    public static void main(String[] args) throws IOException {
        inputAndRun();
        printResult();
    }

    public static void inputAndRun() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sentence = br.readLine();
        N = Integer.parseInt(br.readLine());

        for (char c : sentence.toCharArray()) {
            left.push(c);
        }

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            switch (command.charAt(0)) {
                case 'L':
                    if (!left.isEmpty())
                        right.push(left.pop());
                    break;
                case 'D':
                    if (!right.isEmpty())
                        left.push(right.pop());
                    break;
                case 'B':
                    if (!left.isEmpty())
                        left.pop();
                    break;
                case 'P':
                    left.push(command.charAt(2));
                    break;
            }
        }

        br.close();

    }

    public static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(char c : left) sb.append(c);  // 왼쪽 문자열 |
        while(! right.isEmpty()) sb.append(right.pop()); // | 오른쪽 문자열 -> 가장 최근에 넣은 문자들을 pop해서 붙여야 문자열 정렬?이 바름
        System.out.println(sb.toString());
    }


}


