package src;

public class BinarySearchTreeBuilder 
{
    BSTNode root;

    public BinarySearchTreeBuilder()
    {
        this.root = null;
    }


    // Checks if the keyword is in the BST
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


    // Inserts a record into the BST
    boolean insert(String keyword, FileData fd)
    {
        Record record = new Record(fd.id, fd.title, fd.author, null);
        
        // If the tree is empty, insert new record at the root
        if(this.root == null)
        {
            this.root = new BSTNode(keyword, record);
            return true;
        }

        else
        {
            BSTNode current = this.root;
            BSTNode parent = current;

            // Searches the BST until the keyword is found or until the bottom of
            // the BST is reached
            while(current != null)
            {
                if(current.keyword.compareTo(keyword) > 0)
                {
                    parent = current;
                    current = current.left;
                }

                else if(current.keyword.compareTo(keyword) < 0)
                {
                    parent = current;
                    current = current.right;
                }
                
                // If the keyword has been found, add record to the LinkedList
                else
                {
                    record.next = current.record;
                    current.record = record;
                    return true;
                }
            }

            // If the BST does not contain the keyword, create new BSTNode
            BSTNode newNode = new BSTNode(keyword, record);

            if(parent.keyword.compareTo(keyword) > 0)
                parent.left = newNode;
            else
                parent.right = newNode;
            
            return true;
        }
    }
}