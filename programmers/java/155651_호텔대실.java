import java.util.*;

class Time implements Comparable<Time> {
    final int h;
    final int m;
    public Time(String str) {
        String[] s = str.split(":");
        this.h = Integer.parseInt(s[0]);
        this.m = Integer.parseInt(s[1]);
    }
    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }
    public Time increase10() {
        int mInc = m+10;
        if (mInc >= 60) {
            return new Time(h+1, mInc-60);
        }
        return new Time(h, mInc);
    }
    @Override
    public int compareTo(Time t) {
        int hCompare = Integer.compare(this.h, t.h);
        if (hCompare != 0) return hCompare;
        return Integer.compare(this.m, t.m);
    }
    
    @Override
    public String toString() {
        return String.format("%02d:%02d", h, m);
    }
}

class Book implements Comparable<Book> {
    Time start;
    Time end;
    public Book(String start, String end) {
        this.start = new Time(start);
        this.end = new Time(end);
    }
    @Override
    public int compareTo(Book b) {
        int compareStart = this.start.compareTo(b.start);
        if (compareStart != 0) return compareStart;
        return this.end.compareTo(b.end);
    }
    @Override
    public String toString() {
        return String.format("start %s end %s", start, end);
    }
}

class Room implements Comparable<Room> {
    Time end; // 직전 사용자 퇴실시간 + 10
    
    public Room(Book b) {
        // end=b.end+10;
        this.end = b.end.increase10();
    }
    public boolean canUse(Book b) {
        // b.start > this.end
        return b.start.compareTo(this.end) >= 0;
    }
    public void use(Book b) {
        if (!canUse(b)) {
            throw new IllegalStateException("사용불가");
        }
        end = b.end.increase10();
    }
    @Override
    public int compareTo(Room r) {
        return this.end.compareTo(r.end);
    }
    @Override
    public String toString() {
        return String.format("end=%s", end);
    }
}

class Solution {
    public int solution(String[][] book_time) {
        /*
        1. 입실시간 오름차순 정렬
        2. 제일 빠른거 room1 시작 << 퇴실시간 빠른 순서로 방 정렬
        3. i번째 입실자
           기존 방에 들어갈 수 있으면 해당 방 퇴실시간 업데이트
           기존 방에 들어갈 수 없으면 room 추가
        */
        // List<Book> books = new ArrayList<>();
        
        // Collections.sort(books);
        
        
        PriorityQueue<Book> books = new PriorityQueue<>();
        
        for (String[] arr : book_time) {
            Book b = new Book(arr[0], arr[1]);
            books.offer(b);
            // System.out.println(b);
        }
        
        // System.out.println(books);
        
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        
        // 첫번째 입실
        Book b = books.poll();
        rooms.offer(new Room(b));
        
        // n번째 입실
        while (!books.isEmpty()) {
            b = books.poll();
            // System.out.println("[room " + rooms.peek() + "] [book " + b + "]");
            
            if (rooms.peek().canUse(b)) {
                // 해당 방에 입실
                Room r = rooms.poll();
                r.use(b);
                rooms.offer(r);
            } else {
                // 방 추가
                rooms.offer(new Room(b));
            }
        }
        return rooms.size();
    }
}
