package online.shopping.system.cart_service.service;

import lombok.extern.slf4j.Slf4j;
import online.shopping.system.cart_service.annotation.ValidCustomer;
import online.shopping.system.cart_service.constant.AddCartStatusCode;
import online.shopping.system.cart_service.customer.dto.CustomerDto;
import online.shopping.system.cart_service.customer.service.CustomerService;
import online.shopping.system.cart_service.dto.AddCartItemResponseDto;
import online.shopping.system.cart_service.dto.CreateCartItemRequestDTO;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.entity.CartItem;
import online.shopping.system.cart_service.exception.BusinessException;
import online.shopping.system.cart_service.exception.constant.ErrorCode;
import online.shopping.system.cart_service.product.dto.ProductDto;
import online.shopping.system.cart_service.product.service.ProductService;
import online.shopping.system.cart_service.repository.CartItemRepository;
import online.shopping.system.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemRepository cartItemRepository;
    
    @Override
    public Cart createCart(String customerId) throws BusinessException {
        //get latest cart
        Cart cart = getLatestCart(customerId);

        //if latest cart is empty , create an empty cart
        if(cart == null) {
            LocalDateTime currentTime = LocalDateTime.now();
            cart = Cart.builder()
                    .customerId(Integer.parseInt(customerId))
                    .totalPrice(BigDecimal.valueOf(0.0))
                    .items(new ArrayList<>())
                    .createdOn(currentTime)
                    .lastModifiedOn(currentTime)
                    .build();
            try {
                cartRepository.save(cart);
            } catch (Exception ex){
                log.error(ex.getMessage(), ex);
                throw new BusinessException(ErrorCode.SYSTEM_BUSY);
            }
        }
        return cart;
    }

    @Override
    public Cart getLatestCart(String customerId) throws BusinessException {
        try {
//            call customer api
            CustomerDto customer = Optional.ofNullable(customerService.getCustomer(customerId))
                    .orElseThrow(()->new BusinessException(ErrorCode.CUSTOMER_NOT_FOUND, customerId));


            return cartRepository.findFirstByCustomerIdOrderByLastModifiedOnDesc(Integer.parseInt(customerId)).orElse(null);

        } catch (Exception ex){
            if(ex instanceof BusinessException){
                throw ex;
            }
            log.error(ex.getMessage(), ex);
            throw new BusinessException(ErrorCode.SYSTEM_BUSY);
        }
    }

    @Override
    public AddCartItemResponseDto addItemToCart(String customerId, String cartId, CreateCartItemRequestDTO createCartItemRequestDTO) throws BusinessException {
        try {
           // call customer api
           Optional.ofNullable(customerService.getCustomer(customerId))
                    .orElseThrow(()->new BusinessException(ErrorCode.CUSTOMER_NOT_FOUND, customerId));

            //check cart exist
            Cart cart =  cartRepository.findByCartIdAndCustomerIdOrderByLastModifiedOnDesc(Integer.parseInt(cartId), Integer.parseInt(customerId))
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_FOUND, cartId));


            ProductDto productDto = Optional.ofNullable(productService.getProduct(createCartItemRequestDTO.getProductCode()))
                    .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, createCartItemRequestDTO.getProductCode()));

            //check product availability
            if (productDto.getAvailableItemCount() < createCartItemRequestDTO.getQuantity()) {
                throw new BusinessException(ErrorCode.INSUFFICIENT_PRODUCT, createCartItemRequestDTO.getProductCode());
            }

            //get cart items
            List<CartItem> cartItemList = cart.getItems();

            CartItem cartItem = cartItemList.stream()
                        .filter(item -> item.getProductCode().equals(createCartItemRequestDTO.getProductCode()))
                        .findFirst()
                        .orElse(null);

            //create a new cart item if cartItem is null
            Integer quantity = createCartItemRequestDTO.getQuantity();
            BigDecimal price = productDto.getPrice();
            AddCartItemResponseDto addCartItemResponseDto = AddCartItemResponseDto.builder()
                    .quantityAdded(createCartItemRequestDTO.getQuantity())
                    .productCode(createCartItemRequestDTO.getProductCode())
                    .build();
            if (cartItem == null) {
                cartItem = CartItem.builder()
                        .productCode(createCartItemRequestDTO.getProductCode())
                        .quantity(quantity)
                        .price(price)
                        .totalPrice(BigDecimal.valueOf(quantity).multiply(price))
                        .createdOn(LocalDateTime.now())
                        .lastModifiedOn(LocalDateTime.now())
                        .cart(cart)
                        .build();
                cartItemRepository.save(cartItem);
                cart.getItems().add(cartItem);
                addCartItemResponseDto.setItemId(cartItem.getItemId());
                addCartItemResponseDto.setQuantity(createCartItemRequestDTO.getQuantity());

            } else {
                Integer newQuantity = cartItem.getQuantity() + quantity;
                cartItem.setQuantity(newQuantity);
                cartItem.setTotalPrice(BigDecimal.valueOf(newQuantity).multiply(price));
                addCartItemResponseDto.setItemId(cartItem.getItemId());
                addCartItemResponseDto.setQuantity(cartItem.getQuantity());
            }
            calulateCart(cart);
            addCartItemResponseDto.setStatusCode(AddCartStatusCode.SUCCESS.name());
            return addCartItemResponseDto;
        } catch (Exception ex){
            AddCartItemResponseDto addCartItemResponseDto = AddCartItemResponseDto.builder()
                    .productCode(createCartItemRequestDTO.getProductCode())
                    .statusCode(AddCartStatusCode.FAILED.name())
                    .build();
            if(ex instanceof BusinessException){
                addCartItemResponseDto.setErrorCode(((BusinessException) ex).getErrorCode());
            } else {
                log.error(ex.getMessage(), ex);
                addCartItemResponseDto.setErrorCode(ErrorCode.SYSTEM_BUSY.getErrorCode());
            }
            return addCartItemResponseDto;
        }
    }

    public void calulateCart(Cart cart){
        //loop the cart items to calulate total price for the cart, update the total price for the cart
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CartItem item : cart.getItems()){
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        cart.setTotalPrice(totalPrice);
        // save the cart
        cartRepository.save(cart);

    }
}
