package com.last.project4_memerealm.services;


import com.last.project4_memerealm.models.Comment;
import com.last.project4_memerealm.models.Feedback;
import com.last.project4_memerealm.models.Post;
import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;

import java.util.List;

public interface UserService {
	List<User> getAll();    //for testing only

	UserDetailDto testGetDetail();

	UserDetailDto getPersonalInformation(String token);
	boolean updateInformation(String token, User obj);
	boolean createPost(String token, Post obj);
	boolean deletePost(String token, Integer postId);
	boolean createComment(String token, Comment obj);
	boolean deleteComment(String token, Integer commentId);
	boolean addFavouritePost(String token, Integer postId);
	boolean removeFavouritePost(String token, Integer favId);
	boolean followUser(String token, Integer userId);
	boolean unfollowUser(String token, Integer followId);
	boolean createFeedback(String token, Feedback obj);

}
