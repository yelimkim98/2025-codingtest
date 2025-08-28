import java.util.*;

class Node {
    String name;
    int amount=0;
    Node parent=null;   // 추천인 없으면 null
    List<Node> children = new ArrayList<>();
    
    public Node(String name, Node parent) {
        this.name=name;
        this.parent=parent;
    }
    public void addChild(Node n) {
        this.children.add(n);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (parent != null) sb.append(String.format(" parent(%s)", parent.name));
        if (!children.isEmpty()) {
            sb.append(" children ");
            for (Node child : children) {
                sb.append(child.name + " ");
            }
        }
        return sb.toString();
    }
}

class Solution {
    
    Node[] nodes;
    // key 판매원이름 value 판매원id
    Map<String, Integer> nameIdMap = new HashMap<>();
    
    public int[] solution(
        String[] enroll,    // 판매원 이름
        String[] referral,  // referral[i] : enroll[i]를 데려온 판매원이름 
        String[] seller,    // seller[j] : amount[j] 판매건을 판 판매원 이름
        int[] amount        // 칫솔 판매건 별 판매갯수
    ) {
        nodes = new Node[enroll.length];
        Node center = new Node("minho", null);
        
        for (int id=0; id<enroll.length; id++) {
            // 판매원 이름 enroll[i]
            // 얘를 데려온 판매원 이름 referral[i]
            
            Node parent = center;
            
            if (!"-".equals(referral[id])) {    // 부모노드 따로 있음
                int parentId = nameIdMap.get(referral[id]);
                parent = nodes[parentId];
            }
            Node node = new Node(enroll[id], parent);
            
            nodes[id]=node;
            if (parent != null ) {
                parent.addChild(node);
            }
            nameIdMap.put(enroll[id], id);
        }
        // System.out.println(nodes[0]);
        // if (nameIdMap.containsKey("edward")){
        //     Node n = nodes[nameIdMap.get("edward")];
        //     System.out.println(n);
        // }
        // settlement(amount[0]*100, nodes[nameIdMap.get("young")]);
        for (int i=0; i<amount.length; i++) {
            int salesAmount = amount[i]*100;
            Node sellerNode = nodes[nameIdMap.get(seller[i])];
            
            settlement(salesAmount, sellerNode);
            // System.out.println();
        }
        int[] ans = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
            ans[i] = nodes[i].amount;
        }
        return ans;
    }
    
    public void settlement(int salesAmount, Node seller) {
        if (seller.parent == null) {
            seller.amount += salesAmount;
            // System.out.printf("%s %d\n", seller.name, seller.amount);
            return;
        }
        
        int commission = (int) (salesAmount * 0.1);
        int settlementAmount = salesAmount - commission;
        
        seller.amount += settlementAmount;
        
        // System.out.printf("%s %d\n", seller.name, seller.amount);
        
        if (commission > 0) {
            settlement(commission, seller.parent);
        }
    }
}
