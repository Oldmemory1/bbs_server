package com.mxy.bbs_server.service;

import com.mxy.bbs_server.entity.*;
import com.mxy.bbs_server.mapper.PostMapper;
import com.mxy.bbs_server.mapper.ReviewMapper;
import com.mxy.bbs_server.response.review.ReviewResponse;
import com.mxy.bbs_server.response.review.ReviewResponseFailedReason;
import com.mxy.bbs_server.utility.Const;
import com.mxy.bbs_server.utility.Utility;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewMapper reviewMapper;

    private final PostMapper postMapper;

    public ReviewService(ReviewMapper reviewMapper, PostMapper postMapper) {
        this.reviewMapper = reviewMapper;
        this.postMapper = postMapper;
    }

    public ReviewResponse add(ReviewRequest reviewRequest) throws IOException {
        final var reviewToQuery = new ReviewData(reviewRequest.getId(), null, null, null, null,null, null);
        if (reviewMapper.query(reviewToQuery) != null) {
            return new ReviewResponse(false, ReviewResponseFailedReason.REVIEW_ALREADY_EXISTS, null);
        }
        final var images = Utility.saveReviewImages(reviewRequest.getImages(), reviewRequest.getId());
        reviewMapper.add(new ReviewData(reviewRequest.getId(), reviewRequest.getTargetPost(), Utility.getDate(Const.DATE_FORMAT), reviewRequest.getUsername(), reviewRequest.getContent(), Utility.toJson(images), 0));
        final var previousPost = postMapper.query(new PostData(reviewRequest.getTargetPost(), null, null, null, null, null, null, null));
        //更新针对该post的评论
        final List<String> reviewsLst = Utility.fromJson(previousPost.getReviews(), List.class);
        reviewsLst.add(reviewRequest.getId());
        previousPost.setReviews(Utility.toJson(reviewsLst));
        postMapper.update(previousPost);
        final var reviewData = reviewMapper.query(reviewToQuery);
        return new ReviewResponse(true, null, new Review(reviewData.getId(),
                reviewData.getTargetPost(),
                reviewData.getDate(),
                reviewData.getUsername(),
                reviewData.getContent(),
                Utility.fromJson(reviewData.getImages(), ArrayList.class),
                reviewData.getLikeNum()
        ));
    }

    public ReviewResponse query(ReviewRequest reviewRequest) {
        final var reviewData = reviewMapper.query(new ReviewData(reviewRequest.getId(), null, null, null, null, null, null));
        if (reviewData == null) {
            return new ReviewResponse(false, ReviewResponseFailedReason.REVIEW_DOES_NOT_EXISTS, null);
        }
        return new ReviewResponse(true, null,
                new Review(reviewData.getId(),
                        reviewData.getTargetPost(),
                        reviewData.getDate(),
                        reviewData.getUsername(),
                        reviewData.getContent(),
                        Utility.fromJson(reviewData.getImages(), ArrayList.class),
                        reviewData.getLikeNum()
                )
        );
    }
}
