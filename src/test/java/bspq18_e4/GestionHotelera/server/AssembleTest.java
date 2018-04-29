package bspq18_e4.GestionHotelera.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class AssembleTest {

	private static Assemble ass;
	private static User user;
	private static UserDTO userDTO;
	
	@Before
	public void setUp() {
		ass = new Assemble();
		user = new User("mail@gmail.com", "name", "pass", "1234");
	}
	
	@Test
	public void testUser() {
		userDTO = ass.user(user);
		//userDTO = null;
		user = ass.userDTO(userDTO);
		assertEquals(user.getEmail(), userDTO.getEmail());
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getPass(), userDTO.getPass());
		assertEquals(user.getCc(), userDTO.getCc());
	}
	
	@Test
	public void testUserDTO() {
		userDTO = ass.user(user);
		assertEquals(userDTO.getEmail(), user.getEmail());
		assertNotEquals(userDTO.getEmail(), user.getName());
		assertEquals(userDTO.getName(), user.getName());
		assertEquals(userDTO.getPass(), user.getPass());
		assertEquals(userDTO.getCc(), user.getCc());
	}
}
