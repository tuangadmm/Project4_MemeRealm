package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.*;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;
import com.last.project4_memerealm.repositories.*;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.services.UserService;
import com.last.project4_memerealm.utils.JwtHelper;
import com.last.project4_memerealm.utils.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final List<String>    access = Permissions.ALL;

	private final UserRepository        ur;
	private final AuthService           as;
	private final PostRepository        pr;
	private final CommentRepository     cr;
	private final FavouriteRepository   fr;
	private final FollowRepository      flr;
	private final FeedbackRepository    fbr;
	private final RoleRepository        rr;

	@Autowired
	public UserServiceImpl(
			UserRepository userRepository,
			AuthService as,
			PostRepository pr,
			CommentRepository cr,
			FavouriteRepository fr,
			FollowRepository flr,
			FeedbackRepository fbr,
			RoleRepository rr
	) {
		this.ur = userRepository;
		this.as = as;
		this.pr = pr;
		this.cr = cr;
		this.fr = fr;
		this.flr = flr;
		this.fbr = fbr;
		this.rr = rr;
	}

	@Override
	public List<User> getAll() {
		return ur.findAll();
	}

	@Override
	public UserDetailDto testGetDetail() {

		List<Role> roles = rr.findByUserRoles_User_Id(1001);

		List<Object[]> detailsRaw = ur.getUserDetailsRaw(1001);
		Object[] result = detailsRaw.get(0);

		UserDetailDto res = new UserDetailDto();
		res.setUserId((Integer) result[0]);
		res.setUsername((String) result[1]);
		res.setEmail((String) result[2]);

		res.setRoles(roles);

		res.setAvatar( result[3] == null ? null : (String) result[3] );
		res.setPostCount((Long) result[5]);
		res.setFavouriteCount((Long) result[6]);
		res.setFollowCount((Long) result[7]);

		return res;
	}


	@Override
	public UserDetailDto getPersonalInformation(String token) {
		if(!as.hasPermission(token, access))
			return null;

		String username = JwtHelper.getUsernameFormToken(token);
		User user = ur.findByUsername(username);

		List<Role> roles = rr.findByUserRoles_User_Id(user.getId());

		List<Object[]> detailsRaw = ur.getUserDetailsRaw(user.getId());
		Object[] result = detailsRaw.get(0);

		UserDetailDto res = new UserDetailDto();
		res.setUserId((Integer) result[0]);
		res.setUsername((String) result[1]);
		res.setEmail((String) result[2]);

		res.setRoles(roles);

		res.setAvatar( result[3] == null ? null : (String) result[3] );
		res.setPostCount((Long) result[5]);
		res.setFavouriteCount((Long) result[6]);
		res.setFollowCount((Long) result[7]);

		return res;
	}

	@Override
	public boolean updateInformation(String token, User obj) {
		if(!as.hasPermission(token, access))
			return false;

		User u = ur.findByUsername(obj.getUsername());
		u.setEmail(obj.getEmail());
		u.setPassword(obj.getPassword());
		u.setAvatar(obj.getAvatar());
		ur.saveAndFlush(u);

		return true;
	}

	@Override
	public boolean createPost(String token, Post obj) {
		if(!as.hasPermission(token, access))
			return false;

		pr.saveAndFlush(obj);
		return true;
	}

	@Override
	public boolean deletePost(String token, Integer postId) {
		if(!as.hasPermission(token, access))
			return false;

		pr.findById(postId).ifPresent(pr::delete);
		return true;
	}

	@Override
	public boolean createComment(String token, Comment obj) {
		if(!as.hasPermission(token, access))
			return false;

		cr.saveAndFlush(obj);
		return true;
	}

	@Override
	public boolean deleteComment(String token, Integer commentId) {
		if(!as.hasPermission(token, access))
			return false;

		cr.findById(commentId).ifPresent(cr::delete);
		return true;
	}

	@Override
	public boolean addFavouritePost(String token, Integer postId) {
		if(!as.hasPermission(token, access))
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
	public boolean removeFavouritePost(String token, Integer favId) {
		if(!as.hasPermission(token, access))
			return false;

		Favourite f = fr.findById(favId).orElse(null);


		if(f != null){
			fr.delete(f);
			return true;
		}
		return false;
	}

	@Override
	public boolean followUser(String token, Integer userId) {
		if(!as.hasPermission(token, access))
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
	public boolean unfollowUser(String token, Integer followId) {
		if(!as.hasPermission(token, access))
			return false;

		Follow f = flr.findById(followId).orElse(null);

		if(f != null){
			flr.delete(f);
			return true;
		}
		return false;
	}

	@Override
	public boolean createFeedback(String token, Feedback obj) {
		if(!as.hasPermission(token, access))
			return false;

		fbr.saveAndFlush(obj);
		return true;
	}
}
