package com.microservice.inventoryservice.repository;

import com.microservice.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query(value = "SELECT * FROM public.t_inventory where sku_code IN (:skuCodes)", nativeQuery = true)
    List<Inventory> findBySkuCodeIn(@Param("skuCodes") List<String> skuCode);

    @Query(value = "SELECT * FROM public.t_inventory where sku_code = (:skuCode)", nativeQuery = true)
    Inventory findBySkuCode(@Param("skuCode") String skuCode);
}