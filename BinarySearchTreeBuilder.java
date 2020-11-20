public class BinarySearchTreeBuilder 
{
    BSTNode root;

    public BinarySearchTreeBuilder()
    {
        this.root = null;
    }

    public boolean contains(String keyword)
    {
        BSTNode current = root;
        while(current != null)
        {
            if(current.keyword.compareTo(keyword) < 0)
                current = current.left;

            else if(current.keyword.compareTo(keyword) > 0)
                current = current.right;
            
            else
                return true;
        }
        return false;
    }


    void insert(String keyword, FileData fd)
    {
        Record record = new Record(fd.id, fd.title, fd.author, null);
        if(!this.contains(keyword))
        {
            insert(keyword, record);
        }
    }

    boolean insert(String keyword, Record record)
    {
        return false;
    }
    
}