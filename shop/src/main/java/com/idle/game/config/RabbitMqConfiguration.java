package com.idle.game.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 *
 * @author rafael
 */
@Configuration
@Profile({"default"})
@EnableRabbit
public class RabbitMqConfiguration implements RabbitListenerConfigurer {

    @Value("${idle.config.amq.hostname}")
    private String hostAmq;
    @Value("${idle.config.amq.port}")
    private Integer portAmq;
    @Value("${idle.config.amq.username}")
    private String userAmq;
    @Value("${idle.config.amq.password}")
    private String passAmq;

    /**
     * Metodo responsavel por criar o factory de conexão do RabbitMq
     *
     * @return CachingConnectionFactory - Objeto de conexão para acesso ao
     * serviço do RabbitMQ.
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostAmq, portAmq);
        connectionFactory.setUsername(userAmq);
        connectionFactory.setPassword(passAmq);
        return connectionFactory;
    }

    /**
     *
     * Obrigatório para executar funções de administração do Broker de
     * mensageria.
     *
     * @return AmqpAdmin - Objeto de conexão para acesso ao serviço do RabbitMQ.
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * Metodo responsavel por garantir que dois ou mais consumidores não
     * consigam consumir a mesma mensagem da fila de cardex em paralelo.
     *
     * @return SimpleRabbitListenerContainerFactory - Objeto que possui os
     * parametros que garantem a regra durante o processamento da fila de cardex
     * interno pendente
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    /**
     * Metodo responsavel por converter a mensagem retornando um objeto do tipo
     * MessageConverter, necessário para o correto funcionamento do metodo
     * rabbitListenerContainerFactory.
     *
     * @return MessageConverter - Objeto que possui os dados da mensagem
     * convertida
     */
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
