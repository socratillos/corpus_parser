package com.socrates.corpus.parser.arff.output;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.socrates.corpus.arff.output.ARFFOutputService;
import com.socrates.corpus.input.CorpusParser;
import com.socrates.corpus.input.FileParser;
import com.socrates.corpus.normalise.NormalizationService;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;
import com.socrates.corpus.redis.model.Lema;
import com.socrates.corpus.redis.repo.LemaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARFFOutputTest {
	
	private static final String path = "src/test/resources/data/corpus_test.txt";
	File file;
	
	@Autowired
	private ARFFOutputService outputService;
	
	@Autowired
	private NormalizationService normalizationService;
	
	@Autowired
	private CorpusParser corpusParser;
	
	@Autowired
	private FileParser fileParser;
	
	@Autowired
	private LemaRepository lemaRepository;
	
	@Before
	public void setUp() {
		file = new File(path);
	}
	
	@Test
	public void testWriteFileFromNormalisedObject() {
		try {
			FileWriter fw = outputService.writeFormatedFileFromInputFile(file);
			assertNotNull(fw);
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test normalise method: " + ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void testWriteFileFromListOfNormalisedObject() {
		try {
			List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file);
			Sentence sentence = new Sentence();
			List<Sentence> sentences = new ArrayList<>();
			for(String line: multipleLinesFromFile) {
				if(!fileParser.isNewSentence(line)) {
					List<String> parts = fileParser.splitLines(line);
					Word word = corpusParser.parseWordLine(parts);
					sentence.addWord(word);
				} else {
					sentences.add(sentence);
					sentence = new Sentence();
				}
			}
			
			//Get the max amount of words in a sentence
			int max = 0;
			for(Sentence sentenceFromList : sentences) {
				if(sentenceFromList.getNumberOfWords() > max) {
					max = sentenceFromList.getNumberOfWords();
				}
			}
			
			
			//Write the normalized sentences into the file
			List<NormalisedWord> normalisedWords = normalizationService.normalise(sentences, max);
			FileWriter fw = outputService.writeFileFromNormalisedObject(normalisedWords);
			assertNotNull(fw);
			
			//Test for redis
			Map<String, Lema> lemas = lemaRepository.findAll();
			assertNotNull(lemas);
			assertFalse(lemas.isEmpty());
			
			for (Map.Entry<String, Lema> entry : lemas.entrySet()) {
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
			
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test normalise method for list of sentences: " + ex.getLocalizedMessage());
		}
	}
	
}
