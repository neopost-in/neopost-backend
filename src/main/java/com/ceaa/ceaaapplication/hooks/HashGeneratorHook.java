package com.ceaa.ceaaapplication.hooks;

import com.ceaa.ceaaapplication.entities.Post;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.sql.Timestamp;

@Service
public class HashGeneratorHook {

    private static String calculatedHash;
    private CacheManager cacheManager;

    public HashGeneratorHook(CacheManager cacheManager) {
        calculatedHash = createNewHash();
        this.cacheManager = cacheManager;
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void generateHook(final Post post) {
        calculatedHash = createNewHash();
        this.cacheManager.getCache("posts").clear();
    }

    @PostConstruct
    public void postConstruct() {
        calculatedHash = createNewHash();
        this.cacheManager.getCache("posts").clear();
    }

    private String createNewHash() {
        String currentTimestamp = new Timestamp(System.currentTimeMillis()).toString();
        byte[] digest = DigestUtils.md5Digest(currentTimestamp.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }

        return sb.toString();
    }

    public static String getCalculatedHash() {
        return calculatedHash;
    }
}
