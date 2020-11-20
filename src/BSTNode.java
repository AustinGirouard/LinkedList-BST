package src;

class BSTNode 
{
    String keyword;
    Record record;
    BSTNode left;
    BSTNode right;
  
    BSTNode(String k, Record r)
    {
      keyword = k;
      record = r;
      left = null;
      right = null;
    }
  }
  