package com.ecommerce.springbootecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.springbootecommerce.converter.ProductConverter;
import com.ecommerce.springbootecommerce.dto.ProductDTO;
import com.ecommerce.springbootecommerce.entity.AccountEntity;
import com.ecommerce.springbootecommerce.entity.CategoryEntity;
import com.ecommerce.springbootecommerce.entity.ProductEntity;
import com.ecommerce.springbootecommerce.repository.AccountRepository;
import com.ecommerce.springbootecommerce.repository.CategoryRepository;
import com.ecommerce.springbootecommerce.repository.ProductRepository;
import com.ecommerce.springbootecommerce.service.IProductService;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public void save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();

        if (productDTO.getId() != null) {
            ProductEntity preProductEntity = productRepository.findOneById(productDTO.getId());
            if (productDTO.getImage() == null) {
                productDTO.setImage(preProductEntity.getImage());                
            }
            productEntity = productConverter.toEntity(productDTO, preProductEntity);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();

            productEntity = productConverter.toEntity(productDTO);
            CategoryEntity categoryEntity = categoryRepository.findOneById(productDTO.getCategoryId());
            AccountEntity accountEntity = accountRepository.findOneByUserName(currentPrincipalName);

            productEntity.setCategory(categoryEntity);
            productEntity.setAccount(accountEntity);

        }

        productRepository.save(productEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            productRepository.deleteById(id);
        }
    }
    
    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductEntity> listProductEntity = productRepository.findAll(pageable).getContent();
        List<ProductDTO> listProductDTO = productConverter.toListProductDTO(listProductEntity);
        return listProductDTO;
    }

    @Override
    public List<ProductDTO> findAllByAccountId(long accountId, Pageable pageable) {
        List<ProductEntity> listProductEntity = productRepository.findAllByAccountId(accountId, pageable).getContent();
        List<ProductDTO> listProductDTO = productConverter.toListProductDTO(listProductEntity);
        return listProductDTO;
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(long categoryId, Pageable pageable) {
        List<ProductEntity> listProductEntity = productRepository.findAllByCategoryId(categoryId, pageable).getContent();
        List<ProductDTO> listProductDTO = productConverter.toListProductDTO(listProductEntity);
        return listProductDTO;
    }

    @Override
    public List<ProductDTO> findByStockEquals(Integer stock, Pageable pageable) {
        List<ProductEntity> listProductEntity = productRepository.findByStockEquals(stock, pageable).getContent();
        List<ProductDTO> listProductDTO = productConverter.toListProductDTO(listProductEntity);

        return listProductDTO;
    }

    @Override
    public List<ProductDTO> findByStockGreaterThan(Integer stock, Pageable pageable) {
        List<ProductEntity> listProductEntity = productRepository.findByStockGreaterThan(stock, pageable).getContent();
        List<ProductDTO> listProductDTO = productConverter.toListProductDTO(listProductEntity);
        return listProductDTO;
    }

    @Override
    public long countAllByAccountId(long accountId) {
        return productRepository.countAllByAccountId(accountId);
    }

    @Override
    public long countByStockGreaterThan(Integer stock){
        return productRepository.countByStockGreaterThan(stock);
    }

    @Override
    public long countByStockEquals(Integer stock){
        return productRepository.countByStockEquals(stock);
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = productRepository.findOneById(id);
        ProductDTO productDTO = productConverter.toDTO(productEntity);
        return productDTO;
    }

    @Override
    public long countAllByCategoryId(Long categoryId) {
        return productRepository.countAllByCategoryId(categoryId);
    }

    @Override
    public long countByNameContains(String keyword) {
        return productRepository.countByNameContains(keyword);
    }

    @Override
    public List<ProductDTO> findAllByNameContains(String keyword, Pageable pageable) {
        List<ProductEntity> productEntities = productRepository.findAllByNameContains(keyword, pageable).getContent();
        List<ProductDTO> productDTOs = productConverter.toListProductDTO(productEntities);
        return productDTOs;
    }

}
