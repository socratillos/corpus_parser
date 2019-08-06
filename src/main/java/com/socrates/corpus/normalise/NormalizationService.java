package com.socrates.corpus.normalise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.socrates.corpus.normalise.maps.LemasMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachTypeMap;
import com.socrates.corpus.normalise.maps.WordsMap;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Sentence;
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
			normalisedWord.setDomainName(word.getDomain());
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
	
	public List<NormalisedWord> normalise(Sentence sentence) {
		assert sentence != null;
		assert sentence.getWords() != null;
		
		List<NormalisedWord> normalisedWords = new ArrayList<>();
		
		sentence.getWords().forEach( word -> {
			Optional<NormalisedWord> optionalNormalisedWord = normalise(word);
			if(optionalNormalisedWord.isPresent()) {
				normalisedWords.add(optionalNormalisedWord.get());
			}
		});
		
		if(normalisedWords.isEmpty()) {
			return Collections.emptyList();
		}
		
		return normalisedWords;
	}
	
	public List<NormalisedWord> normalise(List<Sentence> sentences) {
		assert sentences != null;
		
		List<NormalisedWord> normalisedWords = new ArrayList<>();
		
		sentences.forEach( sentence -> {
			normalisedWords.addAll(normalise(sentence));
		});
		
		return normalisedWords;
	}
	
	
}
