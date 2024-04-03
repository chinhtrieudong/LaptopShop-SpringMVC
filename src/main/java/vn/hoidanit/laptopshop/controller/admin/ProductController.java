package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        model.addAttribute("hoidanit", this.productService.getAllProduct());
        return "/admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postCreateProduct(@ModelAttribute("newProduct") Product hoidanit,
            @RequestParam("hoidanitFile") MultipartFile file) {
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        hoidanit.setImage(image);
        this.productService.handleSaveProduct(hoidanit);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProductPage(Model model, @PathVariable long id) {
        Product hoidanit = this.productService.getProductById(id);
        model.addAttribute("productInfo", hoidanit);
        return "/admin/product/detail";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product delProduct = this.productService.getProductById(id);
        model.addAttribute("delProduct", delProduct);
        return "/admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(@ModelAttribute("delProduct") Product delProduct) {
        this.productService.handleDelProduct(delProduct.getId());
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product hoidanit = this.productService.getProductById(id);
        model.addAttribute("newProduct", hoidanit);
        return "/admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(@ModelAttribute("newProduct") Product hoidanit,
            @RequestParam("hoidanitFile") MultipartFile file) {
        System.out.println("run here " + hoidanit.getId());
        Product curProduct = this.productService.getProductById(hoidanit.getId());

        if (hoidanit != null) {
            curProduct.setId(hoidanit.getId());
            curProduct.setName(hoidanit.getName());
            curProduct.setPrice(hoidanit.getPrice());
            curProduct.setDetailDesc(hoidanit.getDetailDesc());
            curProduct.setShortDesc(hoidanit.getShortDesc());
            curProduct.setQuantity(hoidanit.getQuantity());
            curProduct.setFactory(hoidanit.getFactory());
            curProduct.setTarget(hoidanit.getTarget());
            curProduct.setImage(this.uploadService.handleSaveUploadFile(file,
                    "product"));
            this.productService.handleSaveProduct(curProduct);
        }
        return "redirect:/admin/product";
    }

}
