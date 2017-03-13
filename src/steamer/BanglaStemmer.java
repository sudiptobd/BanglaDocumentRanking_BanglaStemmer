package steamer;

public class BanglaStemmer {
	private String[] uposorgo = { "অ", "অঘা", "অজ", "অনা", "আ", "আড়", "আন", "আব", "ইতি", "ঊন", "ঊনা", "কদ", "কু", "নি",
			"পাতি", "বি", "ভর", "রাম", "স", "সা", "সু", "হা ", "প্র", "পরা", "অপ", "সম", "নি", "অনু", "অব", "নির",
			"দুর", "বি", "অধি", "সু", "উৎ", "পরি", "প্রতি", "অতি", "অপি", "অভি", "উপ", "আ", "কার", "দর",
			/* "না", */"নিম", "ফি", "বদ", "বে", "বর", "ব", "কম", "আম", "খাস", "লা", "গর", "ফুল", "হাফ", "হেড", "সাব",
			"হর" };
	private String[] prottoi = { "[ি ো]", // removeOI
			"দের", "য়েদেরকে", "েদেরকে", "দেরকে", "য়েদের", "েদের", "য়েরা", "ভাবে", "ের", "েরা", "য়ের", /* "কায়", */"কার",
			"িলা", "িলা", "ার", "ান", "কু", "কা", "বি", "তে", "রা", "য়", "বে", "মি", "সি", /* "ড়ে", */"কে", "ে", "েই",
			"র", "য়", "ব", "ম", "স", "া", // removecase
			"খানা", "খানি", "গুলো", "গুলি", "য়োন", "খান", "গুল", "টা", "টি", "টু", "কা", "তা", "িল", "িক", "েক", "েত",
			/* "লি", */"য়া", "ায়", "ড়ি", "িস", "ান", // removearticle
			"িনি", "নি", "না", "তেই", "টাই", // normalize

			// prottoi-bangla(krit)
			"য়ে", "না", "তি", "তা", "ওয়া", "উয়া", "উনি", "উকা", "ঈ", "ই", "আও", "অ", " রি", " তি", " তা", " ও", " এ",
			" ঊ", " উরি", " উন্তি", " উক", " ইয়ে", " ইয়া", " ইলে", " আল", " আরী", " আরি", " আনো", " আনি", " আন", " আত",
			" আকু", " আইত", " আই", " আ", " অন্তি", " অন্ত", " অনি", " অনা", " অন", " অতি", " অতা", " অত", " অক", " োয়া",
			"ুয়া", "ুন্তি", "ুনি", "ুকা", "ুকা", "ু", "ো", "ে", "ী", "য়",
			// prottoi-bangla(toddit)
			"ড়ে", "ড়ি", "ড়া", "সে", "সিয়", "সা", "লা", "মি", "মন্ত", "ভরা", "ভর", "পারা", "পানো", "পনা", "নি", "তুত",
			"তি", "তা", "টে", "টিয়া", "টা", "ট", "চে", "কে", "কিয়া", "কা", "ক", "ওয়ালা", "এল", "এল", "ঊ", "উয়া",
			"উড়িয়া", "উড়", "উলি", "উরে", "উরিয়া", "উক", "ইয়াল", "ইয়া", "ই", "আলো", "আলি", "আলা", "আল", "আরু", "আরি",
			"আর", "আমো", "আমি", "আম", "আনো", "আনি", "আত", "আচি", "আচ", "আইত", "আই", "আ", "অড়", "অল", };
	private String word_with_suffix_prefix;
	private String root_word;
	private String suffix;
	private String prefix;
	//Stemmer banglaStemmer2 = new Stemmer();
	
	BanglaPosDic banPosTag = new BanglaPosDic();
	public void start()
	{
		banPosTag.bangbang();
	}
	
	public String getSuffix(){
		return this.suffix;
	}

	public String removeSuffixPrefix(String word) {
		String temp = "";
		String temp_prefix_remove_word = "";
		root_word = "";
		suffix = "";
		prefix = "";
		for (int i = 0; i < prottoi.length; i++) {
			if (word.endsWith(prottoi[i])) {
				temp = word.substring(0, word.length() - prottoi[i].length());
				root_word = temp;
				suffix = prottoi[i];
				if (BanglaPosDic.getWordWithPOS(temp) != null) {
					prefix = "";
					return root_word;
				} else {
					String properNounSufixes[] = { "কে", "ের", "র", "য়ে","তে", "ে"};
					for (int j = 0; j < properNounSufixes.length; j++) {
						if (word.endsWith(properNounSufixes[j])) {
							temp = word.substring(0, word.length() - properNounSufixes[j].length());
							root_word = temp;
							suffix = properNounSufixes[j];
							prefix = "";
							return root_word;
						}
					}
				}
				temp_prefix_remove_word = this.removePrefix(temp);
				if(!temp_prefix_remove_word.equals("") && !temp_prefix_remove_word.equals(temp)){
					root_word = temp_prefix_remove_word;
					return root_word;
				}
			}
		}
		if(root_word == "")
			root_word = word;
		//suffix = "";
		temp_prefix_remove_word = this.removePrefix(word);
		if(!temp_prefix_remove_word.equals("") && !temp_prefix_remove_word.equals(word)){
			root_word = temp_prefix_remove_word;
		}
		return root_word;
		//if(!tempora.equals("")&&!tempora.equals(temp))return tempora;
	}
	
	public String removePrefix(String word_or_stemm) {
		String temp_2 = "";
		for (int i = 0; i < uposorgo.length; i++) {
			if (word_or_stemm.startsWith(uposorgo[i])) {
				int beginnerIndex=uposorgo[i].length();
				temp_2=word_or_stemm.substring(beginnerIndex);
				if (BanglaPosDic.getWordWithPOS(temp_2) != null) {
					prefix = uposorgo[i];
					return temp_2;
				} else {
					prefix = "";
				}
			}
		}
		prefix = "";
		return word_or_stemm;
	}
	
	public String getPrefix(){
		return this.prefix;
	}

	public String getWord_with_suffix_prefix() {
		return word_with_suffix_prefix;
	}

	public void setWord_with_suffix_prefix(String word_with_suffix_prefix) {
		this.word_with_suffix_prefix = word_with_suffix_prefix;
	}

	public String getRootWord() {
		return root_word;
	}

	public void setWord_with_prefix(String root_word) {
		this.root_word = root_word;
	}
}