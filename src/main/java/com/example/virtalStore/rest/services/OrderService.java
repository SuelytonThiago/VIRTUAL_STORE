package com.example.virtalStore.rest.services;

import com.example.virtalStore.configs.security.JWTService;
import com.example.virtalStore.domain.entities.Order;
import com.example.virtalStore.domain.entities.OrderItems;
import com.example.virtalStore.domain.entities.Product;
import com.example.virtalStore.domain.enums.RequestStatus;
import com.example.virtalStore.domain.repositories.ClientsRepository;
import com.example.virtalStore.domain.repositories.OrderItemsRepository;
import com.example.virtalStore.domain.repositories.OrderRepository;
import com.example.virtalStore.domain.repositories.ProductRepository;
import com.example.virtalStore.rest.dto.OrderItemsRequestDto;
import com.example.virtalStore.rest.dto.OrderItemsResponseDto;
import com.example.virtalStore.rest.dto.OrderResponseDto;
import com.example.virtalStore.rest.services.exceptions.CustomException;
import com.example.virtalStore.rest.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private JWTService jwtService;

    @Transactional
    public void insertNewOrder(List<OrderItemsRequestDto> itemsDto,HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        var token = auth.replace("Bearer ","");
        var email = jwtService.getSubject(token);
        var client = clientsRepository.findByEmail(email).get();

        Order order = new Order();
        order.setClients(client);
        order.setInstant(LocalDate.now());
        order.setStatus(RequestStatus.ORDER_PLACED);
        List<OrderItems> items = convertList(order,itemsDto);
        orderRepository.save(order);
        orderItemsRepository.saveAll(items);
        order.setOrderItems(items);
    }

    private List<OrderItems> convertList(Order order,List<OrderItemsRequestDto> list) {
        if(list.isEmpty()){
            throw new CustomException("it is not possible to place an order without items");
        }
        return list.stream().map(item ->{
            Product product = productRepository.findById(item.getIdProduct())
                    .orElseThrow(() -> new ObjectNotFoundException("Product not found"));

            OrderItems orderItems = new OrderItems();
            orderItems.setPrice(product.getPrice());
            orderItems.setOrder(order);
            orderItems.setProduct(product);
            orderItems.setQuantity(item.getQuantity());
            return orderItems;
        }).collect(Collectors.toList());
    }

    public List<OrderResponseDto> findAllOrders(HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        var token = auth.replace("Bearer ","");
        var email = jwtService.getSubject(token);
        var client = clientsRepository.findByEmail(email).get();
        return orderRepository.findByClients(client).stream().map(order ->{
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setInstant(order.getInstant().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            orderResponseDto.setStatus(order.getStatus().toString());
            orderResponseDto.setTotal(order.getTotal());
            orderResponseDto.setItems(convertDTOList(order.getOrderItems()));
            return orderResponseDto;
        }).collect(Collectors.toList());
    }

    private List<OrderItemsResponseDto> convertDTOList(List<OrderItems> list){
        if(list.isEmpty()){
            throw new CustomException("you don't have any requests");
        }
        return list.stream().map(item ->{
            OrderItemsResponseDto itemsDto = new OrderItemsResponseDto();
            itemsDto.setPrice(item.getPrice());
            itemsDto.setQuantity(item.getQuantity());
            itemsDto.setSubtotal(item.getSubTotal());
            itemsDto.setProductName(item.getProduct().getName());
            return itemsDto;
        }).collect(Collectors.toList());
    }

}
