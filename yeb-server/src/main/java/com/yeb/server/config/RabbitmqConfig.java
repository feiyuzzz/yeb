package com.yeb.server.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yeb.server.common.MailConstants;
import com.yeb.server.pojo.MailLog;
import com.yeb.server.service.IMailLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Rabbitmq 控制类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@Slf4j(topic = "RabbitmqConfig")
@Configuration
public class RabbitmqConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private IMailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                log.info("{}=====>消息发送成功", msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 1).eq("msgId", msgId));
            } else {
                log.info("{}=====>消息发送失败",msgId);
            }
        });
        rabbitTemplate.setReturnCallback((msg,repCode,repTexr,exchange,routingKey)->{
            log.info("{}=====>消息发送queue时失败", msg.getBody());
        });
        return rabbitTemplate;
    }


    @Bean
    public Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public TopicExchange mailTopicExchange() {
        return new TopicExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(mailTopicExchange()).with("mail.#");
    }

}
