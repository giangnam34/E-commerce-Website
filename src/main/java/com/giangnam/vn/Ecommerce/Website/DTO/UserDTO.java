package com.giangnam.vn.Ecommerce.Website.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private int id;
	private String email;
	private String phoneNumber;
	private String address;
	private String firstName;
	private String lastName;
	private String password;
	private String role;
}
