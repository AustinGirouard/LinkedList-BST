package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class BinarySearchTreeTest{

  BufferedReader reader;
  BinarySearchTreeBuilder dbTree;

  public static void main(String[] args)
  {
    BinarySearchTreeTest tree = new BinarySearchTreeTest("datafile.txt");
    tree.dbTree.print();                       // Prints titles of all elements in the BST sorted by keyword.
    tree.dbTree.delete("database");            // Deletes the "database" node from the BST
    tree.dbTree.delete("image-retrieval");     // Deletes the "image-retrieval" node from the BST
    tree.dbTree.print();
  }


  BinarySearchTreeTest(String filename)
  {
    // Read datafile.txt into datastructure
    try
    {
      dbTree = new BinarySearchTreeBuilder();
      reader = new BufferedReader(new FileReader(filename));
      
      FileData fd;

      while ((fd = readNextRecord()) != null)
      {
        for (int i=0; i<fd.keywords.length; i++)
        {
          dbTree.insert(fd.keywords[i], fd);
        }
      }
    }
    catch (IOException e) 
    {
      e.printStackTrace();
    }
    finally
    {
      try 
      {
        if (reader!=null) reader.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  // Reads the next record from filedata.txt
  public FileData readNextRecord()
  {
        if(reader == null)
        {
			System.out.println("Error: You must open the file first.");
			return null;
		}
        else
        {
			FileData readData;
            try
            {
				String data = reader.readLine();
                if(data == null) 
                {
                    return null;
                }
                // FileData(           int id,                 String title,      String author,     int keywordCount)
				readData= new FileData(Integer.parseInt(data), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()));
        /*
        4
        database
        image-retrieval
        content-based
        medical

        */
                for (int i = 0; i < readData.keywords.length; i++)
                {
					readData.keywords[i] = reader.readLine();
				}
				String space = reader.readLine();
				if((space != null) && (!space.trim().equals(""))){
					System.out.println("Error in file format");
					return null;
				}
			}
            catch(NumberFormatException e)
            {
				System.out.println("Error Number Expected! ");
				return null;
			}
            catch(Exception e)
            {
				System.out.println("Fatal Error: "+e);
				return null;
			}
			return readData;
		}
	}
}