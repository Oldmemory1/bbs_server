package com.mxy.bbs_server.service;


import com.mxy.bbs_server.entity.ConcernedRequest;
import com.mxy.bbs_server.entity.FriendshipData;
import com.mxy.bbs_server.mapper.FriendMapper;
import com.mxy.bbs_server.response.concerned.ConcernedResponse;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConcernedService {

    private final FriendMapper friendMapper;
    public ConcernedService(FriendMapper friendMapper) {

        this.friendMapper = friendMapper;
    }

    public ConcernedResponse concerned(ConcernedRequest concernedRequest,Boolean isCancel) {
        final var targetConcerned = concernedRequest.getConcernedId();     //获取请求关注的对象id，即对方的username
        final var who = concernedRequest.getUsername();                    //得到自己的Username
        final var friendship = friendMapper.query(                         //得到自己的好友关系
                new FriendshipData(who, "","",null,null)
        );
        final var targetfriendship = friendMapper.query(                         //得到关注对象的好友关系
                new FriendshipData(targetConcerned, "","",null,null)
        );
        final ArrayList<String> myconcerned = Utility.fromJson(friendship.getMyConcerned(), ArrayList.class);//自己的关注列表
        final ArrayList<String> targetfans = Utility.fromJson(targetfriendship.getMyFans(), ArrayList.class);    //关注对象的粉丝列表
        if (!isCancel) { //实现关注
            myconcerned.add(targetConcerned);
            friendMapper.update(new FriendshipData(
                    who,
                    Utility.toJson(myconcerned),
                    friendship.getMyFans(),
                    friendship.getNumConcerned()+1,
                    friendship.getNumFans()
            ));                    //自己的关注数加一，粉丝数不变
            targetfans.add(who);
            friendMapper.update(new FriendshipData(
                    targetConcerned,
                    targetfriendship.getMyConcerned(),
                    Utility.toJson(targetfans),
                    targetfriendship .getNumConcerned(),
                    targetfriendship.getNumFans()+1
            ));
        } else {  //实现取关
            final var newMyconcerned = new ArrayList<String>();
            for (var targetId: myconcerned) {
                if (!targetId.equals(targetConcerned)) {
                    newMyconcerned.add(targetId);
                }
            }                                                      //把不是取关目标的username重新复制一份进行更新
            friendMapper.update(new FriendshipData(
                    who,
                    Utility.toJson(newMyconcerned),
                    friendship.getMyFans(),
                    friendship.getNumConcerned()-1,
                    friendship.getNumFans()

            ));                                                    //更新我的friendship

            final var newTargetFans = new ArrayList<String>();
            for (var myId: targetfans) {
                if (!myId.equals(targetfans)) {
                    newMyconcerned.add(myId);
                }
            }                                                     //把不是我username重新复制一份进行更新
            friendMapper.update(new FriendshipData(
                    targetConcerned,
                    targetfriendship.getMyConcerned(),
                    Utility.toJson(newTargetFans),
                    targetfriendship.getNumConcerned(),
                    targetfriendship.getNumFans()-1      //更新目标的friendship

            ));
        }
        return new ConcernedResponse(true);
    }
}
