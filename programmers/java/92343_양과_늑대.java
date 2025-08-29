import java.util.*;
import java.util.stream.*;

class Node {
    int id;
    int animal;  // 양 0 늑대 1
    Node parent;
    List<Node> children = new ArrayList<>();
    
    public Node(int id, int animal) {
        this.id=id;
        this.animal=animal;
    }
    public void setParent(Node parent) {
        this.parent=parent;
    }
    public void addChild(Node child) {
        this.children.add(child);
    }
    public boolean isSheep() {
        return this.animal == 0;
    }
    @Override
    public String toString() {
        String animal = isSheep() ? "Sheep" : "Woolf";
        return String.format("id=%d %s", id, animal);
    }
}

class Solution {
    
    Node[] nodes;
    
    public int solution(int[] info, int[][] edges) {
        nodes = new Node[info.length];
        for (int i=0; i<nodes.length; i++) {
            nodes[i]=new Node(i, info[i]);
        }
        for (int[] edge : edges) {
            int parentId=edge[0];
            int childId=edge[1];
            nodes[parentId].addChild(nodes[childId]);
            nodes[childId].setParent(nodes[parentId]);
        }
        RecursiveResult result = recursive(1, 0, nodes[0].children);
        return result.sheepCnt;
    }
    
    
    RecursiveResult recursive(int beforeSheepCnt, int beforeWoolfCnt, List<Node> nextNodes) {
        /*
        nextNodes에 양이 있다면, 양으로 가는 길만 테스트해서 양을 가장 많이 데려오는 경우를 채택한다.
        nextNodes에 늑대만 있으면, 각 경우들을 다 수행해봐서 양을 가장 많이 데려오는 경우를 채택한다.
        */
        List<Node> nextSheepNodes = nextNodes.stream()
            .filter(node -> node.isSheep())
            .collect(Collectors.toList());
        
        // 갈 수 있는 경로로 갔을 때 
        PriorityQueue<RecursiveResult> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(new RecursiveResult(beforeSheepCnt, beforeWoolfCnt));
        
        if (nextSheepNodes.size() > 0) {
            // 양으로 가는 길만 테스트해서, 양을 가장 많이 데려오는 경우를 채택
            for (Node nextSheep : nextSheepNodes) {
                // nextSheep으로 가는 경우 테스트
                int nextBeforeSheepCnt = beforeSheepCnt+1;
                int nextBeforeWoolfCnt = beforeWoolfCnt;
                
                List<Node> nextNextNodes = new ArrayList<>();
                for (Node nextNode : nextNodes) {
                    if (nextNode.id != nextSheep.id) {
                        nextNextNodes.add(nextNode);
                    }
                }
                nextNextNodes.addAll(nextSheep.children);
                
                RecursiveResult result = recursive(nextBeforeSheepCnt, nextBeforeWoolfCnt, nextNextNodes);
                // System.out.printf("%d로 가면 양%d 늑대%d\n", nextSheep.id, result.sheepCnt, result.woolfCnt);
                pq.add(result);
            }
            
        } else {
            // 모든 nextNodes 각각 테스트해서, 양을 가장 많이 데려오는 경우 채택
            for (Node nextWoolf : nextNodes) {
                int nextBeforeSheepCnt=beforeSheepCnt;
                int nextBeforeWoolfCnt=beforeWoolfCnt+1;
                
                if (nextBeforeSheepCnt == nextBeforeWoolfCnt) {
                    pq.add(new RecursiveResult(0, nextBeforeWoolfCnt));
                    continue;
                }
                
                List<Node> nextNextNodes = new ArrayList<>();
                for (Node nextNode : nextNodes) {
                    if (nextNode.id != nextWoolf.id) {
                        nextNextNodes.add(nextNode);
                    }
                }
                nextNextNodes.addAll(nextWoolf.children);
                
                RecursiveResult result = recursive(nextBeforeSheepCnt, nextBeforeWoolfCnt, nextNextNodes);
                // System.out.printf("%d로 가면 양%d 늑대%d\n", nextWoolf.id, result.sheepCnt, result.woolfCnt);
                pq.add(result);
            }
        }
        return pq.poll();
    }
    
    static class RecursiveResult implements Comparable<RecursiveResult> {
        int sheepCnt;
        int woolfCnt;
        public RecursiveResult(int s, int w) {
            sheepCnt=s;
            woolfCnt=w;
        }
        @Override
        public int compareTo(RecursiveResult rr) {
            return Integer.compare(this.sheepCnt, rr.sheepCnt);
        }
    }
}
