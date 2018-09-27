package com.mitrais.carrot.controllers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mitrais.carrot.config.SecurityConfig;
import com.mitrais.carrot.config.jwt.JwtTokenProvider;
import com.mitrais.carrot.models.Role;
import org.springframework.beans.factory.annotation.Value;

import com.mitrais.carrot.services.RoleService;
import com.mitrais.carrot.utils.UserPrincipalData;

/**
 * Role controller test
 * 
 * @author Febri_MW251
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SecurityConfig.class)
public class RoleControllerTest {

	/**
	 * get base url from config application.properties
	 */
	@Value("${spring.data.rest.basePath}")
	private String baseUrl;

	/**
	 * mock mvc
	 */
	private MockMvc mvc;

	/**
     * variable of AuthenticationManager service
     */
	@MockBean
    private AuthenticationManager authenticationManager;

    /**
     * variable of JwtTokenProvider service
     */
    @MockBean
    private JwtTokenProvider tokenProvider;

	/**
	 * WebApplicationContext variable
	 */
	@Autowired
	private WebApplicationContext context;

	/**
	 * variable of RoleService service
	 */
	@MockBean
	public RoleService roleServiceMock;

	private UserPrincipalData userPrincipalData = new UserPrincipalData();

	/**
	 * setup to intialize default variable value
	 */
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	/**
	 * should get 4XX client error caused should be access with JWT token
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldGetUnauthorizedWithoutJWTToken() throws Exception {
		Role role = new Role();
		role.setRoleName("ROLE_STAFF");
		role.setDeleted(false);

		mvc.perform(get(this.baseUrl + "/roles")).andExpect(status().is(401));
	}

	@Test
	public void testAll() throws Exception {
		Role role = new Role(1,"ROLE_STAFF");		

		given(roleServiceMock.findAllBydeletedFalse()).willReturn(Arrays.asList(role));

//		given(tokenProvider.generateToken(authentication)).willReturn("xxxx");
		
		String token = this.userPrincipalData.getToken();

		mvc.perform(get(this.baseUrl + "/roles")
				.header("Authorization", "Bearer "+ token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
		// .andExpect(jsonPath("$[0].typeName").value(role.getRoleName()));
	}

	// /**
	// * Test Get for empty list role
	// *
	// * @throws Exception
	// * @verifies should return status().isOk() and hasSize(0)
	// */
	// @Test
	// public void testGetAllRoleIsEmpty() throws Exception {
	//
	// given(roleServiceMock.findAllBydeletedFalse()).willReturn(new ArrayList());
	//
	// mvc.perform(get(this.baseUrl +
	// "/rewards").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	// .andExpect(jsonPath("$", hasSize(0)));
	// }

	// public void testSave() {
	//
	// }
}
