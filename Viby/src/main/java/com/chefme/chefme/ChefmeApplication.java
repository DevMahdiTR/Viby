package com.chefme.chefme;

import com.chefme.chefme.model.role.Role;
import com.chefme.chefme.repository.RoleRepository;
import com.chefme.chefme.service.role.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ChefmeApplication {

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChefmeApplication.class, args);
	}

	@PostConstruct
	private void init()
	{
		roleRepository.save(new Role("USER"));
		roleRepository.save(new Role("ADMIN"));
	}

}
