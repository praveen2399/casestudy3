package com.tcs.hack.config;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.tcs.hack.model.Inventory;

@Configuration
@EnableBatchProcessing
public class SpringbatchConfig {
	
	public static String batchStep="";
	
	@Autowired
	DataSource dataSource;
	
	
	

	@Bean
	@Qualifier("File-Load")
	public Job job(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,ItemReader<Inventory> itemReader,
			ItemWriter<Inventory> itemWriter,ItemProcessor<Inventory, Inventory> itemProcessor)
	{
		
		
		Step step=stepBuilderFactory.get("File-Load")
				.<Inventory,Inventory>chunk(5)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("inventoryLoad")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<Inventory> itemReader()
	{
		System.out.println("Job id is "+batchStep);
		FlatFileItemReader<Inventory> flatFileItemReader = new FlatFileItemReader<>();
		
		flatFileItemReader.setResource(new ClassPathResource("inventory.csv"));
		flatFileItemReader.setName("CSV-File");
		flatFileItemReader.setLineMapper(lineMapper());
		
		
		return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<Inventory> lineMapper() {
		
		DefaultLineMapper<Inventory> defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedTokensizer = new DelimitedLineTokenizer();
		
		delimitedTokensizer.setDelimiter(",");
		delimitedTokensizer.setStrict(false);
		delimitedTokensizer.setNames(new String[] { "skuId", "productName", "productLabel","inventoryOnHand","minQtyReq","price" });
		
		BeanWrapperFieldSetMapper<Inventory> fieldSetMapper =new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Inventory.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(delimitedTokensizer);
		
		return defaultLineMapper;
	}
	
	@Bean
	@Qualifier("Inventory-Check")
	public Job job2(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,ItemReader<Inventory> databaselItemReader,
			ItemWriter<Inventory> itemWriter2,ItemProcessor<Inventory, Inventory> itemProcessor2)
	{
		
		
		Step step2=stepBuilderFactory.get("Inventory-Check")
				.<Inventory,Inventory>chunk(5)
				.reader(databaselItemReader)
				.processor(itemProcessor2)
				.writer(itemWriter2)
				.build();
		
		return jobBuilderFactory.get("Inventory-Check")
				.incrementer(new RunIdIncrementer())
				.start(step2)
				.build();
	}
	
	@Bean
    ItemReader<Inventory> databaselItemReader() {
        JdbcCursorItemReader<Inventory> databaseReader = new JdbcCursorItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql("Select * from Inventory where inventory_on_hand <= min_qty_req");
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(Inventory.class));
 
        return databaseReader;
    }
	
	
	/*@Bean
	public FlatFileItemReader<Inventory> itemReader2()
	{
		System.out.println("Job id is "+batchStep);
		FlatFileItemReader<Inventory> flatFileItemReader = new FlatFileItemReader<>();
		
		flatFileItemReader.setResource(new ClassPathResource("inventory2.csv"));
		flatFileItemReader.setName("CSV-File");
		flatFileItemReader.setLineMapper(lineMapper());
		
		
		return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<Inventory> lineMapper2() {
		
		DefaultLineMapper<Inventory> defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedTokensizer = new DelimitedLineTokenizer();
		
		delimitedTokensizer.setDelimiter(",");
		delimitedTokensizer.setStrict(false);
		delimitedTokensizer.setNames(new String[] { "skuId", "productName", "productLabel","inventoryOnHand","minQtyReq","price" });
		
		BeanWrapperFieldSetMapper<Inventory> fieldSetMapper =new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Inventory.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(delimitedTokensizer);
		
		return defaultLineMapper;
	}*/
	
	
	
}
