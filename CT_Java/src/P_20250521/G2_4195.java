package P_20250521;

import java.io.*;
import java.util.*;

public class G2_4195 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int T;
	static int networkCount;

	static HashMap<String, Integer> nameMap;
	static List<Integer> networkParent;
	static List<Integer> networkSize;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			inputAndRun();
		}
	}

	public static void inputAndRun() throws IOException {
		networkCount = Integer.parseInt(br.readLine());

		nameMap = new HashMap<>();
		
		networkParent=new ArrayList<>(); 
		networkSize = new ArrayList<>(); 

		for (int i = 0; i < networkCount; i++) {
			st = new StringTokenizer(br.readLine());

			String f1 = st.nextToken();
			String f2 = st.nextToken();

			if (!nameMap.containsKey(f1)) {
				int f1_index = nameMap.size();
				nameMap.put(f1, f1_index);
				networkParent.add(f1_index);
				networkSize.add(1);

			}

			if (!nameMap.containsKey(f2)) {
				int f2_index = nameMap.size();
				nameMap.put(f2, f2_index);
				networkParent.add(f2_index);
				networkSize.add(1);
			}

			int f1ParentIndex = find(nameMap.get(f1));
			int f2ParentIndex = find(nameMap.get(f2)); 
			
			if(f1ParentIndex != f2ParentIndex) {
				networkParent.set(f2ParentIndex, f1ParentIndex); 
				networkSize.set(f1ParentIndex, networkSize.get(f1ParentIndex) 
						+ networkSize.get(f2ParentIndex)); 
			}
			
			System.out.println(networkSize.get(f1ParentIndex)); 

		}
	}

	static int find(int f_index) {
		if (f_index != networkParent.get(f_index)) {
			// 부모가 따로 존재한다면
			networkParent.set(f_index, find(networkParent.get(f_index)));
		}

		return networkParent.get(f_index);

	}

}
