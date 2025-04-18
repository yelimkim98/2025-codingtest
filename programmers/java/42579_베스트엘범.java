import java.util.*;
import java.util.stream.*;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            Genre genre = map.getOrDefault(genres[i], new Genre());
            genre.addMusic(i, plays[i]);
            map.put(genres[i], genre);
        }
        List<Genre> genreList = new ArrayList<Genre>(map.values());
        Collections.sort(genreList);
        
        List<Integer> resultList = new ArrayList<>();
        
        for (Genre g : genreList) {
            resultList.addAll(g.getBestMusicIds());
        }
        int[] answer = new int[resultList.size()];
        
        for (int i=0; i<resultList.size(); i++){
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}

class Genre implements Comparable<Genre> {
    
    public int totalPlay = 0;
    public BestMusics bestMusics = new BestMusics();
    
    public void addMusic(int id, int play) {
        this.totalPlay += play;
        bestMusics.music(new Music(id, play));
    }
    
    public List<Integer> getBestMusicIds() {
        return this.bestMusics.getIds();
    }
    
    // 내림차순
    @Override
    public int compareTo(Genre g) {
        return Integer.compare(g.totalPlay, this.totalPlay);
    }
}

class BestMusics {
    
    private List<Music> values = new ArrayList<>();
    
    public List<Integer> getIds() {
        return values.stream()
            .map(value -> value.id)
            .collect(Collectors.toList());
    }
    
    public void music(Music m) {
        if (values.isEmpty()) {
            values.add(m);
            return;
        }
        Music first = values.get(0);
        if (values.size() == 1) {
            if (m.play > first.play) {
                values.add(0, m);  // 1위 등록
                return;
            }
            values.add(m);  // 2위 등록
            return;
        }
        if (values.size() == 2) {
            if (m.play > first.play) {
                values.add(0, m);  // 1위 등록
                values.remove(2);  // 3위 제거
                return;
            }
            Music second = values.get(1);
            if (m.play > second.play) {
                values.add(1, m);  // 2위 등록
                values.remove(2);  // 3위 제거
            }
        }
    }
}

class Music {
    public int id;
    public int play;
    
    public Music(int id, int play) {
        this.id = id;
        this.play = play;
    }
}
