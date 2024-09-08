package com.socialmedia.posts.util;

import com.socialmedia.posts.entity.PostEntity;
import java.util.Date;
import java.util.Optional;

public class AuditUtil {

    public static void addAuditDetails(PostEntity postEntity) {
        postEntity.setCreatedDate(Optional.ofNullable(postEntity.getCreatedDate()).orElseGet(Date::new));
        postEntity.setModifiedDate(Optional.ofNullable(postEntity.getModifiedDate()).orElseGet(Date::new));
    }
}