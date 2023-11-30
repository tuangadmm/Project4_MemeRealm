package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.*;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;
import com.last.project4_memerealm.repositories.*;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.services.UserService;
import com.last.project4_memerealm.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository ur;
	private final UserRoleRepository urr;
	private final AuthService as;
	private final PostRepository pr;
	private final CommentRepository cr;
	private final FavouriteRepository fr;
	private final FollowRepository flr;
	private final FeedbackRepository fbr;
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserRoleRepository urr, AuthService as, PostRepository pr, CommentRepository cr, FavouriteRepository fr, FollowRepository flr, FeedbackRepository fbr) {
		this.ur = userRepository;
		this.urr = urr;
		this.as = as;
		this.pr = pr;
		this.cr = cr;
		this.fr = fr;
		this.flr = flr;
		this.fbr = fbr;
	}

	@Override
	public List<User> getAll() {
		return ur.findAll();
	}

	@Override
	public UserDetailDto getPersonalInformation(String token) {
		if(!as.hasPermission(token))
			return null;

		String username = JwtHelper.getUsernameFormToken(token);
		User user = ur.findByUsername(username);
		List<UserRole> roles = urr.findByUser_Id(user.getId());

		return new UserDetailDto(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getAvatar(),
				roles,
				0,
				0,
				0,
				0
		);
	}

	@Override
	public boolean updateInformation(String token, User obj) {
		if(!as.hasPermission(token))
			return false;

		ur.saveAndFlush(obj);

		return true;
	}

	@Override
	public boolean createPost(String token, Post obj) {
		if(!as.hasPermission(token))
			return false;

		pr.saveAndFlush(obj);
		return true;
	}

	@Override
	public boolean deletePost(String token, Integer postId) {
		if(!as.hasPermission(token))
			return false;

		pr.findById(postId).ifPresent(pr::delete);
		return true;
	}

	@Override
	public boolean createComment(String token, Comment obj) {
		if(!as.hasPermission(token))
			return false;

		cr.saveAndFlush(obj);
		return true;
	}

	@Override
	public boolean deleteComment(String token, Integer commentId) {
		if(!as.hasPermission(token))
			return false;

		cr.findById(commentId).ifPresent(cr::delete);
		return true;
	}

	@Override
	public boolean addFavouritePost(String token, Integer postId) {
		if(!as.hasPermission(token))
			return false;

		Favourite f = new Favourite();
		Post p = pr.findById(postId).orElse(null);

		if(p != null){
			f.setPost(p);
			fr.saveAndFlush(f);
			return true;
		}
		return false;
	}

	@Override
	public boolean followUser(String token, Integer userId) {
		if(!as.hasPermission(token))
			return false;

		Follow f = new Follow();
		User u = ur.findById(userId).orElse(null);

		if(u != null){
			f.setUser(u);
			flr.saveAndFlush(f);
			return true;
		}
		return false;
	}

	@Override
	public boolean createFeedback(String token, Feedback obj) {
		if(!as.hasPermission(token))
			return false;

		fbr.saveAndFlush(obj);
		return true;
	}
}
