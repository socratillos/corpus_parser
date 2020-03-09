package com.socrates.corpus.arff.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socrates.corpus.input.CorpusParser;
import com.socrates.corpus.normalise.NormalizationService;
import com.socrates.corpus.normalise.model.GRMMNormalisedWord;
import com.socrates.corpus.normalise.model.NormalisedWord;
import com.socrates.corpus.parser.model.Sentence;
import com.socrates.corpus.parser.model.Word;

@Service
public class ARFFOutputService {
	
	private final static String NEGATIVE = "negative";
	private final static String POSITIVE = "positive";
	private final static String WHITE_SPACE = " ";
	private final static String SEPARATION = "----";
	private final static String DOMAIN_FAIL_NAME = "domain_filename";
	private final static String SENTENCE_NUMBER = "sentence number";
	private final static String TOKEN_NUMBER = "token number";
	private final static String LEMA = "lemma";
	private final static String PART_OF_SPEECH = "part-of-speech";
	private final static String EQUALS = "=";
	
	@Autowired
	private CorpusParser corpusParser;
	
	@Autowired
	private ARFFOutputService outputService;
	
	@Autowired
	private NormalizationService normalizationService;
	
	
	private String title;
	
	public FileWriter writeFileFromGRMMNormalisedObject(List<GRMMNormalisedWord> normalisedWords) throws IOException {
		FileWriter fw = new FileWriter("src/test/resources/data/corpus_test.txt");
		
		normalisedWords.forEach( word -> {
			
			try {
				fw.write(word.getWord());
				fw.write(WHITE_SPACE);
				fw.write(word.getPartOfSpeachType());
				fw.write(WHITE_SPACE);
				
				fw.write(SEPARATION);
				fw.write(WHITE_SPACE);
				
				fw.write(DOMAIN_FAIL_NAME);
				fw.write(EQUALS);
				fw.write(word.getDomain());
				fw.write(WHITE_SPACE);
				
				fw.write(SENTENCE_NUMBER);
				fw.write(EQUALS);
				fw.write(String.valueOf(word.getSentenceNumber()));
				fw.write(WHITE_SPACE);
				
				fw.write(TOKEN_NUMBER);
				fw.write(EQUALS);
				fw.write(String.valueOf(word.getTokenNumber()));
				fw.write(WHITE_SPACE);
				
				fw.write(LEMA);
				fw.write(EQUALS);
				fw.write(word.getLema());
				fw.write(WHITE_SPACE);
				
				fw.write(PART_OF_SPEECH);
				fw.write(EQUALS);
				fw.write(word.getPartOfSpeach());
				
				fw.write("\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		});
		
		return fw;
	}
	
	public FileWriter writeFileFromNormalisedObject(List<NormalisedWord> normalisedWords) throws IOException {
		FileWriter fw = new FileWriter("src/test/resources/data/corpus.arff");
		
		fw.write("% 1. Title: Test\n");
		fw.write("%\n");
		fw.write("% 2. Sources:\n");
		fw.write("%      (a) Creator: R.A. Fisher\n");
		fw.write("% (b) Donor: Michael Marshall (MARSHALL%PLU@io.arc.nasa.gov)\n");
		fw.write("%      (c) Date: July, 1988\n");
		fw.write("%\n");
		
		fw.write(Headers.RELATION.header +" TEST\n");
		fw.write("\n");
		fw.write(Headers.ATTRIBUTE.header + " domain " + AttributeTypes.STRING.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " sentenceNumber " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " tokenNumber " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " word " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " lema " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " partOfSpeach " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " partOfSpeachType " + AttributeTypes.NUMERIC.value+"\n");
		fw.write(Headers.ATTRIBUTE.header + " " + AttributeTypes.CLASS.value + " " + "{" + POSITIVE + ", " + NEGATIVE + "}\n");
		
		fw.write(Headers.DATA.header+"\n");
		
		normalisedWords.forEach(normalisedWord -> {
			try {
				fw.write(normalisedWord.getDomainName()+",");
				fw.write(normalisedWord.getSentenceNumber()+",");
				fw.write(normalisedWord.getTokenNumber().toString()+",");
				fw.write(normalisedWord.getWord().toString()+",");
				fw.write(normalisedWord.getLema().toString()+",");
				fw.write(normalisedWord.getPartOfSpeach().toString()+",");
				fw.write(normalisedWord.getPartOfSpeachType().toString()+",");
				if(NumberUtils.INTEGER_ONE.equals(normalisedWord.getNegation())) {
					fw.write(NEGATIVE);
				} else {
					fw.write(POSITIVE);
				}
				fw.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		fw.close();
		
		return fw;
	}
	
	
	public FileWriter writeFormatedFileFromInputFile(File file) throws IOException{
		List<Sentence> sentences = corpusParser.parseFile(file);
		FileWriter fw = null;
		
		for(Sentence sentence : sentences) {
			List<NormalisedWord> normalisedWords = normalizationService.normalise(sentence);
			fw = outputService.writeFileFromNormalisedObject(normalisedWords);
		}
		return fw;
	}
}
