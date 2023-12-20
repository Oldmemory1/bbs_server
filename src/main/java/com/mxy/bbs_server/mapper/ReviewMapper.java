package com.mxy.bbs_server.mapper;

import com.mxy.bbs_server.entity.Review;
import com.mxy.bbs_server.entity.ReviewData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReviewMapper {

    @Insert("insert into Review (id, targetPost, date, username, content, images, likeNum) values (#{id}, #{targetPost}, #{date}, #{username}, #{content}, #{images}, #{likeNum})")
    void add(ReviewData review);

    @Select("select * from Review where id = #{id}")
    ReviewData query(ReviewData review);

    @Update("update Review set date = #{date}, targetPost = #{targetPost}, username = #{username}, content = #{content}, images = #{images}, likeNum = #{likeNum} where id = #{id}")
    void update(ReviewData review);
}
