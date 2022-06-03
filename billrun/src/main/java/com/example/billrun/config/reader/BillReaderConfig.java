package com.example.billrun.config.reader;

import com.example.billrun.config.dominio.Usage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class BillReaderConfig {

	@Value("${usage.file.name:classpath:usageinfo.json}")
	private Resource usageResource;

	@Bean
	public JsonItemReader<Usage> billReader(){

		var objectMapper = new ObjectMapper();
		var jsonObjectReader = new JacksonJsonObjectReader<>(Usage.class);
		jsonObjectReader.setMapper(objectMapper);

		return new JsonItemReaderBuilder<Usage>()
			  .jsonObjectReader(jsonObjectReader)
			  .resource(usageResource)
			  .name("billReader")
			  .build();
	}



}
