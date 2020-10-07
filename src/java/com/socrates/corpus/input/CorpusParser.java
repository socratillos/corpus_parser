package com.socrates.corpus.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;

@Service
public class CorpusParser {
	
	@Autowired
	private FileParser fileParser;
	
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
		
		if(line.size() > 11 && word.isNegation()) {
			word.setSecondNegation(line.get(10));
		}
		return word;
	}
	public List<Sentence> parseFile(File file) throws IOException {
		
		List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file);
		Sentence sentence = new Sentence();
		List<Sentence> sentences = new ArrayList<>();
		
		for(String line: multipleLinesFromFile) {
			
			if(!fileParser.isNewSentence(line)) {
				List<String> parts = fileParser.splitLines(line);
				Word word = parseWordLine(parts);
				sentence.addWord(word);
			} else {
				
//				if(hasNegation(sentence)) {
//					setSentenceNegated(sentence);
//				}
				
				sentences.add(sentence);
				sentence = new Sentence();
			}
		}
		
		
		return sentences;
	}
	
	private void setSentenceNegated(Sentence sentence) {
		sentence.getWords().forEach(word -> {
			word.setNegation("T");
		});
	}
	
	private Boolean hasNegation(Sentence sentence) {
		for(Word word:sentence.getWords()) {
			if(word.isNegation()) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}
}
