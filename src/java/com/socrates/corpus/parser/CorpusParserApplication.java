package com.socrates.corpus.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.socrates.corpus.arff.output.ARFFOutputService;
import com.socrates.corpus.input.CorpusParser;
import com.socrates.corpus.input.FileParser;
import com.socrates.corpus.normalise.NormalizationService;
import com.socrates.corpus.normalise.model.GRMMNormalisedWord;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;

@SpringBootApplication
@ComponentScan(basePackages = "com.socrates.corpus")
public class CorpusParserApplication {
	
	private final static String WEKA_OPTION = "weka";
	private final static String GRMM_OPTION = "grmm";

	
	private static FileParser fileParser;
	private static CorpusParser corpusParser;
	private static NormalizationService normalizationService;
	private static ARFFOutputService outputService;

	

	public static void main(String[] args) {
		SpringApplication.run(CorpusParserApplication.class, args);
		System.out.println("Running parser application...");
		if(args.length < 3) {
			System.out.println("Error input param. Param 1: weka|grmm. Param 2: path file");
			System.exit(-1);
		} else {
			String param1 = args[0].toLowerCase();
			String param2 = args[1];
			String param3 = args[2];
			switch(param1) {
				case WEKA_OPTION:
				{
					try {
						Boolean result = buildWekaFile(param2, param3);
						if (result) {
							System.out.println("Fichero weka parseado satisfactoriamente");
							System.exit(0);
						}
					} catch (Exception e) {
						System.out.println("Error during weka parsing...exit");
						e.printStackTrace();
						System.exit(-1);
					}
				}
				case GRMM_OPTION:
				{
					//buildGRMMFile
					try {
						Boolean result = buildGRMMFile(param2, param3);
						if (result) {
							System.out.println("Fichero grmm parseado satisfactoriamente");
							System.exit(0);
						}
					} catch (Exception e) {
						System.out.println("Error during grmm parsing...exit");
						e.printStackTrace();
						System.exit(-1);
					}
				}
				default:
				{
					System.out.println("Error input param. Param 1: weka|grmm");
					System.exit(-1);
				}
			}
		}
		System.exit(0);
	}
	
	private static Boolean buildWekaFile(String path, String output) throws IOException {
		File file;
		file = new File(path);
		if(!file.exists()) {
			System.out.println("Fichero no existente...");
			System.exit(-1);
		}
		
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
		FileWriter fw = outputService.writeFileFromNormalisedObject(normalisedWords, output);
		
		return (fw != null);
	}
	
	private static boolean buildGRMMFile(String path, String output) throws IOException {
		File file;
		file = new File(path);
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
		List<GRMMNormalisedWord> normalisedWords = normalizationService.normaliseGRMMSentences(sentences);
		FileWriter fw = outputService.writeFileFromGRMMNormalisedObject(normalisedWords, output);
		
		return (fw != null);
	}
	
	@Autowired
	public void setCorpusParser(CorpusParser corpusParser) {
		CorpusParserApplication.corpusParser = corpusParser;
	}
	
	@Autowired
	public void setFileParser(FileParser fileParser) {
		CorpusParserApplication.fileParser = fileParser;
	}
	
	@Autowired
	public void setNormalizationService(NormalizationService normalizationService) {
		CorpusParserApplication.normalizationService = normalizationService;
	}
	
	@Autowired
	public void setOutputService(ARFFOutputService outputService) {
		CorpusParserApplication.outputService = outputService;
	}

}
