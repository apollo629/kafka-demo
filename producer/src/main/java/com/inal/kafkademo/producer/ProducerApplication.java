package com.inal.kafkademo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
//		Properties config = new Properties();
//		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
//		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092");
//		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//		StreamsBuilder builder = new StreamsBuilder();
//		KStream<String, String> textLines = builder.stream("TextLinesTopic");
//		KTable<String, Long> wordCounts = textLines
//				.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//				.groupBy((key, word) -> word)
//				.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
//		wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
//
//		KafkaStreams streams = new KafkaStreams(builder.build(), config);
//		streams.start();

        SpringApplication application = new SpringApplication(ProducerApplication.class);
        application.run(args);
    }
}
