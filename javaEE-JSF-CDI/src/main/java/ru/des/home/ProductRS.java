package ru.des.home;

import ru.des.home.persist.Product;
import ru.des.home.service.ProductRepr;
import ru.des.home.service.ProductService;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Path("product")
public class ProductRS {

    @EJB
    private ProductService productService;

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<ProductRepr> getAllProduct(){
        return productService.findAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public ProductRepr findProduct(@PathParam("id") Long id){
        return productService.findById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/{title}")
    public String findProductByTitle(@PathParam("title") String title){
        return productService.findByTitle(title).toString();
    }

    @GET
    @Produces("application/json")
    @Path("/category/{id}")
    public List<Product> findProductByCategoryId(@PathParam("id") Long id){
        return productService.findAllByCategoryId(id);
    }

    @GET
    @Produces("application/json")
    @Path("/all/{id}")
    public ProductRepr deleteProduct(@PathParam("id") Long id){
        ProductRepr product = productService.findById(id);
        productService.deleteById(id);
        return product;
    }

    @POST
    //??
    @Consumes("product")
    public void addProduct(@FormParam("product") ProductRepr product){
        productService.saveOrUpdate(product);
    }
}
