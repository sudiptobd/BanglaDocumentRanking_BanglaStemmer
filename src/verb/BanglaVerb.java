package verb;

public class BanglaVerb {
	private  String root_word="";
	
	public  String bangVerbCheck(String word)
	{
		String verbSuffix[]={"ছিস","ছেন","চ্ছি","চ্ছ","চ্ছে","েছি","েছ","েছে","লাম","লেন","ছিলে","ছিল","তাম","তেন","বেন"};
		String verbData[][] =null;
		
		for(String eachVerbSuffix:verbSuffix)
			if(word.endsWith(eachVerbSuffix))
			{
				root_word=word.substring(0, word.length()-eachVerbSuffix.length());
				break;
			}
		
				
		if(word.startsWith("আ"))
			verbData= new verbData2().data;
		
		else if(word.startsWith("উ"))
			verbData= new verbData5().data;
		
		else if(word.startsWith("এ"))
			verbData= new verbData8().data;
		
		else if(word.startsWith("ও"))
			verbData= new verbData10().data;
		
		else if(word.startsWith("ক"))
			verbData= new verbData12().data;
		
		else if(word.startsWith("খ"))
			verbData= new verbData13().data;
		
		else if(word.startsWith("গ"))
			verbData= new verbData14().data;
		
		else if(word.startsWith("ঘ"))
			verbData= new verbData15().data;
		
		else if(word.startsWith("চ"))
			verbData= new verbData17().data;
		
		else if(word.startsWith("ছ"))
			verbData= new verbData18().data;
		
		else if(word.startsWith("জ"))
			verbData= new verbData19().data;
		
		else if(word.startsWith("ঝ"))
			verbData= new verbData20().data;
		
		else if(word.startsWith("ট"))
			verbData= new verbData22().data;
		
		else if(word.startsWith("থ"))
			verbData= new verbData23().data;
		
		else if(word.startsWith("ড"))
			verbData= new verbData24().data;
		
		else if(word.startsWith("ঢ"))
			verbData= new verbData25().data;
		
		else if(word.startsWith("ত"))
			verbData= new verbData27().data;
		
		else if(word.startsWith("থ"))
			verbData= new verbData28().data;
		
		else if(word.startsWith("দ"))
			verbData= new verbData29().data;
		
		else if(word.startsWith("ধ"))
			verbData= new verbData30().data;
		
		else if(word.startsWith("ন"))
			verbData= new verbData31().data;
		
		else if(word.startsWith("প"))
			verbData= new verbData32().data;
		
		else if(word.startsWith("ফ"))
			verbData= new verbData33().data;
		
		else if(word.startsWith("ব"))
			verbData= new verbData34().data;
		
		else if(word.startsWith("ভ"))
			verbData= new verbData35().data;
		
		else if(word.startsWith("ম"))
			verbData= new verbData36().data;
		
		else if(word.startsWith("য"))
			verbData= new verbData37().data;
		
		else if(word.startsWith("র"))
			verbData= new verbData38().data;
		
		else if(word.startsWith("ল"))
			verbData= new verbData39().data;
		
		else if(word.startsWith("শ"))
			verbData= new verbData40().data;
		
		else if(word.startsWith("স"))
			verbData= new verbData42().data;
		
		else
			verbData= new verbData43().data;

		for(int i=0;i<verbData.length;i++)
			for(int j=0;j<verbData[i].length;j++)
				if(root_word.equals(verbData[i][j]))
				{
					root_word=verbData[i][0];
					return root_word;
				}
		
		
		return root_word;
	}
}