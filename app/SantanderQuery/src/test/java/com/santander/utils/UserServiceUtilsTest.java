package com.santander.utils;

import java.util.HashSet;
import java.util.Set;

import com.santander.enums.PerfilCliente;
import com.santander.security.UserDetailsImpl;

public class UserServiceUtilsTest {

	public UserDetailsImpl getUserTest() {

		Set<PerfilCliente> perfis = new HashSet<>();
		perfis.add(PerfilCliente.CLIENTE);

		UserDetailsImpl user = new UserDetailsImpl("matheus@matheus.com",
				"$2a$10$RXUmuF//FvuE3x0HLhvZD.Q.jufe8hnt6xbIaq5d7sHiJI2SjVX7S", perfis);

		return user;

	}

}
