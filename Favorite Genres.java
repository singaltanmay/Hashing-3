// "static void main" must be defined in a public class.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Approach is simple: initialize a mapping of songsToGenre.
// Then, using our mapping, we iterate through each song (we don't know if songs have multiple genres, so we handle that here), and count up the genres while keeping a 'maximum count'.
// After that, we find out all of the songs that are equal to our maximum mapping. 

class Solution {
	public Map<String, List<String>> favoriteGenre (Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
		
        
	Map<String, String> songGenres = new HashMap<>();

	for(Map.Entry<String, List<String>> entry : genreMap.entrySet()){
		entry.getValue().forEach(song -> {
			songGenres.put(song, entry.getKey());
		});
	}

	Map<String, List<String>> result = new HashMap<>();

	for(Map.Entry<String, List<String>> entry : userMap.entrySet()){
		String user = entry.getKey();
		List<String> userSongs = entry.getValue();

		Map<String, Integer> genreCount = new TreeMap<>(Collections.reverseOrder());
		
		for(String userSong: userSongs){
			String genre = songGenres.get(userSong);
			genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
		}

		int prev = Integer.MIN_VALUE;
		for(Map.Entry<String, Integer> genreCounts : genreCount.entrySet()){
			String genre = genreCounts.getKey();
			int count = genreCounts.getValue();
			if(count < prev) break;
			List<String> userFavGenres = result.getOrDefault(user, new LinkedList<>());
			userFavGenres.add(genre);
            result.put(user, userFavGenres);
			prev = count;
		}
			
	}
	
	return result;

    }
}


public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> userMap = new HashMap<>();
		List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
		List<String> list2 = Arrays.asList("song5", "song6", "song7");
		userMap.put("David", list1);
		userMap.put("Emma", list2);
		
		Map<String, List<String>> genreMap = new HashMap<>();
		List<String> list3 = Arrays.asList("song1", "song3");
		List<String> list4 = Arrays.asList("song7");
		List<String> list5 = Arrays.asList("song2", "song4");
		List<String> list6 = Arrays.asList("song5", "song6");
		List<String> list7 = Arrays.asList("song8", "song9");
		genreMap.put("Rock", list3);
		genreMap.put("Dubstep", list4);
		genreMap.put("Techno", list5);
		genreMap.put("Pop", list6);
		genreMap.put("Jazz", list7);
        
        /*Map<String, List<String>> userMap = new HashMap<>();
		List<String> list1 = Arrays.asList("song1", "song2");
		List<String> list2 = Arrays.asList("song3", "song4");
		userMap.put("David", list1);
		userMap.put("Emma", list2);
		
		Map<String, List<String>> genreMap = new HashMap<>();*/
        
        System.out.println(new Solution().favoriteGenre(userMap, genreMap));
    }
}
