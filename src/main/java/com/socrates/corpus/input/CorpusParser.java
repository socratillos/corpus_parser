package com.socrates.corpus.input;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socrates.corpus.parser.model.Word;

@Service
public class CorpusParser {
	
	public Word parseWordLine(List<String> line) {
		if(line.size() < 8)  {
			return null;
		}
		Word word = new Word();
		word.setDomain(line.get(0));
		word.setSentenceNumber(Integer.valueOf(line.get(1)));
		word.setTokenNumber(Integer.valueOf(line.get(2)));
		word.setWord(line.get(3));
		word.setLema(line.get(4));
		word.setPartOfSpeach(line.get(5));
		word.setPartOfSpeachType(line.get(6));
		word.setNegation(line.get(7));
		//word.setSecondNegation(line.get(10));
		
		return word;
	}
}
