/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



import steamer.BanglaStemmer;
import verb.BanglaVerb;

/**
 *
 * @author KonoNamNai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
	public static BanglaStemmer bs=new BanglaStemmer();
	public static Map root = new HashMap();
	
	public static Set stopword(Set wordset) throws FileNotFoundException, IOException
    {
    	
        /// for the query
        ///////////////////////////////////////////////////////////////////
        File f=new File("C://Users//HereIAm//Documents//NetBeansProjects//MIthun2//Doc//stopwordnew.txt");
        InputStream is = new FileInputStream(f) ; // input carrier 
        BufferedReader br = new BufferedReader( new InputStreamReader(is) ); // buffer carrier
        String s; // string input variable
        StringBuilder sb = new StringBuilder (""); // first choices of words will be stored here 
        String bad="#/,�?“@৳%&*()-=><\n\r?!।"; // this character will be removed
        int x=0; // used to remove the starting unknown character
        while( (s = br.readLine()) !=null )
        {
                 if(x==0)
                            s=s.replace( s.charAt(0), ' '); // removing very first character
                  x++;
                  for(int i=0;i<bad.length();i++)
                            s=s.replace( bad.charAt(i), ' '); // oproyojonio symbol bad jacce
                  sb.append(s);/// eta total unique words mark korar jonno used hbe
        }
        
        String[] words=sb.toString().split(" ");
         Iterator it = wordset.iterator(); // every unique word current sentence e ace kina/ kotobar ace  check kora hobe
         Set del=new HashSet();
        while(it.hasNext())
         {
                   String element = (String) it.next(); // ekta unique word holo element
                   for(String selement:words) // current sentence er ek ekta word holo selement
                            if(same(element,selement))
                                del.add(element);
         }
        ///////////////////////////////////////////////////////
        Iterator dit = del.iterator();
        while(dit.hasNext())
            wordset.remove((String) dit.next());

        return wordset;
    }
    public static boolean samex(String a,String b)
    {
        int mx=0;
        if(a.length()>b.length())
        {
            String temp=b;
            b=a;
            a=temp;
        }
         for(int i=0;i<=(b.length()-a.length());i++)
         {
                int xi=0;
                int sum=0;
                while(xi<a.length())
                {
                    if(a.charAt(xi)==b.charAt(xi+i))sum++;
                     else break;
                    xi++;
                 }
                mx=Math.max(sum, mx);
          }
         double match=mx;
         double character=(a.length()+b.length())/2;
         double ans=(match/character)*100;
        if(ans<80.0) return false;
        else return true;
    }
    
    public static String getroot(String a)
    {
    	 String aroot="";
    	 
    	 if(root.containsKey(a))
   		   aroot=root.get(a).toString();
   	   	else 
   	   	{
   		   aroot=new BanglaVerb().bangVerbCheck(a);
   		   if(aroot.length()!=0)
   			   root.put(a,aroot);
   		   else aroot=Main.bs.removeSuffixPrefix(a);
   	   	}
    	 return aroot;
    }
    
    public static boolean same(String a,String b)
    {
    	
       String aroot,broot;
       
       
       
 	   if(root.containsKey(a))
 		   aroot=root.get(a).toString();
 	   else 
 	   {
 		   aroot=new BanglaVerb().bangVerbCheck(a);
 		   if(aroot.length()!=0)
 			   root.put(a,aroot);
 		   else aroot=Main.bs.removeSuffixPrefix(a);
 	   }
 	   
 	  if(root.containsKey(b))
		   broot=root.get(b).toString();
	   else 
	   {
		   broot=new BanglaVerb().bangVerbCheck(b);
			
		   if(broot.length()!=0)
			   root.put(b,broot);
		   else broot=Main.bs.removeSuffixPrefix(b);
	   }

 	   if(aroot.equals(broot))return true; // current element , koyta selement er sathe match kore ta gona hocce///////edit here 
 	   else return false;
    }
    
    
    
    public static double[] Query(Set wordset) throws FileNotFoundException, IOException
    {
        /// for the query
        ///////////////////////////////////////////////////////////////////
        File f=new File("D://Query.txt");
        InputStream is = new FileInputStream(f) ; // input carrier 
        BufferedReader br = new BufferedReader( new InputStreamReader(is) ); // buffer carrier
        String s; // string input variable
        StringBuilder sb = new StringBuilder (""); // first choices of words will be stored here 
        String bad="#'‘/“,@৳%&*()-=><\n\r?!।"; // this character will be removed
        int x=0; // used to remove the starting unknown character
        while( (s = br.readLine()) !=null )
        {
                 if(x==0)
                            s=s.replace( s.charAt(0), ' '); // removing very first character
                  x++;
                  for(int i=0;i<bad.length();i++)
                            s=s.replace( bad.charAt(i), ' '); // oproyojonio symbol bad jacce
                  sb.append(s);/// eta total unique words mark korar jonno used hbe
        }
        
        String[] words=sb.toString().split(" ");
        int fi=0;
        for(String word:words)
        {
        	words[fi]=getroot(word);
        	fi++;
        }
        double[] query=new double[100000];
         Iterator it = wordset.iterator(); // every unique word current sentence e ace kina/ kotobar ace  check kora hobe
        int i=0;
        while(it.hasNext())
         {
                   String element = (String) it.next(); // ekta unique word holo element
                   int sum=0;
                   for(String selement:words) // current sentence er ek ekta word holo selement
                            if(element.equals(selement))sum++; // current element , koyta selement er sathe match kore ta gona hocce
                   query[i]=sum; // result data te rakha hocce 
                   sum=0;
                   i++;
         }
        ///////////////////////////////////////////////////////
        return query;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code ap"অকায়_ADJ",
		
    	Main.bs.start();
    	//same("করেছিলাম","মিথ�?নের");
    	//new Scanner(System.in).nextLine();
        File[] files=new File("D://").listFiles();//every file will be scanned on this address
        Set wordset=new HashSet(); // total unique words will be stored
        String[][] filewords=new String[100][100000]; // individual words those are contained by the files
        String[] FileName=new String[100];
        int fwindex=0; // how many valid txt file
        for(File f:files) // for every files
        {
                    if(!f.getPath().endsWith(".txt"))continue; // if extention is txt
                    if(f.getName().equals("Query.txt"))continue; // Query file
                    System.out.println(f.toString()); // printing file name
                    FileName[fwindex]=f.toString();
                    InputStream is = new FileInputStream(f) ; // input carrier 
                    BufferedReader br = new BufferedReader( new InputStreamReader(is) ); // buffer carrier
                    
                    String s; // string input variable
                    StringBuilder sb = new StringBuilder (""); // first choices of words will be stored here 
                    String bad="#'�?’‘/“,@৳%&*()-=><\n\r?!।"; // this character will be removed
                    int x=0; // used to remove the starting unknown character
                    while( (s = br.readLine()) !=null )
                             {
                                    if(x==0)
                                                s=s.replace( s.charAt(0), ' '); // removing very first character
                                    x++;
                                    for(int i=0;i<bad.length();i++)
                                                s=s.replace( bad.charAt(i), ' '); // oproyojonio symbol bad jacce
                                    sb.append(s);/// eta total unique words mark korar jonno used hbe
                                    filewords[fwindex]=sb.toString().split(" "); //  words spilt hocce
                                    int fi=0;
                                    for(String word:filewords[fwindex])
                                    {
                                    	filewords[fwindex][fi]=getroot(word);
                                    	fi++;
                                    }
                                    //Set set =new HashSet(); // every word unique word hisebe set e thakbe 
                                    for(String show:filewords[fwindex]) // set e kono blank words thakbena
                                    {
                                             if(show.length()!=0)
                                            {
                                                    //System.out.println(show);
                                                        wordset.add(show);
                                            }
                                    }
                                    //System.out.print(s);
                                    s=null;
                             }
                     fwindex++;
                    //System.out.println();
        }
        //System.out.println(fwindex);
        wordset=stopword(wordset);
        double[][] data=new double[100000][100]; // the matrix 
        int check=0;
        for(int j=0;j<fwindex;j++) // for every txt file
        {
            Iterator it = wordset.iterator(); // every unique word current sentence e ace kina/ kotobar ace  check kora hobe
            int i=0;
                   while(it.hasNext())
                 {
                            String element = (String) it.next(); // ekta unique word holo element
                            int sum=0;
                           for(String selement:filewords[j]) // current sentence er ek ekta word holo selement
                                if(element.equals(selement))sum++; // current element , koyta selement er sathe match kore ta gona hocce
                            data[i][j]=sum; // result data te rakha hocce 
                            sum=0;
                            i++;
                            System.out.println(100*((j*wordset.size()+i))/(fwindex*wordset.size())+"%");
                }
        }
        
        
        
        
        double query[] = Query(wordset); // importing query function 
        System.out.println(fwindex+"\n"+wordset.size()); // number of file and number of unique word
        Iterator it = wordset.iterator(); // iterator that will browse  unique words
        for(int  i=0;i<wordset.size();i++) // for every unique words 
        {
            System.out.print((String) it.next()+"\t");
            for(int j=0;j<fwindex;j++)System.out.print(data[i][j]+"\t"); //printting occurence data
            System.out.print("| "+query[i]); // printing query 
            System.out.println();
        }
        
        int row=wordset.size();
        int column=fwindex;
        
         Matrix A=new Matrix(data,row,column);
        SingularValueDecomposition SVD=new SingularValueDecomposition(A);
        Matrix U=SVD.getU();
        Matrix S=SVD.getS();
        Matrix V=SVD.getV();
        Matrix Vt=V.transpose();
        
        System.out.println("U");
        U.print(3, 2);
        System.out.println("S");
        S.print(3, 2);
        System.out.println("V");
        V.print(3, 2);
        System.out.println("Vt");
        Vt.print(3, 2);
        
        System.out.println("Rank of S : "+S.rank());
        
        Matrix Uk=U.resize(U.getRowDimension(),S.rank());
        System.out.println("Uk");
        Uk.print(3,2);
        Matrix Sk=S.resize(S.rank(),S.rank());
        System.out.println("Sk");
        Sk.print(3,2);
        Matrix Vk=V.resize(V.getRowDimension(),S.rank());
        System.out.println("Vk");
        Vk.print(3,2);
        
        Matrix Vkt=Vk.transpose();
        System.out.println("Vkt");
        Vkt.print(3,2);
        
        double qtemp[][]=new double[row][1];
        for(int i=0;i<row;i++)qtemp[i][0]=query[i];
        Matrix q=new Matrix(qtemp,row,1);
        Matrix qt=q.transpose();
        
        Matrix Ski=Sk.inverse();
        System.out.println("Ski");
        Ski.print(3, 2);
        
        q=q.multiplcation(qt, Uk);
        q=q.multiplcation(q, Ski);
        System.out.println("Step 5  q ");
        q.print(3, 2);
        
        Matrix d=Vk;
        double[] sin=d.cosine(q, d);
        System.out.println("Cosine Similarities ");
        for(int i=0;i<column;i++)
                   System.out.println(sin[i]);
        int[] id=new int [column];
        for(int i=0;i<column;i++)
                   id[i]=i;
            
         
        // sorting desi sort
        for(int i=0;i<column;i++)
            for(int j=0;j<column-1;j++)
                if(sin[j]<sin[j+1])
                {
                   double stemp=sin[j];
                    sin[j]=sin[j+1];
                    sin[j+1]=stemp;
                    
                    int itemp=id[j];
                    id[j]=id[j+1];
                    id[j+1]=itemp;
                    
                }
        //////////////////////////////////////////////////
        System.out.println("Ranking ");
         for(int i=0;i<column;i++)
             System.out.println(id[i]+1+"."+FileName[id[i]]+" "+sin[i]);
         
         
        
        
    }
}




