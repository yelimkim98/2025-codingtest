import java.util.*;
import java.util.stream.*;

class Solution {
    
    static int boardLength;
    
    public int solution(int[][] game_board, int[][] table) {
        boardLength=game_board.length;
        
        // bfs로 game_board shapes 만들기
        Map<ShapeKey, List<Shape>> gameBoardShapesMap = findShapes(game_board, 0);
        
        // bfs로 table shapes 만들기
        Map<ShapeKey, List<Shape>> tableShapesMap = findShapes(table, 1);
        
        
        // 점 갯수가 같고, arr 가로 세로 length가 같은 애들과 비교 -> 같은거 발견하면 칸 갯수만큼 answer 증가
        Set<ShapeKey> tableShapesKey = tableShapesMap.keySet();
        int answer=0;
        
        for (ShapeKey key : tableShapesKey) {
            // 칸 갯수랑 너비 같은애들끼리 비교
            List<Shape> tableShapes = tableShapesMap.get(key);
            List<Shape> gameBoardShapes = gameBoardShapesMap.getOrDefault(key, Collections.emptyList());
            
            for (int tableId=0; tableId<tableShapes.size(); tableId++) {
                for (int gameBoardId=0; gameBoardId<gameBoardShapes.size(); gameBoardId++) {
                    Shape tableShape = tableShapes.get(tableId);
                    Shape gameBoardShape = gameBoardShapes.get(gameBoardId);
                    
                    if (tableShape.used || gameBoardShape.used) {
                        continue;
                    }
                    if (Shape.same(tableShape, gameBoardShape)) {
                        tableShape.used=true;
                        gameBoardShape.used=true;
                        answer += tableShape.pointCnt;

                    }
                }
            }
            
        }
        return answer;
    }
    
    Map<ShapeKey, List<Shape>> findShapes(int[][] board, int checkValue) {
        Map<ShapeKey, List<Shape>> map = new HashMap<>();
        
        boolean[][] visited = new boolean[board.length][board.length];
        
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                
                if (board[i][j] == checkValue && !visited[i][j]) {
                    // System.out.printf("start at (%d,%d)\n", i, j);
                    List<Point> results = bfs(new Point(i, j), checkValue, board, visited);
                    
                    Shape shape = Shape.of(results);
                    ShapeKey key = new ShapeKey(shape.pointCnt, shape.getArrLength());
                    
//                     System.out.println(shape);
//                     System.out.println();
                    
                    List<Shape> shapes = map.getOrDefault(key, new ArrayList<>());
                    shapes.add(shape);
                    map.put(key, shapes);
                }
            }
            
        }
        return map;
    }
    List<Point> bfs(Point start, int checkValue, int[][] board, boolean[][] visited) {
        if (board[start.row][start.col] != checkValue) return Collections.emptyList();
        
        List<Point> results = new ArrayList<>();
        results.add(start);
        
        visited[start.row][start.col]=true;
        Deque<Point> q = new ArrayDeque<>();
        
        q.offer(start);
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            List<Point> adjs = getAdjs(p, checkValue, board, visited);
            for (Point adj : adjs) {
                q.offer(adj);
                results.add(adj);
                visited[adj.row][adj.col] = true;
            }
        }
        return results;
    }
    List<Point> getAdjs(Point p, int checkValue, int[][] board, boolean[][] visited) {
        return p.getAdjs().stream()
            .filter(adj -> board[adj.row][adj.col]==checkValue && !visited[adj.row][adj.col])
            .collect(Collectors.toList());
    }
    
    static class Point {
        int row, col;
        public Point(int row, int col) {
            this.row=row;
            this.col=col;
        }
        public List<Point> getAdjs() {
            List<Point> results = new ArrayList<>();
            
            // 상
            Point p = new Point(this.row-1, this.col);
            if (p.isValid()) results.add(p);
            
            // 하
            p = new Point(this.row+1, this.col);
            if (p.isValid()) results.add(p);
            
            // 좌
            p = new Point(this.row, this.col-1);
            if (p.isValid()) results.add(p);
            
            // 우
            p = new Point(this.row, this.col+1);
            if (p.isValid()) results.add(p);
            
            return results;
        }
        boolean isValid() {
            return 0 <= row && row < boardLength
                && 0 <= col && col < boardLength;
        }
        @Override
        public String toString() {
            return String.format("(%d,%d)", row, col);
        }
    }

    static class Shape {
        int[][] arr;
        int pointCnt=0;
        boolean used=false;
        
        public static boolean same(Shape s1, Shape s2) {
            List<Shape> turns = s2.turns();
            // System.out.println("#### s1");
            // System.out.println(s1);
            // System.out.println("#### s2");
            
            // 왼쪽 위로 붙이기
            for (Shape s : turns) {
                for (int i=0; i<s.getArrLength(); i++) {
                    s.leftUp();
                }
                // System.out.println(s);
                // System.out.println();
                if (s1.equals(s)) return true;
            }
            return false;
        }
        
        public boolean equals(Shape s) {
            if (this.getArrLength() != s.getArrLength()) return false;
            
            for (int i=0; i<arr.length; i++) {
                for (int j=0; j<arr.length; j++) {
                    if (this.arr[i][j] != s.arr[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        public List<Shape> turns() {
            // 0도, 90도, 180도, 270도
            List<Shape> results = new ArrayList<>();
            
            // 0도
            Shape s = new Shape(this.arr, this.pointCnt);
            results.add(s);
            
            // 90도
            s = s.turn90();
            results.add(s);
            
            // 180도
            s = s.turn90();
            results.add(s);
            
            // 270도
            s = s.turn90();
            results.add(s);
            
            return results;
        }
        
        public Shape turn90() {
            int[][] newArr = new int[arr.length][arr.length];
            
            for (int row=0, newCol=arr.length-1; row<arr.length; row++, newCol--) {
                for (int col=0, newRow=0; col<arr.length; col++, newRow++) {
                    newArr[newRow][newCol] = arr[row][col];
                }
            }
            return new Shape(newArr, this.pointCnt);
        }
        
        public void leftUp() {
            // col=0 이 전부 비어있거나 row=0 이 전부 비어있으면 땡겨주기
            // col=0
            boolean needToRemoveCol0=true;
            for (int row=0; row<arr.length; row++) {
                if (arr[row][0] != 0) {
                    needToRemoveCol0=false;
                    break;
                }
            }
            if (needToRemoveCol0) {
                for (int row=0; row<arr.length; row++) {
                    for (int col=1; col<arr.length; col++) {
                        arr[row][col-1] = arr[row][col];
                    }
                }
                for (int row=0; row<arr.length; row++) {
                    arr[row][arr.length-1]=0;
                }
            }
            
            // row=0
            boolean needToRemoveRow0=true;
            for (int col=0; col<arr.length; col++) {
                if (arr[0][col] != 0) {
                    needToRemoveRow0=false;
                    break;
                }
            }
            if (needToRemoveRow0) {
                for (int row=1; row<arr.length; row++) {
                    for (int col=0; col<arr.length; col++) {
                        arr[row-1][col] = arr[row][col];
                    }
                }
                for (int col=0; col<arr.length; col++) {
                    arr[arr.length-1][col]=0;
                }
            }
        }

        public static Shape of(List<Point> points) {
            // try {
                // System.out.println(points);
                
                Collections.sort(points, (p1, p2) -> Integer.compare(p1.row, p2.row));
                int minRow = points.get(0).row;
                int maxRow = points.get(points.size()-1).row;

                Collections.sort(points, (p1, p2) -> Integer.compare(p1.col, p2.col));
                int minCol = points.get(0).col;
                int maxCol = points.get(points.size()-1).col;
                
                int arrLength = Math.max(maxRow-minRow+1, maxCol-minCol+1);
                // System.out.printf("minRow%d maxRow%d minCol%d maxCol%d arrLen%d\n", 
                //                  minRow, maxRow, minCol, maxCol, arrLength);

                int[][] arr = new int[arrLength][arrLength];

                for (Point p : points) {
                    int row = p.row-minRow;
                    int col = p.col-minCol;
                    arr[row][col]=1;
                }
                return new Shape(arr, points.size());
            
            // } catch (Exception e) {
            //     System.out.println(e.getMessage());
            //     return new Shape(new int[0][0], 0);
            // }
        }

        public Shape(int[][] arr, int pointCnt) {
            this.arr=arr;
            this.pointCnt=pointCnt;
        }

        public int getArrLength() {
            return arr.length;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i=0; i<arr.length; i++) {
                for (int j=0; j<arr.length; j++) {
                    sb.append(arr[i][j] + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    static class ShapeKey {
        int pointCnt;
        int arrLength;

        public ShapeKey(int pointCnt, int arrLength) {
            this.pointCnt=pointCnt;
            this.arrLength=arrLength;
        }
        @Override
        public int hashCode() {
            return Objects.hash(pointCnt, arrLength);
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ShapeKey)) return false;
            ShapeKey sk = (ShapeKey) o;
            return this.pointCnt == sk.pointCnt
                && this.arrLength == sk.arrLength;
        }
    }
}

/*
게임 보드의 모든 칸 셋트 수집
테이블의 모든 칸 셋트 수집

1:1 비교하면서 하나씩 채워넣기

칸 갯수별로 포인트 셋을 수집하면
갯수 같은 애들만 비교할 수 있어서 좀 더 효율적일듯.
*/
