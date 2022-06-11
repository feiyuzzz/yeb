package com.ez.mail;

import com.yeb.server.common.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class YebMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebMailApplication.class,args);
    }

    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }


}
