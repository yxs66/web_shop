package com.yyy.springboot.controller;

import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.entitys.Cart;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.CartService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/5/4 20:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Result<List<Cart>> carts(){
        List<Cart> carts = cartService.carts();
        if (CollectionUtils.isEmpty(carts)) {
            return ResultUtil.success();
        }else{
            return ResultUtil.success(carts);
        }
    }

    @PostMapping
    public Result addCart(@RequestBody ProductSpecificationIdDTO productSpecificationIdDTO) {
        cartService.addCart(productSpecificationIdDTO);
        return ResultUtil.success();
    }

    @DeleteMapping
    public Result delCart(@RequestBody List<ProductSpecificationIdDTO> productSpecificationIdDTO) {
        cartService.delCart(productSpecificationIdDTO);
        return ResultUtil.success();
    }

    @PutMapping("/{type}")
    public Result inOrDecrementCartNum(@RequestBody ProductSpecificationIdDTO productSpecificationIdDTO, @PathVariable("type") Byte type){
        cartService.inOrDecrementCartNum(productSpecificationIdDTO, type);
        return ResultUtil.success();
    }

}
