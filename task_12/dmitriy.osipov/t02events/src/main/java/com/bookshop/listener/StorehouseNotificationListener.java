package com.bookshop.listener;

import com.bookshop.events.NewOrderEvent;
import com.bookshop.model.Order;
import com.bookshop.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StorehouseNotificationListener {

  @Autowired
  private MailService mailService;

  @Value("${storehouse.mail}")
  private String storehouseEmail;

  @Async
  @EventListener
  public void handleNewOrder(NewOrderEvent event) {
    //mailService.sendMail(storehouseEmail, "New Order", prepareContent(event));
    System.out.println("----------------------------Storehouse notified!----------");
    System.out.println(prepareContent(event));
  }

  private String prepareContent(NewOrderEvent event) {
    Order order = event.getOrder();
    StringBuilder builder = new StringBuilder(
        String.format("Order from: %s%n", order.getUser().getLogin()));
    builder.append(String.format("Contacts: %s%n", order.getContacts()))
        .append("Books:\n");

    order.getEntities().forEach(entity -> {
      builder.append(String.format("%s \t %d pcs", entity.getBook().toString(), entity.getCount()));
    });
    builder.append(String.format("Note: %s", order.getNote()));
    return builder.toString();
  }

}
