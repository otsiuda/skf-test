package com.skf.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@RestController
public class MessageController {

    @Autowired
    private RedisTemplate<String, String> template;

    @GetMapping("/publish")
    public String healthCheck(@RequestParam String content) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long currentTime = System.currentTimeMillis();
        Double secondaryIndex = currentTime.doubleValue();
        template.boundZSetOps("messages").add(content, secondaryIndex);
        return timestamp.toString();
    }

    @GetMapping("/getLast")
    public String getLast() {
        Set<String> messages = template.opsForZSet().range("messages", -1, -1);
        if (messages != null && messages.isEmpty()) {
            return "queue is empty";
        }
        return messages.stream().findFirst().get();
    }

    @GetMapping("/getByTime")
    public @ResponseBody Set<String> getByTime(@RequestParam("start")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            LocalDateTime start,
                                  @RequestParam
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            LocalDateTime end
    ) {
        Long startTimestamp = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long endTimestamp = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Set<String> messages = template.opsForZSet()
                .rangeByScore("messages", startTimestamp.doubleValue(), endTimestamp.doubleValue());
        return messages;
    }
}
