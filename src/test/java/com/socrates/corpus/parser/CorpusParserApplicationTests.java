package com.socrates.corpus.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.socrates.corpus.input.CorpusParser;
import com.socrates.corpus.input.FileParser;
import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CorpusParserApplicationTests {
	
	private static final String path = "src/test/resources/data/corpus_test.txt";
	private static final String path_space_division = "src/test/resources/data/corpus_with_space_division.txt";
	private static final String LINE = "coches_yes_4_9	17	61	,	,	fc	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	";
	
	
	
	File file;
	File file_with_spaces;
	
	@Autowired
	private FileParser fileParser;
	
	@Autowired
	private CorpusParser corpusParser;
	
	@Before
	public void setUp() {
		file = new File(path);
		file_with_spaces = new File(path_space_division);
	}
	
	@Test
	public void readFileInOneStringTest() {
		try {
			String completeStringFromFile = fileParser.readFile(file);
			assertNotNull(completeStringFromFile);
			assertFalse(completeStringFromFile.isEmpty());
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test readFileInOneString method: " + ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void readFileInMultipleString() {
		try {
			List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file);
			assertNotNull(multipleLinesFromFile);
			assertFalse(multipleLinesFromFile.isEmpty());
			multipleLinesFromFile.stream().forEach( System.out::println);
			
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test readFileInMultipleLines method: " + ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void readFileWithSpaceDivision() {
		try {
			List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file_with_spaces);
			assertNotNull(multipleLinesFromFile);
			assertFalse(multipleLinesFromFile.isEmpty());
			boolean hasSpaceDivision = false;
			for(String line : multipleLinesFromFile) {
				hasSpaceDivision = fileParser.isNewSentence(line);
				if(hasSpaceDivision) {
					return;
				}
			}
			assertTrue(hasSpaceDivision);
			
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test isNewSentence method: " + ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void splitLinesTest() {
		try {
			List<String> parts = fileParser.splitLines(LINE);
			assertNotNull(parts);
			assertFalse(parts.isEmpty());
			assertEquals(25, parts.size());
			//coches_yes_4_9	17	61	,	,	fc
			assertEquals("coches_yes_4_9", parts.get(0));
			assertEquals("17", parts.get(1));
			assertEquals("61", parts.get(2));
			assertEquals(",", parts.get(3));
			assertEquals(",", parts.get(4));
			assertEquals("fc", parts.get(5));
			assertEquals("-", parts.get(6));
			assertEquals("-", parts.get(7));
			assertEquals("-", parts.get(8));
			assertEquals("-", parts.get(9));
			assertEquals("-", parts.get(10));
			assertEquals("-", parts.get(11));
			assertEquals("-", parts.get(12));
			assertEquals("-", parts.get(13));
			assertEquals("-", parts.get(14));
			assertEquals("-", parts.get(15));
			assertEquals("-", parts.get(16));
			assertEquals("-", parts.get(17));
			assertEquals("-", parts.get(18));
			assertEquals("-", parts.get(19));
			assertEquals("-", parts.get(20));
			assertEquals("-", parts.get(21));
			assertEquals("-", parts.get(22));
			assertEquals("-", parts.get(23));
			assertEquals("-", parts.get(24));
		} catch(Throwable ex) {
			ex.printStackTrace();
			fail("Exception trying to test splitLines method: " + ex.getLocalizedMessage());
		}
	}
	@Test
	public void testParseWordLine() {
		try {
			List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file);
			Sentence sentence = new Sentence();
			List<Sentence> sentences = new ArrayList<>();
			boolean hasTwoNegations = Boolean.FALSE;
			
			for(String line: multipleLinesFromFile) {
				
				if(!fileParser.isNewSentence(line)) {
					List<String> parts = fileParser.splitLines(line);
					Word word = corpusParser.parseWordLine(parts);
					sentence.addWord(word);
				} else {
					assertFalse(sentence.isEmpty());
					
					if(sentence.hastTwoNegation()) {
						System.out.println(sentence);
						hasTwoNegations = Boolean.TRUE;
					}
					sentences.add(sentence);
					sentence = new Sentence();
				}
			}
			
			assertTrue(hasTwoNegations);
				
		} catch(Throwable ex) {
			fail("Exception trying to test parseWordLine method: " + ex.getLocalizedMessage());
		}
	}
		
		
}
