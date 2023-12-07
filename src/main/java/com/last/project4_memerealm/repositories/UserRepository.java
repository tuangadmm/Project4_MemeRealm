package com.last.project4_memerealm.repositories;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;
import com.last.project4_memerealm.models.dto.response.UserListBasicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	@Query(nativeQuery = true, value = "select u.user_id, u.username, u.email, u.avatar, u.created_date, count(distinct p.post_id) as post_count, count(distinct f.fav_id) as fav_count, count(distinct fo.follow_id) as follow_count from users u left join posts p on u.user_id = p.user_id left join favourite f on u.user_id = f.user_id left join follows fo on u.user_id = fo.user_id where u.user_id = :userId group by u.user_id, u.username")
	List<Object[]> getUserDetailsRaw(Integer userId);

	@Query("SELECT new com.last.project4_memerealm.models.dto.response.UserListBasicDto(u.id, u.username, u.email) FROM User u")
	Page<UserListBasicDto> findAllUsers(Pageable pageable);


}

