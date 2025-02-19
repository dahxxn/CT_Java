package P_20250219;

import java.util.*;

public class BestAlbum_Lv3 {

	class Solution {
		class MusicSortByPlay implements Comparator<Music> {
			@Override
			public int compare(Music a, Music b) {
				if (a.play > b.play)
					return -1;
				else if (a.play == b.play)
					return 0;
				return 1;
			}
		}

		class Music {
			int id;
			int play;

			public Music(int id, int play) {
				this.id = id;
				this.play = play;
			}

		}

		public int[] solution(String[] genres, int[] plays) {
			int[] answer = {};

			HashMap<String, Integer> genreCount = new HashMap<>();
			HashMap<String, List<Music>> genreMap = new HashMap<>();

			for (int i = 0; i < genres.length; i++) {
				int cnt = genreCount.getOrDefault(genres[i], 0);
				genreCount.put(genres[i], cnt + plays[i]);

				List<Music> playList = genreMap.getOrDefault(genres[i], new ArrayList<>());
				Music music = new Music(i, plays[i]);

				playList.add(music);
				genreMap.put(genres[i], playList);
			}

			List<String> entryList = new ArrayList<>(genreCount.keySet());

			entryList.sort(new Comparator<String>() {
				@Override
				public int compare(String a, String b) {
					if (genreCount.get(a) > genreCount.get(b))
						return -1;
					else if (genreCount.get(a) == genreCount.get(b))
						return 0;
					else
						return 1;
				}
			});

			List<Integer> answerList = new ArrayList<>();
			for (String genre : entryList) {

				List<Music> playList = genreMap.get(genre);
				playList.sort(new MusicSortByPlay());

				int cnt = 0;

				for (Music music : playList) {
					answerList.add(music.id);
					cnt++;
					if (cnt == 2)
						break;
				}
			}

			answer = new int[answerList.size()];
			for (int i = 0; i < answer.length; i++) {
				answer[i] = answerList.get(i);
			}

			return answer;
		}
	}
}
