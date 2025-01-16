package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.User;
import com.carprakingapp.webapp.database.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewDAO extends JpaRepository<UserReview, Long> {

    User findByUserId(Integer id);

    UserReview findByUserReviewId(Integer userReviewId);
}
