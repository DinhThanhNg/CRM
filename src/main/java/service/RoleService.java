package service;

import java.util.List;

import entity.Role;
import repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean insertRole(String name, String description) {
		return roleRepository.save(name, description) > 0;
	}
	public List<Role> getAllRole(){
		return roleRepository.getAll();
	}
}
