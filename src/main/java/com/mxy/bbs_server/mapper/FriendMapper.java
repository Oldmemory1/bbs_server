package com.mxy.bbs_server.mapper;

import com.mxy.bbs_server.entity.FriendshipData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FriendMapper {
    @Insert("insert into Friendship (username, myConcerned, myFans,numconcerned,numfans) values (#{username}, #{myConcerned}, #{myFans}, #{numconcerned}, #{numfans})")
    void add(FriendshipData friendShip);

    @Update("update Friendship set myConcerned = #{myConcerned}, myFans = #{myFans} ,numconcerned=#{numconcerned},numfans=#{numfans} where username = #{username}")
    void update(FriendshipData friendShip);

    @Select("select * from Friendship where username = #{username}")
    FriendshipData query(FriendshipData friendShip);
}


