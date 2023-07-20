package com.giangnam.vn.Ecommerce.Website.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giangnam.vn.Ecommerce.Website.Entity.Variation_Option;

@Repository
public interface Variation_OptionRepository extends JpaRepository<Variation_Option, Integer>{

}
