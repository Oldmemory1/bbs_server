package com.mxy.bbs_server.response.review;

import com.mxy.bbs_server.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponse {
    private Boolean success;
    private ReviewResponseFailedReason reviewResponseFailedReason;
    private Review review;
}
