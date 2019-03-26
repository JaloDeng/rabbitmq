package com.test.rabbitmq.Receiver.Runner;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.rabbitmq.RabbitmqApplication;
import com.test.rabbitmq.Receiver.Receiver;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	
	private final Receiver receiver;

	public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
		this.rabbitTemplate = rabbitTemplate;
		this.receiver = receiver;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}
	
}
