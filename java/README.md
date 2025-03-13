# 자료구조 in java.util
`import java.util.*`

<br/>

## 스택
- 삽입 : push
- 꺼내기 : pop
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);   // Stack의 push()
stack.push(2);
System.out.println(stack.pop()); // Stack의 pop() (출력: 2)
```

<br/>

## 큐
- 삽입 : offer
- 꺼내기 : poll
- 꺼내지 않고 조회 : peek
```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1);  // Queue의 enqueue (add()보다 offer() 권장)
queue.offer(2);
System.out.println(queue.poll()); // Queue의 dequeue (출력: 1)
```

<br/>

## 우선순위 큐 (힙)
- 삽입 : offer
- 꺼내기 : poll
- 꺼내지 앖고 조회 : peek
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 기본: 최소 힙
minHeap.offer(5);
minHeap.offer(2);
minHeap.offer(8);
System.out.println(minHeap.poll()); // 출력: 2 (가장 작은 값)

PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
maxHeap.offer(5);
maxHeap.offer(2);
maxHeap.offer(8);
System.out.println(maxHeap.poll()); // 출력: 8 (가장 큰 값)
```

<br/>

## Set
- 존재여부 : contains
- 삽입 : add

<br/><br/>

# Stream
`import java.util.stream.*`

<br/><br/>

# String
- startsWith  
  `boolean result = "Hello World".startsWith("Hello W");`
