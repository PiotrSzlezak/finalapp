package pl.sda.finalapp.app.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDTO productDTO) {
        productRepository.save(Product.fromDTO(productDTO));
    }
}
