package com.socrates.corpus.normalise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socrates.corpus.normalise.maps.LemasMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachTypeMap;
import com.socrates.corpus.normalise.maps.WordsMap;
import com.socrates.corpus.normalise.model.GRMMNormalisedWord;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;
import com.socrates.corpus.redis.model.Lema;
import com.socrates.corpus.redis.repo.LemaRepository;

@Service
public class NormalizationService {
	
	@Autowired
	private LemaRepository lemaRepository;
	
	private static final String EMTPY_DOMAIN = "empty_domain";
	private static final  Long EMPTY_LONG = 0L;
	private static final String EMPTY_SPACE = " ";
	private static final String POSSITIVE = "positive";
	private static final String NEGATIVE = "negative";
	
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
			
			Long lemaLongValue = LemasMap.getLemaId(word.getLema());
			normalisedWord.setLema(lemaLongValue);
			//Create a Lema object
			Lema lema = new Lema(word.getLema(), lemaLongValue);
			lemaRepository.save(lema);
			
			
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
	
	public GRMMNormalisedWord normaliseGRMMWord(Word word) {
		GRMMNormalisedWord grmmWord = new GRMMNormalisedWord();
		grmmWord.setDomain(word.getDomain());
		grmmWord.setSentenceNumber(word.getSentenceNumber());
		grmmWord.setTokenNumber(word.getTokenNumber());
		grmmWord.setWord(word.getWord());
		grmmWord.setLema(word.getLema());
		grmmWord.setPartOfSpeach(word.getPartOfSpeach());
		grmmWord.setPartOfSpeachType(word.getPartOfSpeachType());
		if(word.isNegation()) {
			grmmWord.setNegation(NEGATIVE);
		} else {
			grmmWord.setNegation(POSSITIVE);
		}
		
		return grmmWord;
	}
	
	public List<GRMMNormalisedWord> normaliseGRMMSentence(Sentence sentence) {
		assert sentence != null;
		assert sentence.getWords() != null;
		
		List<GRMMNormalisedWord> normalisedWords = new ArrayList<>();
		
		sentence.getWords().forEach( word -> {
			GRMMNormalisedWord grmmWord = normaliseGRMMWord(word);
			if(grmmWord != null) {
				normalisedWords.add(grmmWord);
			}
			
		});
		
		return normalisedWords;
	}
	
	public List<GRMMNormalisedWord> normaliseGRMMSentences(List<Sentence> sentences) {
		assert sentences != null;
		assert !sentences.isEmpty();
		
		List<GRMMNormalisedWord> normalisedWords = new ArrayList<>();
		
		sentences.forEach( sentence -> {
			if(sentence.getWords() != null && !sentence.getWords().isEmpty()) {
				normalisedWords.addAll(normaliseGRMMSentence(sentence));
			}
		});
		
		return normalisedWords;
	}
	
	public NormalisedWord normaliseEmptyWord(NormalisedWord word) {
		NormalisedWord emptyWord = new NormalisedWord();
		Long tokenNumber = word.getTokenNumber() + 1;
		
		emptyWord.setDomainName(word.getDomainName());
		emptyWord.setSentenceNumber(word.getSentenceNumber());
		emptyWord.setTokenNumber(tokenNumber);
		emptyWord.setWord(EMPTY_LONG);
		emptyWord.setLema(EMPTY_LONG);
		emptyWord.setPartOfSpeach(EMPTY_LONG);
		emptyWord.setPartOfSpeachType(EMPTY_LONG);
		
		return emptyWord;
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
	
	public List<NormalisedWord> normalise(Sentence sentence, int max) {
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
		} else if(sentence.getNumberOfWords() < max){
			int dif = max - sentence.getNumberOfWords();
			
			for(int i = 0; i < dif; i++) {
				//Iterables
				NormalisedWord lastNormalisedWord = normalisedWords.get(normalisedWords.size() - 1);
				normalisedWords.add(normaliseEmptyWord(lastNormalisedWord));
			}
			
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
	
	public List<NormalisedWord> normalise(List<Sentence> sentences, int max) {
		assert sentences != null;
		
		List<NormalisedWord> normalisedWords = new ArrayList<>();
		
		sentences.forEach( sentence -> {
			normalisedWords.addAll(normalise(sentence, max));
		});
		
		return normalisedWords;
	}
	
	
}
