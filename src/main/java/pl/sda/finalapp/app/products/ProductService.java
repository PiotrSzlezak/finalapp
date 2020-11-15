package pl.sda.finalapp.app.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public void addProduct(ProductDTO productDTO) {
        productRepository.save(Product.fromDTO(productDTO));
    }

    public List<ProductListDTO> allProducts() {
        return productRepository.findAll().stream()
                .map(p -> createProductListDTO(p))
                .collect(Collectors.toList());
    }

    private ProductListDTO createProductListDTO(Product p) {
        String catNameWithId = categoryService.findCategoryNameById(p.getCategoryId())
                 .map(cn -> p.getCategoryId() + " " + cn)
                 .orElse("BŁĄD! Kategoria produktu o ID= "+ p.getCategoryId() +" nie istnieje.");
        return p.toListDTO(catNameWithId);
    }

    public ProductDTO findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(p -> p.toDTO())
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public void editProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ProductNotFoundException(productDTO.getId()));
        product.apply(productDTO);
        productRepository.save(product);
    }
}
