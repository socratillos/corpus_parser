package com.socrates.corpus.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.socrates.corpus.input.FileParser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorpusParserApplicationTests {
	
	private static final String path = "src/test/resources/data/corpus_test.txt";
	
	File file;
	
	@Autowired
	private FileParser fileParser;
	
	@Before
	public void setUp() {
		file = new File(path);
	}
	
	@Test
	public void readFileInOneString() {
		try {
			String completeStringFromFile = fileParser.readFile(file);
			assertNotNull(completeStringFromFile);
			assertFalse(completeStringFromFile.isEmpty());
		} catch(Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void readFileInMultipleString() {
		try {
			List<String> multipleLinesFromFile = fileParser.readLinesFromFile(file);
			assertNotNull(multipleLinesFromFile);
			assertFalse(multipleLinesFromFile.isEmpty());
			
		} catch(Throwable ex) {
			ex.printStackTrace();
		}
	}
}
