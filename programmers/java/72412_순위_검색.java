import java.util.*;

class Solution {
    
    Map<Key, List<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        makeMap(info);
        // System.out.println(map);
        
        int[] answer = new int[query.length];
        
        for (int i=0; i<query.length; i++) {
            String[] split = query[i].split(" ");
            Language lang = "-".equals(split[0]) ? null : Language.of(split[0]);
            Occupation occu = "-".equals(split[2]) ? null : Occupation.of(split[2]);
            Career career = "-".equals(split[4]) ? null : Career.of(split[4]);
            Food food = "-".equals(split[6]) ? null : Food.of(split[6]);
            int scoreMoreThan = Integer.parseInt(split[7]);
            
            List<Integer> scores = map.getOrDefault(new Key(lang, occu, career, food), new ArrayList<>());
            
            // System.out.println(scores);
            answer[i] = countMoreThan(scoreMoreThan, scores);
        }
        return answer;
    }
    
    int countMoreThan(int scoreMoreThan, List<Integer> scores) {
        // scoreMoreThan 보다 최초로 크거나 같아지는 index return
        int idx = Collections.binarySearch(scores, scoreMoreThan);
        
        if (idx < 0) {
            idx = Math.abs(idx)-1;
        } else {
            while (idx > 0) {
                int before = scores.get(idx-1);
                if (before < scoreMoreThan) break;
                idx--;
            }
        }
        return scores.size() - idx;
    }
    
    void makeMap(String[] info) {
        Map<Key, PriorityQueue<Integer>> pqMap = new HashMap<>();
        
        for (String s : info) {
            String[] split = s.split(" ");
            Language lang = Language.of(split[0]);
            Occupation occu = Occupation.of(split[1]);
            Career career = Career.of(split[2]);
            Food food = Food.of(split[3]);
            int score = Integer.parseInt(split[4]);
            
            ArrayList<Key> keys = new ArrayList<>();
            keys.add(new Key(null, null, null, null));
            keys.add(new Key(null, null, null, food));
            keys.add(new Key(null, null, career, null));
            keys.add(new Key(null, null, career, food));
            keys.add(new Key(null, occu, null, null));
            keys.add(new Key(null, occu, null, food));
            keys.add(new Key(null, occu, career, null));
            keys.add(new Key(null, occu, career, food));
            keys.add(new Key(lang, null, null, null));
            keys.add(new Key(lang, null, null, food));
            keys.add(new Key(lang, null, career, null));
            keys.add(new Key(lang, null, career, food));
            keys.add(new Key(lang, occu, null, null));
            keys.add(new Key(lang, occu, null, food));
            keys.add(new Key(lang, occu, career, null));
            keys.add(new Key(lang, occu, career, food));
            
            for (Key key : keys) {
                PriorityQueue<Integer> scoreQ = pqMap.getOrDefault(key, new PriorityQueue<>());
                scoreQ.offer(score);
                pqMap.put(key, scoreQ);
            }
        }
        
        for (Map.Entry<Key, PriorityQueue<Integer>> entry : pqMap.entrySet()) {
            List<Integer> l = new ArrayList<>();
            
            PriorityQueue<Integer> pq = entry.getValue();
            while (!pq.isEmpty()) {
                l.add(pq.poll());
            }
            map.put(entry.getKey(), l);
        }
    }
}

enum Language {
    cpp,
    java,
    python;
    
    public static Language of(String s) {
        return Arrays.stream(Language.values())
            .filter(v -> v.name().equals(s))
            .findFirst()
            .orElse(null);
    }
}

enum Occupation {
    backend,
    frontend;
    
    public static Occupation of(String s) {
        return Arrays.stream(Occupation.values())
            .filter(v -> v.name().equals(s))
            .findFirst()
            .orElse(null);
    }
}

enum Career {
    junior,
    senior;
    public static Career of(String s) {
        return Arrays.stream(Career.values())
            .filter(v -> v.name().equals(s))
            .findFirst()
            .orElse(null);
    }
}

enum Food {
    chicken,
    pizza;
    public static Food of(String s) {
        return Arrays.stream(Food.values())
            .filter(v -> v.name().equals(s))
            .findFirst()
            .orElse(null);
    }
}

class Key {
    Language lang;
    Occupation occu;
    Career career;
    Food food;
    
    public Key(Language lang, Occupation occu, Career career, Food food) {
        this.lang=lang;
        this.occu=occu;
        this.career=career;
        this.food=food;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Key)) return false;
        Key k = (Key) o;
        return this.lang == k.lang
            && this.occu == k.occu
            && this.career == k.career
            && this.food == k.food;
    }
    @Override
    public int hashCode() {
        return Objects.hash(lang, occu, career, food);
    }
    @Override
    public String toString() {
        return String.format("(%s, %s, %s, %s)", lang, occu, career, food);
    }
}
