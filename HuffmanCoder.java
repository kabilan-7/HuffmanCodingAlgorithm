package algorithm;

import java.util.*;

public class HuffmanCoder {
    HashMap<Character,String> encoder;
    HashMap<String,Character> decoder;

    private class Node implements Comparable<Node>{
        Character data;
        int cost;
        Node left;
        Node right;

        public Node(Character data,int cost){
            this.data=data;
            this.cost=cost;
            this.left=null;
            this.right=null;
        }


        @Override
        public int compareTo(Node other) {
            return this.cost-other.cost;
        }
    }
    public HuffmanCoder(String feeder) throws Exception{
         HashMap<Character,Integer> fmap=new HashMap<>();
         for(int i=0;i<feeder.length();i++){
             fmap.put(feeder.charAt(i),fmap.getOrDefault(feeder.charAt(i),0)+1);
         }
         PriorityQueue<Node> minHeap=new PriorityQueue<>();
         Set<Map.Entry<Character,Integer>> entrySet=fmap.entrySet();
         for(Map.Entry<Character,Integer> entry:entrySet){
             Node node=new Node(entry.getKey(),entry.getValue());
             minHeap.offer(node);
         }
         while(minHeap.size()!=1){
             Node first=minHeap.poll();
             Node second=minHeap.poll();

             Node newNode=new Node('\0',first.cost+second.cost);
             newNode.left=first;
             newNode.right=second;
             minHeap.offer(newNode);
         }
         Node ft=minHeap.poll(); //finaltree
         this.encoder=new HashMap<>();
         this.decoder=new HashMap<>();
         this.initEncoderDecoder(ft,"");

    }
    private void initEncoderDecoder(Node node,String output){
        if(node==null) return;
        if(node.left==null && node.right==null){
            this.encoder.put(node.data,output);
            this.decoder.put(output,node.data);
        }
        initEncoderDecoder(node.left,output+"0");
        initEncoderDecoder(node.right,output+"1");
    }
    public String encode(String source){
         StringBuilder ans=new StringBuilder();
         for(int i=0;i<source.length();i++){
             ans.append(encoder.get(source.charAt(i)));
         }
         return ans.toString();
    }
    public String decode(String codedString){
        String key= "";
        StringBuilder ans= new StringBuilder();
        for(int i=0;i<codedString.length();i++){
            key=key+codedString.charAt(i);
            if(decoder.containsKey(key)){
                ans.append(decoder.get(key));
                key="";
            }
        }
        return ans.toString();
    }
}
