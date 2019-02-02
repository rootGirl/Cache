import java.util.*;
public class Cache {

        class Node{
            int value = -1;
            public Node(int value){
                this.value=value;
            }
            Node right = null;
            Node left = null;
        }
        int count=0;
        Node head =null;
        Node tail = null;
        HashMap<Integer,Node> map = new HashMap();

        public void put(int value){
            Node newNode = new Node(value);
            if(head==null){
                head=newNode;
                count++;
                map.put(value,newNode);
            }else if(head.right==null){
                head.right=newNode;
                tail=newNode;
                tail.left=head;
                count++;
                map.put(value,newNode);
            }else if(count>3){
                map.remove(head.value);
                Node next = head.right;
                head.right=null;
                next.left=null;
                head=next;
                tail.right=newNode;
                newNode.left=tail;
                tail=newNode;
            }else{
                Node previous=tail;
                tail.right=newNode;
                tail=newNode;
                tail.left=previous;
                count++;
                map.put(value,newNode);
            }
        }

        public int get(int value){
            if(map.containsKey(value)){
                if(map.get(value)==head){
                    Node next = map.get(value).right;
                    next.left=null;
                    head=next;
                    map.get(value).right=null;
                    tail.right=map.get(value);
                    map.get(value).left=tail;
                    tail=map.get(value);
                    return map.get(value).value;
                }
                if(map.get(value)==tail){
                    return map.get(value).value;
                }
                if(map.get(value)!=tail) {
                    Node previous = map.get(value).left;
                    Node next = map.get(value).right;
                    previous.right = next;
                    next.left = previous;
                    tail.right = map.get(value);
                    map.get(value).left = tail;
                    map.get(value).right = null;
                    tail = map.get(value);
                    return map.get(value).value;
                }
            }
            return -1;
        }

        public void printCache(){
            Node temp = head;
            while(temp!=null){
                System.out.println(temp.value);
                temp=temp.right;
            }
        }

        public static void main(String[] args){
            Cache linkedList = new Cache();
            linkedList.put(4);
            //      System.out.println("get:" + linkedList.get(3));
            linkedList.put(3);
            //      System.out.println("get:" + linkedList.get(4));
            linkedList.put(2);
            linkedList.put(1);
            linkedList.put(5);
            System.out.println("get:" + linkedList.get(2));
            //       linkedList.put(6);
            linkedList.printCache();

        }

}
