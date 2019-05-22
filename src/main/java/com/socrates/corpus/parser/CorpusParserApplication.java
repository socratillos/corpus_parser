package com.socrates.corpus.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.socrates.corpus"})
public class CorpusParserApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CorpusParserApplication.class, args);
		
	}

}
