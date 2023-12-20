package com.mxy.bbs_server.utility;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Utility {

    public static final Gson gson = new Gson();
    public static String saveAvatar(MultipartFile avatar, String username) throws IOException {
        final var targetAvatar = new File("./avatars/" + username + "/" + avatar.getOriginalFilename());
        FileUtils.copyInputStreamToFile(avatar.getInputStream(), targetAvatar);
        return NginxHelper.getAbsoluteUrl(targetAvatar.getAbsolutePath());
    }

    public static List<String> savePostImages(MultipartFile[] images, String postId) throws IOException {
        final List<String> imagesLst = new ArrayList<>();
        for (final var image: images) {
            if (Objects.requireNonNull(image.getOriginalFilename()).isEmpty()) {
                continue;
            }
            final var targetImage = new File("./post_images/" + postId + "/" + image.getOriginalFilename());
            FileUtils.copyInputStreamToFile(image.getInputStream(), targetImage);
            imagesLst.add(NginxHelper.getAbsoluteUrl(targetImage.getAbsolutePath()));
        }
        return imagesLst;
    }

    public static List<String> saveReviewImages(MultipartFile[] images, String reviewId) throws IOException {
        final List<String> imagesLst = new ArrayList<>();
        for (final var image: images) {
            if (Objects.requireNonNull(image.getOriginalFilename()).isEmpty()) {
                continue;
            }
            final var targetImage = new File("./review_images/" + reviewId + "/" + image.getOriginalFilename());
            FileUtils.copyInputStreamToFile(image.getInputStream(), targetImage);
            imagesLst.add(NginxHelper.getAbsoluteUrl(targetImage.getAbsolutePath()));
        }
        return imagesLst;
    }

    public static String getDate(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
