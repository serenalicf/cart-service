package online.shopping.system.cart_service.customer.service;


import online.shopping.system.cart_service.customer.dto.CustomerDto;
import online.shopping.system.cart_service.helper.RestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Value("${customer.rest.uri}")
    String customerRestUri;

    @Autowired
    RestHelper restHelper;

    @Override
    public CustomerDto getCustomer(Integer customerId) {
        return restHelper.getObject(customerRestUri + "/customers/" + customerId, CustomerDto.class);
    }

}
