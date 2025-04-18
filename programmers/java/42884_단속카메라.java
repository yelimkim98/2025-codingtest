import java.util.*;
import java.util.stream.*;

class Route implements Comparable<Route>{
    int start;
    int end;
    
    public Route(int start, int end) {
        if (start > end) {
            this.start = end;
            this.end = start;
        } else {
            this.start = start;
            this.end = end;
        }
    }
    
    @Override
    public int compareTo(Route r) {
        return Integer.compare(this.end, r.end);
    }
}

class Solution {
    public int solution(int[][] routes) {
        List<Route> routeList = Arrays.stream(routes)
            .map(route -> new Route(route[0], route[1]))
            .sorted()
            .collect(Collectors.toList());
        
        int cameraPosition = -30_001;
        int cameraCnt = 0;
        
        for (Route route : routeList) {
            if (cameraPosition >= route.start) {
                continue;
            }
            cameraPosition = route.end;
            cameraCnt++;
        }
        return cameraCnt;
    }
}
