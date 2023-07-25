package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Variation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(orphanRemoval = true, mappedBy = "variation")
	@JsonManagedReference
	private List<Variation_Option> variationOptionList;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	private Product_Category category;
	
	@NotNull
	private String name;
	
	public void addVariationOption(Variation_Option variation_Option) {
		variationOptionList.add(variation_Option);
		variation_Option.setVariation(this);
	}
	
	public void removeVariationOption(Variation_Option variation_Option) {
		variationOptionList.remove(variation_Option);
		variation_Option.setVariation(null);
	}
}
