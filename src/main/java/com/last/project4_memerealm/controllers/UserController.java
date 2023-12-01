package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.models.Comment;
import com.last.project4_memerealm.models.Feedback;
import com.last.project4_memerealm.models.Post;
import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.UserService;
import com.last.project4_memerealm.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService us;
	private final UserRepository ur;

	@Autowired
	public UserController(UserService us, UserRepository ur) {
		this.us = us;
		this.ur = ur;
	}

	@PostMapping("profile")
	public ResponseEntity<UserDetailDto> getPersonalInformation(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(us.getPersonalInformation(obj.get("token")), HttpStatus.OK);
	}

	/**
	 * missing upload avatar
	 * @param obj
	 * @return
	 */
	@PostMapping("update-profile")
	public ResponseEntity<Boolean> updateInformation(@RequestBody Map<String, String> obj){
		String username = JwtHelper.getUsernameFormToken(obj.get("token"));
		if(username == null)
			return new ResponseEntity<>(false, HttpStatus.OK);

		User u = new User();
		u.setId(Integer.valueOf(obj.get("user_id")));
		u.setUsername(obj.get("username"));
		u.setEmail(obj.get("email"));
		u.setPassword(obj.get("password"));
		u.setAvatar(obj.get("avatar"));

		return new ResponseEntity<>(us.updateInformation(obj.get("token"), u ), HttpStatus.OK);
	}

	/**
	 * missing tags
	 * @param obj
	 * @return
	 */
	@PostMapping("create-post")
	public ResponseEntity<Boolean> createPost(@RequestBody Map<String, String> obj){
		User u = ur.findByUsername(obj.get("token"));

		Post p = new Post();
		p.setCaption(obj.get("caption"));
		p.setMediaLink(obj.get("media_link"));
		p.setUpVote(0);
		p.setDownVote(0);
		p.setUser(u);

		return new ResponseEntity<>(us.createPost(obj.get("token"), p ), HttpStatus.OK);
	}

	@PostMapping("delete-post")
	public ResponseEntity<Boolean> deletePost(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(us.deletePost(obj.get("token"), Integer.valueOf(obj.get("post_id"))), HttpStatus.OK);
	}

	@PostMapping("create comment")
	public ResponseEntity<Boolean> createComment(@RequestBody Map<String, String> obj){

		Post p = new Post();
		p.setId(Integer.valueOf(obj.get("post_id")));

		User u = ur.findByUsername(obj.get("token"));

		Comment c = new Comment();
		c.setPost(p);
		c.setUser(u);
		c.setComment(obj.get("comment"));

		return new ResponseEntity<>(us.createComment(obj.get("token"), c), HttpStatus.OK);
	}

	@PostMapping("delete comment")
	public ResponseEntity<Boolean> deleteComment(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(us.deleteComment(obj.get("token"), Integer.valueOf(obj.get("comment_id"))), HttpStatus.OK);
	}

	@PostMapping("add-post-to-fav")
	public ResponseEntity<Boolean> addFavouritePost(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(us.addFavouritePost(obj.get("token"), Integer.valueOf(obj.get("post_id"))), HttpStatus.OK);
	}

	@PostMapping("follow-user")
	public ResponseEntity<Boolean> followUser(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(us.followUser(obj.get("token"), Integer.valueOf(obj.get("user_id"))), HttpStatus.OK);
	}

	@PostMapping("create-feedback")
	public ResponseEntity<Boolean> createFeedback(@RequestBody Map<String, String> obj){
		User u = ur.findByUsername(obj.get("token"));

		Feedback f = new Feedback();
		f.setUser(u);
		f.setMessage(obj.get("message"));

		return new ResponseEntity<>(us.createFeedback(obj.get("token"), f), HttpStatus.OK);
	}






}
