package vn.hoidanit.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecs {

    public static Specification<Product> nameLike(String name) {
        return (root, query, criterialBuilder) -> criterialBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> minPrice(double price) {
        return (root, query, criterialBuilder) -> criterialBuilder.ge(root.get(Product_.PRICE), price);
    }

    public static Specification<Product> maxPrice(double price) {
        return (root, query, criterialBuilder) -> criterialBuilder.le(root.get(Product_.PRICE), price);
    }

    public static Specification<Product> matchFactory(String factory) {
        return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get(Product_.FACTORY), factory);
    }

    public static Specification<Product> matchListFactory(List<String> factory) {
        return (root, query, criterialBuilder) -> criterialBuilder.in(root.get(Product_.FACTORY)).value(factory);
    }

    public static Specification<Product> matchListTarget(List<String> target) {
        return (root, query, criterialBuilder) -> criterialBuilder.in(root.get(Product_.TARGET)).value(target);
    }

    public static Specification<Product> matchPrice(double min, double max) {
        return (root, query, criterialBuilder) -> criterialBuilder
                .and(criterialBuilder.gt(root.get(Product_.PRICE), min),
                        criterialBuilder.le(root.get(Product_.PRICE), max));
    }

    public static Specification<Product> matchMultiplePrice(double min, double max) {
        return (root, query, criterialBuilder) -> criterialBuilder.between(root.get(Product_.PRICE), min, max);
    }

}
