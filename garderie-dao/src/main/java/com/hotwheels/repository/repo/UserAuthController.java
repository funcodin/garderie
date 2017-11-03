//package com.hotwheels.repository.repo;
//
//import java.util.Objects;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.garderie.types.user.info.Child;
//import com.hotwheels.repository.types.UserAuth;
//
//@RestController
//@RequestMapping("user")
//public class UserAuthController {
//
//	private final Logger log = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	UserAuthRepository userAuthRepository;
//
//
//	@RequestMapping("/{username}")
//	public UserAuth findByUsername(@PathVariable String username){
//		final UserAuth userAuth = userAuthRepository.findByUsername(username);
//		
//		if( Objects.nonNull(userAuth)){
//			log.info( "found user for username {}",userAuth.getUsername());
//			return userAuth;
//		}
//		log.info("Did not find user");
//		return null;
//	}
//	
//	@RequestMapping("/create")
//	public void createUser() {
//		final UserAuth userAuth = new UserAuth();
//		userAuth.setUsername("xyz");
//		userAuth.setPassword("abc");
//		userAuthRepository.save(userAuth);
//	}
//	
//}
