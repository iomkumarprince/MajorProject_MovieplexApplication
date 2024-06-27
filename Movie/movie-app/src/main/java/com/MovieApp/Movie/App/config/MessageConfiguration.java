package com.MovieApp.Movie.App.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchangeName = "UserMovieExchange";
    private String queueName = "EmailQueue";
    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue getQueue(){
        return  new Queue(queueName);
    }
    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate getrabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getJackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Binding getBinding(){
        return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("Movie_routing");
    }

}
