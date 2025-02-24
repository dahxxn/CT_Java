package P_20250224;

import java.util.*;

public class BridgeTruck_Lv2 {

	class Solution {
		public int solution(int bridge_length, int weight, int[] truck_weights) {
			int answer = 0;

			Queue<Integer> bridge = new LinkedList<>();
			int remainWeight = weight;

			for (int i = 0; i < bridge_length; i++) {
				bridge.offer(0);
			}

			int totalTrucksCount = truck_weights.length;
			int i = 0;
			int time = 0;
			while (true) {
				remainWeight += bridge.poll();

				if (i < totalTrucksCount && truck_weights[i] <= remainWeight) {
					bridge.offer(truck_weights[i]);
					remainWeight -= truck_weights[i];
					i++;
				} else {
					bridge.offer(0);
				}

				time++;
				if (i == totalTrucksCount && remainWeight == weight)
					break;
			}

			answer = time;

			return answer;
		}
	}
}
