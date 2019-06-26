package com.socrates.corpus.normalise;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.socrates.corpus.normalise.maps.LemasMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachTypeMap;
import com.socrates.corpus.normalise.maps.WordsMap;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Word;

@Service
public class NormalizationService {
	
	public Optional<NormalisedWord> normalise(Word word) {
		if(word != null) {
			
			assert word.getWord() != null;
			assert word.getLema() != null;
			assert word.getPartOfSpeach() != null;
			assert word.getPartOfSpeachType() != null;
			
			NormalisedWord normalisedWord = new NormalisedWord();
			normalisedWord.setSentenceNumber(Long.valueOf(word.getSentenceNumber()));
			normalisedWord.setTokenNumber(Long.valueOf(word.getTokenNumber()));
			
			Long wordId = WordsMap.getWordId(word.getWord());
			normalisedWord.setWord(wordId);
			
			Long lemaId = LemasMap.getLemaId(word.getLema());
			normalisedWord.setLema(lemaId);
			
			Long partOfSpeechId = PartOfSpeachMap.getPartOfSpeechId(word.getPartOfSpeach());
			normalisedWord.setPartOfSpeach(partOfSpeechId);
			
			Long partOfSpeechTypeId = PartOfSpeachTypeMap.getPartOfSpeechTypeId(word.getPartOfSpeachType());
			normalisedWord.setPartOfSpeachType(partOfSpeechTypeId);
			
			if(word.isNegation()) {
				normalisedWord.setNegation(1);
				
				if(word.getSecondNegation() != null) {
					normalisedWord.setSecondNegation(1);
				} else {
					normalisedWord.setSecondNegation(0);
				}
				
			} else {
				normalisedWord.setNegation(0);
			}
			
			return Optional.of(normalisedWord);
		}
		
		return Optional.empty();
	}
	
	
}
