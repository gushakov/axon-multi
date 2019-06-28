package com.github.axonmulti.common.config;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionalAmqpMessageSource extends SpringAMQPMessageSource {
    private final TransactionTemplate transactionTemplate;

    public TransactionalAmqpMessageSource(AMQPMessageConverter messageConverter, TransactionTemplate transactionTemplate) {
        super(messageConverter);
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    @Transactional
    @RabbitListener(queues = "axonQueue")
    public void onMessage(Message message, Channel channel) {
        super.onMessage(message, channel);

    }
}
