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
            if(current.keyword.compareTo(keyword) > 0)
                current = current.left;

            else if(current.keyword.compareTo(keyword) < 0)
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
            BSTNode parent = null;

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

    boolean delete(String keyword)
    {
        if(!contains(keyword))
            return false;
        
        else
        {
            BSTNode current = this.root;
            BSTNode parent = null;

            // Searches the BST until the keyword is found
            while(current.keyword.compareTo(keyword) != 0)
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
            }

            // Once the keyword is found, delete the node (current)

            // CASE 1: The current node does not have a left child
            // Connect parent node to right node of current node
            if(current.left == null)
            {
                // If current is the root of the BST, set new root to the right node of current
                if(parent == null)
                {
                   this.root = current.right; 
                }

                // If current is not the root of BST, connect parent node to right node of current node
                else
                {
                    if(parent.keyword.compareTo(keyword) > 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            }

            // CASE 2: The current node has a left child
            // Locate the rightmost node in the left subtree of current. 
            // Copy the rightmost node to current. 
            // Connect the parent of the rightmost node to the left child of rightmost node.
            else
            {
                BSTNode rightmost = current.left;
                BSTNode parentOfRightmost = current;

                while(rightmost.right != null)
                {
                    parentOfRightmost = rightmost;
                    rightmost = rightmost.right;
                }
                
                // Copy the rightmost node to current
                current.keyword = rightmost.keyword;
                current.record = rightmost.record;

                 // Connect the parent of the rightmost node to the left child of the rightmost node
                 if (parentOfRightmost.right == rightmost) 
                 {
                     parentOfRightmost.right = rightmost.left;
                 }
                 else 
                 {
                     parentOfRightmost.left = rightmost.left;
                 }
                 
            }
            return true;
        }
    }
}
