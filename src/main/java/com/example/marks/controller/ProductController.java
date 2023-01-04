package com.example.marks.controller;

import com.example.marks.model.Product;
import com.example.marks.model.Role;
import com.example.marks.model.User;
import com.example.marks.repository.ProductRepository;
import com.example.marks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;


    private Iterable<Product> products = new ArrayList<>(
//            List.of(
//                    new Product(1L,  "Iron", "Samsung",20, new Product().getAuthorName()),
//                    new Product(2L, "Waschmaschine", "Siemens", 340),
//                    new Product(3L, "Iphone 13 Pro", "Maxwell", 690),
//                    new Product(4L, "MacBook Air 2018", "15d, 256Gb", 750),
//                    new Product(5L, "Lord Of Rings", "2017, BookMarket", 15)
//                    )
    );
    @GetMapping("/")
    public String main(Model model,
                       @RequestParam(required = false) String filter,
                       @AuthenticationPrincipal User user){

        if(filter != null && !filter.isEmpty())
            products = productRepository.findByNameContaining(filter);
        else{
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "main";
    }
    @GetMapping("/add")
    public String add(Product product){
        return "add-product";
    }
    @PostMapping("/add")
    public String adding(
            @AuthenticationPrincipal User user,
            @ModelAttribute Product product,
            BindingResult result

    ){
        if (result.hasErrors())
            return "add-product";
        product.setAuthor(user);
        productRepository.save(product);
        return "redirect:/";
    }



    @GetMapping("/edit/{id}")
    public String editing(@PathVariable(name = "id") Long id,
                          @AuthenticationPrincipal User user,
                          Model model){

        Product product = productRepository.findById(id).get();
        if(!user.isUser(user.getRoles(), product.getAuthor().getRoles()))
            return "redirect:/";
        model.addAttribute("product", product);
        return "edit-product";
    }
    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Product product,
                       @AuthenticationPrincipal User user,
                       @PathVariable(name = "id") Long id,
                       BindingResult result)
    {
        if(result.hasErrors()) {
            product.setId(id);
            return "edit-product";
        }
        product.setAuthor(user);
        productRepository.save(product);
        return "redirect:/";
    }
    @GetMapping("/product/{id}")
    public String productDet(@PathVariable(name = "id") Long id,
                             Model model,
                             @AuthenticationPrincipal User user
    ){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "product-details";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProductById(@PathVariable(name="id") Long id
    ){
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
