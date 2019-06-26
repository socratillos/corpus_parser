package com.socrates.corpus.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.socrates.corpus.normalise.NormalizationService;
import com.socrates.corpus.normalise.maps.LemasMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachMap;
import com.socrates.corpus.normalise.maps.PartOfSpeachTypeMap;
import com.socrates.corpus.normalise.maps.WordsMap;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Word;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NormalizationTests {
	
	private Word word;
	private Word wordWithNegation;
	
	@Autowired
	private NormalizationService normalizationService;
	
	private static final Integer SENTENCE_NUMBER = 1;
	private static final Integer TOKEN_NUMBER = 0;
	private static final String DOMAIN = "coches_no_2_20";
	private static final String WORD = "Lo";
	private static final String LEMA = "el";
	private static final String PART_OF_SPEACH = "da0ns0";
	private static final String PART_OF_SPEACH_TYPE = "article";
	
	//NEGATION
	//Word [domain=coches_no_2_20, sentenceNumber=6, tokenNumber=1, word=Hoy, lema=hoy, partOfSpeach=rg, partOfSpeachType=-, negation=-, secondNegation=-]
	private static final Integer SENTENCE_NUMBER_NEGATION = 6;
	private static final Integer TOKEN_NUMBER_NEGATION = 1;
	private static final String DOMAIN_NEGATION = "coches_no_2_20";
	private static final String WORD_NEGATION = "Hoy";
	private static final String LEMA_NEGATION = "hoy";
	private static final String PART_OF_SPEACH_NEGATION = "rg";
	private static final String PART_OF_SPEACH_TYPE_NEGATION = "-";
	
	
	
	@Before
	public void setUp() {
		word = generateWord();
		wordWithNegation = generateWordWithNegation();
	}
	
	
	@Test
	public void testNormalise() {
		try {
			Optional<NormalisedWord> optionalObject = normalizationService.normalise(word);
			assertTrue(optionalObject.isPresent());
			
			NormalisedWord normalisedWord = optionalObject.get();
			assertEquals(Long.valueOf(SENTENCE_NUMBER) , normalisedWord.getSentenceNumber());
			assertEquals(Long.valueOf(TOKEN_NUMBER), normalisedWord.getTokenNumber());
			
			Long wordId = WordsMap.getWordId(WORD);
			assertEquals(wordId, normalisedWord.getWord());
			
			Long lemaId = LemasMap.getLemaId(LEMA);
			assertEquals(lemaId, normalisedWord.getLema());
			
			Long partOfSpeachId = PartOfSpeachMap.getPartOfSpeechId(PART_OF_SPEACH);
			assertEquals(partOfSpeachId, normalisedWord.getPartOfSpeach());
			
			Long partOfSpeachTypeId = PartOfSpeachTypeMap.getPartOfSpeechTypeId(PART_OF_SPEACH_TYPE);
			assertEquals(partOfSpeachTypeId, normalisedWord.getPartOfSpeachType());
			
			assertEquals(0L, normalisedWord.getNegation());
			assertEquals(0L, normalisedWord.getSecondNegation());
			
		} catch(Throwable ex) {
			fail("Exception trying to test normalise method: " + ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void testNormalisedWithNegation() {
		try {
			//Word [domain=coches_no_2_20, sentenceNumber=6, tokenNumber=1, word=Hoy, lema=hoy, partOfSpeach=rg, partOfSpeachType=-, negation=-, secondNegation=-]
			Optional<NormalisedWord> optionalObject = normalizationService.normalise(wordWithNegation);
			assertTrue(optionalObject.isPresent());
			
			NormalisedWord normalisedWord = optionalObject.get();
			assertEquals(Long.valueOf(SENTENCE_NUMBER_NEGATION) , normalisedWord.getSentenceNumber());
			assertEquals(Long.valueOf(TOKEN_NUMBER_NEGATION), normalisedWord.getTokenNumber());
			
			Long wordId = WordsMap.getWordId(WORD_NEGATION);
			assertEquals(wordId, normalisedWord.getWord());
			
			Long lemaId = LemasMap.getLemaId(LEMA_NEGATION);
			assertEquals(lemaId, normalisedWord.getLema());
			
			Long partOfSpeachId = PartOfSpeachMap.getPartOfSpeechId(PART_OF_SPEACH_NEGATION);
			assertEquals(partOfSpeachId, normalisedWord.getPartOfSpeach());
			
			Long partOfSpeachTypeId = PartOfSpeachTypeMap.getPartOfSpeechTypeId(PART_OF_SPEACH_TYPE_NEGATION);
			assertEquals(partOfSpeachTypeId, normalisedWord.getPartOfSpeachType());
			
			assertEquals(1L, normalisedWord.getNegation());
			assertEquals(1L, normalisedWord.getSecondNegation());
		} catch(Throwable ex) {
			fail("Exception trying to test normalise method with negation: " + ex.getLocalizedMessage());
		 }
	}
	
	@Test
	public void testNormaliseWithNullWord() {
		try {
			Optional<NormalisedWord> optionalObject = normalizationService.normalise(null);
			assertFalse(optionalObject.isPresent());
		} catch(Throwable ex) {
			fail("Exception trying to test normalise method with null word: " + ex.getLocalizedMessage());
		 }
	}
	
	@Test(expected = AssertionError.class)
	public void testNormaliseWithNullLema() {
		word.setLema(null);
		Optional<NormalisedWord> optionalObject = normalizationService.normalise(word);
		assertFalse(optionalObject.isPresent());
	}
	
	private Word generateWord() {
		Word word = new Word();
		word.setDomain(DOMAIN);
		word.setSentenceNumber(SENTENCE_NUMBER);
		word.setTokenNumber(TOKEN_NUMBER);
		word.setWord(WORD);
		word.setLema(LEMA);
		word.setPartOfSpeach(PART_OF_SPEACH);
		word.setPartOfSpeachType(PART_OF_SPEACH_TYPE);
		
		return word;
	}
	
	private Word generateWordWithNegation() {
		
		Word word = new Word();
		word.setDomain(DOMAIN_NEGATION);
		word.setSentenceNumber(SENTENCE_NUMBER_NEGATION);
		word.setTokenNumber(TOKEN_NUMBER_NEGATION);
		word.setWord(WORD_NEGATION);
		word.setLema(LEMA_NEGATION);
		word.setPartOfSpeach(PART_OF_SPEACH_NEGATION);
		word.setPartOfSpeachType(PART_OF_SPEACH_TYPE_NEGATION);
		word.setNegation("-");
		word.setSecondNegation("-");
		return word;
	}

}
