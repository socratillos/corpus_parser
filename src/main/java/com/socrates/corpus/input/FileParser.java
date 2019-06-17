package com.socrates.corpus.input;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class FileParser {
	
	public String readFile(File file) throws IOException {
		
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}
	
	public List<String> readLinesFromFile(File file) throws IOException {
		return FileUtils.readLines(file, StandardCharsets.UTF_8);
	}
	
	public List<String> splitLines(String line) {
		String[] parts = line.split("\\s+");
		return Arrays.asList(parts);
	}
	
	public boolean isNewSentence(final String line) {
		String trimLine = line.trim();
		return trimLine.isEmpty();
	}
}
