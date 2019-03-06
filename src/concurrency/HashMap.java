package concurrency;

public class HashMap 
{
	
    private int BUCKET_ARRAY_SIZE = 256;
    private Node[] bucketArray = new Node[BUCKET_ARRAY_SIZE];
    public HashMap(){}
    public HashMap(int initialSize)
    {
        this.BUCKET_ARRAY_SIZE = initialSize;
    }
    public void put(String key, String value)
    {
        int index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        Node node = new Node(key,value);
        if(bucketArray[index] == null)
        {
            bucketArray[index] = node;
        }
        else
        { 
            Node current = bucketArray[index];
            while(current != null)
            {
                if(current.key.equals(node.key))
                {
                    current.value=node.value;
                    return;
                }
                current = current.next;
            }
            current= node;
        }
    }
    public String get(String key)
    {
        int index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);
        Node current = bucketArray[index];
        while(current != null)
        {
            if(current.key.equals(key))
            {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
   
    class Node
    {
        private String key;
        private String value;
        private Node next;
        public Node(){}
        public Node(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
}
