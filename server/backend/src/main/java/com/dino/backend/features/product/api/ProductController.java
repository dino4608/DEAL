package com.dino.backend.features.product.api;

import com.dino.backend.features.product.application.model.projection.ProductProj;
import com.dino.backend.features.product.application.model.request.ProductReq;
import com.dino.backend.features.product.application.IProductAppService;
import com.dino.backend.features.product.domain.entity.Product;
import com.dino.backend.shared.model.PageReq;
import com.dino.backend.shared.model.PageRes;
import com.dino.backend.shared.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    //ADMIN//
    @RestController
    @RequestMapping("/admin/api/v1/product")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class ProductAdminController {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product for admin");
        }
    }

    //SELLER//
    @RestController
    @RequestMapping("/seller/api/v1/product")
    @PreAuthorize("hasRole('SELLER')")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class ProductSellerController {
        IProductAppService productAppService;

        //CREATE//
        @PostMapping("/create")
        public ResponseEntity<Product> create(
                @RequestBody @Valid ProductReq.Create productDto
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.productAppService.create(productDto));
        }

        //LIST//
        @GetMapping("/list")
        public ResponseEntity<PageRes<ProductProj>> findAll(
                @ModelAttribute PageReq pageReq
        ) {
            return ResponseEntity
                    .ok()
                    .body(this.productAppService.findAll(AppUtils.toPageable(pageReq)));
        }
    }

    //BUYER//
    @RestController
    @RequestMapping("/api/v1/product")
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class ProductBuyerController {
        IProductAppService productAppService;

        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product on SSHOP");
        }

        //FIND//
        @GetMapping("/find")
        public ResponseEntity<Object> find() {
            return ResponseEntity
                    .ok()
                    .body(null);
        }
    }
}
