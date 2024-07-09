package online.shopping.system.cart_service.customer.service;

import online.shopping.system.cart_service.customer.dto.CustomerDto;

public interface CustomerService {

    CustomerDto getCustomer(Integer customerId);

}
