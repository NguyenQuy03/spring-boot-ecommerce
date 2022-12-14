package com.ecommerce.springbootecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.springbootecommerce.dto.CategoryDTO;
import com.ecommerce.springbootecommerce.service.ICategoryService;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping(value="/account/adminAccount")
    public String adminAccount() {
        return "manager/account/adminAccount";
    }

    @GetMapping(value="/account/sellerAccount")
    public String sellerAccount() {
        return "manager/account/sellerAccount";
    }
    
    @GetMapping(value="/account/buyerAccount")
    public String buyerAccount() {
        return "manager/account/buyerAccount";
    }

    @GetMapping(value="/category")
    public String categories(
            Model model,
            @RequestParam int page,
            @RequestParam int size
        ) {
        
        long quantityCategory = categoryService.count();
        Pageable pageable = PageRequest.of(page - 1, size);
        List<CategoryDTO> listCategory = categoryService.findAll(pageable);
        Integer totalPage = (int) Math.ceil((double) quantityCategory / size);
        
        CategoryDTO dto = new CategoryDTO();
        dto.setTotalPage(totalPage);
        dto.setListResult(listCategory);
        dto.setPage(page);
        dto.setSize(size);

        model.addAttribute("quantityCategory", quantityCategory);
        model.addAttribute("dto", dto);
                
        return "manager/category/categories";
    }

    @GetMapping(value="/transaction")
    public String transactions() {
        return "manager/transactions";
    }

    @GetMapping(value="/category/new")
    public String newCategory(Model model) {
        CategoryDTO category = new CategoryDTO();
        
        model.addAttribute("category", category);
        return "manager/category/newCategory";
    }
}
