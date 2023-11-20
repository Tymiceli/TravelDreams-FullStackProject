package com.traveldreams.web;

import com.traveldreams.entity.Authorities;
import com.traveldreams.entity.RefreshToken;
import com.traveldreams.repository.UserRepository;
import com.traveldreams.request.RefreshTokenRequest;
import com.traveldreams.response.AuthenticationResponse;
import com.traveldreams.response.RefreshTokenResponse;
import com.traveldreams.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class UserController {

	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private JwtService jwtService;
	private UserService userService;
	private RefreshTokenService refreshTokenService;
	private AuthoritiesService authoritiesService;

	private CountryService countryService;

	public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, UserService userService, RefreshTokenService refreshTokenService, AuthoritiesService authoritiesService, CountryService countryService) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.userService = userService;
		this.refreshTokenService = refreshTokenService;
		this.authoritiesService = authoritiesService;
		this.countryService = countryService;
	}

	@GetMapping("/profile")
	public String getProfilePage (ModelMap model, @AuthenticationPrincipal UserEntity user) {

		Optional<UserEntity> userFound = userService.findById(user.getId());

		userFound.ifPresent(userEntity -> model.put("user", userEntity));

		return "profile";
	}
	@GetMapping("/user")
	public String getUserCountryList(ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		Optional<UserEntity> authUser = userService.findById(user.getId());
		authUser.ifPresent(userEntity -> model.put("user", userEntity));

		return "user";
	}
	
	@PostMapping ("/user/{userId}/country/{countryId}/delete")
	public String deleteCountryFromUserCountryList(@PathVariable Long userId, @PathVariable Long countryId) {
		
		countryService.removeCountry(userId, countryId);
		
		return "redirect:/user";
	}
	
	@PostMapping("/update-user-account")
	public String updateUser (UserEntity user) {
		
		userService.save(user);
		
		return "redirect:/user";
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<RefreshTokenResponse> getNewAccessToken (@RequestBody RefreshTokenRequest refreshTokenRequest) {
		String accessToken = refreshTokenService.generateNewAccessToken(refreshTokenRequest);
		return ResponseEntity.ok(new RefreshTokenResponse(accessToken, refreshTokenRequest.refreshToken()));
	}
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> signUpUser (UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Authorities authorities = new Authorities("ROLE_USER", user);
		user.getAuthorities().add(authorities);
		UserEntity savedUser = userRepository.save(user);
		authoritiesService.saveAuth(authorities);

		String accessToken = jwtService.generateToken(new HashMap<>(), savedUser);
		RefreshToken refreshToken = refreshTokenService.generateRefreshToken(savedUser.getId());

		return ResponseEntity.ok(new AuthenticationResponse(savedUser.getUsername(), accessToken, refreshToken.getRefreshToken()));
	}
	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {

		model.put("user", new UserEntity());
		return "register";
	}

	@PostMapping("/exists")
	@ResponseBody
	public Boolean postExists (@RequestBody UserEntity user) {

		System.out.println("Endpoint hit");
		user = userService.findByUsername(user.getUsername());

		Boolean userFoundCheck = user !=null;

		if (userFoundCheck) {
			System.out.println("User found in database");
		} else {
			System.out.println("User not found in database");
		}

		return userFoundCheck;
	}
}
