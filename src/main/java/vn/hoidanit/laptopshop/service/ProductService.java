package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cardDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cardDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cardDetailRepository = cardDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Page<Product> fetchProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    // public Page<Product> fetchProductsWithSpec(Pageable page, String name) {
    // return this.productRepository.findAll(ProductSpecs.nameLike(name), page);
    // }

    // public Page<Product> fetchProductsWithSpec(Pageable page, double price) {
    // return this.productRepository.findAll(ProductSpecs.minPrice(price), page);
    // }
    // public Page<Product> fetchProductsWithSpec(Pageable page, String factory) {
    // return this.productRepository.findAll(ProductSpecs.matchFactory(factory),
    // page);
    // }
    // public Page<Product> fetchProductsWithSpec(Pageable page, List<String>
    // factory) {
    // return this.productRepository.findAll(ProductSpecs.matchListFactory(factory),
    // page);
    // }

    // public Page<Product> fetchProductsWithSpec(Pageable page, String price) {
    // if (price.equals("10-toi-15-trieu")) {
    // double min = 10000000;
    // double max = 15000000;

    // return this.productRepository.findAll(ProductSpecs.matchPrice(min, max),
    // page);
    // } else if (price.equals("15-toi-30-trieu")) {
    // double min = 15000000;
    // double max = 30000000;

    // return this.productRepository.findAll(ProductSpecs.matchPrice(min, max),
    // page);
    // }
    // return this.productRepository.findAll(page);
    // }

    // case6
    // public Page<Product> fetchProductsWithSpec(Pageable page, List<String> price)
    // {
    // Specification<Product> combinedSpec = (root, query, criterialBuilder) ->
    // criterialBuilder.disjunction();
    // int count = 0;
    // for (String p : price) {
    // double min = 0;
    // double max = 0;
    // switch (p) {
    // case "10-toi-15-trieu":
    // min = 10000000;
    // max = 15000000;
    // count++;
    // break;
    // case "15-toi-20-trieu":
    // min = 15000000;
    // max = 20000000;
    // count++;
    // break;
    // case "20-toi-30-trieu":
    // min = 20000000;
    // max = 30000000;
    // count++;
    // break;
    // }
    // if (min != 0 & max != 0) {
    // Specification<Product> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
    // combinedSpec = combinedSpec.or(rangeSpec);
    // }
    // }
    // if (count == 0) {
    // return this.productRepository.findAll(page);
    // }
    // return this.productRepository.findAll(combinedSpec, page);
    // }

    public void handleSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public void handleDelProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user có Cart hay chưa ? nếu chưa -> tạo mới
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            // find product
            Optional<Product> product = this.productRepository.findById(productId);

            if (product.isPresent()) {
                Product realProduct = product.get();

                // check sản phẩm đã được thêm vào giỏ hàng hay chưa
                CartDetail oldDetail = this.cardDetailRepository.findByCartAndProduct(cart, realProduct);

                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(quantity);
                    this.cardDetailRepository.save(cartDetail);

                    // update cart (sum)
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                    this.cardDetailRepository.save(oldDetail);
                }
            }
        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cardDetailRepository.findById(cartDetailId);

        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();

            this.cardDetailRepository.deleteById(cartDetailId);

            if (currentCart.getSum() > 1) {
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // sum = 1
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckOut(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cardDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail curCartDetail = cdOptional.get();
                curCartDetail.setQuantity(cartDetail.getQuantity());
                this.cardDetailRepository.save(curCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) {
        // get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {

                // create order
                Order curOrder = new Order();
                curOrder.setUser(user);
                curOrder.setReceiverName(receiverName);
                curOrder.setReceiverAddress(receiverAddress);
                curOrder.setReceiverPhone(receiverPhone);
                curOrder.setStatus("PENDING");

                double totalPrice = 0;
                for (CartDetail cd : cartDetails) {
                    totalPrice += cd.getPrice() * cd.getQuantity();
                }

                curOrder.setTotalPrice(totalPrice);
                Order order = this.orderRepository.save(curOrder);

                // create orderDetail
                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }

                // xóa cart & cartDetail
                for (CartDetail cd : cartDetails) {
                    this.cardDetailRepository.deleteById(cd.getId());
                }
                this.cartRepository.deleteById(cart.getId());

                // update session
                session.setAttribute("sum", 0);
            }
        }
    }
}
