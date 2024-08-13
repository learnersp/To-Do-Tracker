//package com.ToDoTracker.config;
//
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Producer {
//    private RabbitTemplate rabbitTemplate;
//    private DirectExchange directExchange;
//
//    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.directExchange = directExchange;
//    }
//
//    public void sendUserMsg (UserDTO userDTO) {
//        rabbitTemplate.convertAndSend(directExchange.getName(),"usertaskroutingkey", userDTO);
//    }
//
//    public void sendTaskMsg (TaskDTO taskDTO) {
//        rabbitTemplate.convertAndSend(directExchange.getName(),"usertaskroutingkey", taskDTO);
//    }
//}
