package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.mapper.CategoryMapper;
import restaurant_management_system.mapper.ProductMapper;
import restaurant_management_system.model.Category;
import restaurant_management_system.model.Product;
import restaurant_management_system.repo.ProductRepo;
import restaurant_management_system.service.ProductService;
import restaurant_management_system.vm.ProductsResponseVm;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final CategoryServiceImpl categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepo productRepo , CategoryServiceImpl categoryService
    , CategoryMapper categoryMapper)
    {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO _________________getAllProductsByCategoryId___________________
//TODO ______________________________________________________________

    @Override
    @Cacheable(value = "productsPages" , key = "'pageNumber' + #pageNumber + 'pageSize' + #pageSize + 'categId' + #categoryId")
    public ProductsResponseVm getAllProductsByCategoryId( int pageNumber , int pageSize, Long categoryId)
    {

        validatePageNumberAndSize(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);


        Page<Product> productOptional = productRepo.findAllByCategoryIdOrderById(categoryId ,pageable);


        if(productOptional.getContent().isEmpty())
        {
            throw new RuntimeException("No.Products.Found");
        }


        return new ProductsResponseVm(productMapper.toDtoList(productOptional.getContent()) ,productOptional.getTotalElements());
    }


//TODO _________________saveProduct__________________________________
//TODO ______________________________________________________________

    @Override
    @CacheEvict(value = "productsPages"  , allEntries = true)
    public ProductDto saveProduct(ProductDto productDto)
    {
      return helpSaveProduct(productDto);
    }


//TODO _________________help save Product__________________________________
//TODO ______________________________________________________________

    private ProductDto helpSaveProduct(ProductDto productDto)
    {
        if(Objects.nonNull(productDto.getId()))
        {
            throw new RuntimeException("id.Must.Be.Null");
        }

        if(productRepo.findByName(productDto.getName()).isPresent())
        {
            throw new RuntimeException("Product.Already.Exists");
        }

        Product product = productMapper.toEntity(productDto);

        Category category = categoryMapper.toEntity(categoryService.getCategoryByName(productDto.getCategoryName()));

        product.setCategory(category);

      product =  productRepo.save(product);

        return productMapper.toDto(product);
    }



//TODO _________________saveListOfProducts___________________________
//TODO ______________________________________________________________
    @Override
    @CacheEvict(value = "productsPages"  , allEntries = true)
    public List<ProductDto> saveListOfProducts(List<ProductDto> productDtoList)
    {
       return productDtoList.stream().map(this::helpSaveProduct).toList();
    }



//TODO _________________updateProduct________________________________
//TODO ______________________________________________________________

    @Override
    @CacheEvict(value = "productsPages", allEntries = true)
    @CachePut(value = "products"  , key = "'proId'+ #productDto.id")
    public ProductDto updateProduct(ProductDto productDto)
    {
       return helpUpdatePro2(productDto);
    }


//TODO _________________help Update Products ___________________________
//TODO ________________________________________________________________
   private ProductDto helpUpdatePro2(ProductDto productDto){
       if(Objects.isNull(productDto.getId()))
       {
           throw new RuntimeException("id.Must.Not.Be.Null");
       }

       Optional<Product> productOptional = productRepo.findById(productDto.getId());
       if(productOptional.isEmpty())
       {
           throw new RuntimeException("Product.Not.Found");
       }


       Product product = productMapper.toEntity(productDto);

       Category category = categoryMapper.toEntity(categoryService.getCategoryByName(productDto.getCategoryName()));

       product.setCategory(category);

       product =  productRepo.save(product);

       return productMapper.toDto(product);
   }



//TODO _________________updateListOfProducts___________________________
//TODO ________________________________________________________________

    @Override
    @CacheEvict(value = "productsPages", allEntries = true)
    public List<ProductDto> updateListOfProducts(List<ProductDto> productDtoList)
    {
        return  productDtoList.stream().map(this::helpUpdatePro2).toList();
    }



//TODO _________________deleteProduct__________________________________
//TODO ________________________________________________________________

    @Override
    @CacheEvict(value = {"productsPages" , "products"} , allEntries = true)
    public void deleteProduct(String productName)
    {
        Optional<Product> productOptional = productRepo.findByName(productName);

        if(productOptional.isEmpty())
        {
            throw new RuntimeException("Product.Not.Found");
        }

        productRepo.deleteById(productOptional.get().getId());
    }



//TODO _________________deleteListOfProducts___________________________
//TODO ________________________________________________________________

    @Override
    @CacheEvict(value = {"productsPages" , "products"} , allEntries = true)
    public void deleteListOfProducts(List<String> namesOfProducts)
    {
       List<Product> namesFound = productRepo.findAllByNameIn(namesOfProducts);

        List<String> names = namesFound
                .stream()
                .map(Product::getName).toList();

        List<String> resultValue =  namesOfProducts.stream().map(name ->{
            if(!names.contains(name))
            {
                throw new RuntimeException("Product.Not.Found" + name);
            }

            return name;
        }).toList();

        productRepo.deleteAllByNameIn(resultValue);
    }


//TODO _________________searchProducts_________________________________
//TODO ________________________________________________________________

    @Override
    @Cacheable(value = "productsPages"  , key = "'proKey'+ #key + 'pageNumber'+#pageNumber + 'pageSize' + #pageSize")
    public ProductsResponseVm searchProducts(int pageNumber , int pageSize , String key)
    {

        validatePageNumberAndSize(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Product> products = productRepo.searchProducts(key , pageable);


        if(products.getContent().isEmpty())
        {
            throw new RuntimeException("No.Products.Found.search");
        }


         return new ProductsResponseVm(productMapper.toDtoList(products.getContent()) ,products.getTotalElements());
    }



//TODO _________________getAllProducts_________________________________
//TODO ________________________________________________________________
    @Override
    @Cacheable(value = "productsPages" , key = "'pageNumber' + #pageNumber + 'pageSize' + #pageSize")
    public ProductsResponseVm getAllProducts(int pageNumber, int pageSize) {

        validatePageNumberAndSize(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Product> products = productRepo.findAll(pageable);


        if (products.getContent().isEmpty())
        {
            throw new RuntimeException("No.Products.Found");
        }


        return new ProductsResponseVm(productMapper.toDtoList(products.getContent()) ,products.getTotalElements());
    }



//TODO _________________getProductsByListOfId__________________________
//TODO ________________________________________________________________
    @Override
    public List<ProductDto> getProductsByListOfId(List<Long> ids)
    {
        if(ids.isEmpty())
        {
            throw new RuntimeException("id.Must.Not.Be.Empty");
        }

        List<Product> ProductOptional = productRepo.findAllById(ids);

        if(ProductOptional.isEmpty())
        {
            throw new RuntimeException("Product.Not.Found");
        }

        return productMapper.toDtoList(ProductOptional);
    }



//TODO _________________getProductById________________________________
//TODO ________________________________________________________________
    @Override
    @Cacheable(value = "products"  , key = "'proId'+ #id")
    public ProductDto getProductById(Long id)
    {
        if(Objects.isNull(id))
        {
            throw new RuntimeException("id.Must.Not.Be.Empty");
        }
        Optional<Product> productOptional = productRepo.findById(id);

        if(productOptional.isEmpty())
        {
            throw new RuntimeException("Product.Not.Found");
        }
        System.out.println( productOptional.get().getCategory().getName());


        ProductDto productDto = productMapper.toDto(productOptional.get());
        productDto.setCategoryName(productOptional.get().getCategory().getName());
        return productDto;
    }


    //TODO _________________validatePageNumberAndSize______________________
//TODO ________________________________________________________________
    boolean validatePageNumberAndSize(int pageNumber, int pageSize)
    {

        if (pageNumber < 1 || pageSize <= 0)
        {
            throw new IllegalArgumentException("page.number.invalid");
        }
        return true;
    }
}











