import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static Set<String> company = new HashSet<>();
	static List<String> result = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		input();
		process();
		output();
	}

	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String status = st.nextToken();

			if (status.equals("enter")) {
				company.add(name);
			} else {
				company.remove(name);
			}
		}
	}

	static void process() {
		result.addAll(company);
		Collections.sort(result, Collections.reverseOrder());
	}

	static void output() {
		for (String name : result) {
			System.out.println(name);
		}
	}
}
