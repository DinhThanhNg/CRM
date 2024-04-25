package service;

import java.util.List;

import entity.Role;
import entity.User;
import repository.RoleRepository;
import repository.UserRepository;

public class UserService {

	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();

	public boolean updateUser(int id, String email, String password, String fullname, String phone, int idRole) {
		return userRepository.updateUserById(id, email, password, fullname, phone, idRole) > 0;
	}

	public List<Role> getAllRole() {
		return roleRepository.getAll();
	}

	public boolean insertUser(String fullname, String email, String password, String phone, int idRole) {
		int count = userRepository.save(fullname, email, password, phone, idRole);
		return count > 0;
	}

	public boolean deleteUserById(int id) {
		return userRepository.deleteUserById(id) > 0;
	}

	public List<User> getAllUser() {
		return userRepository.getAlUser();
	}

	public User getUserById(int id) {
		return userRepository.getUserById(id);
	}
}
