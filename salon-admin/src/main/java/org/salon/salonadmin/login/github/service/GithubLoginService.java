package org.salon.salonadmin.login.github.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yangxin
 * @date 2021/2/4 9:41
 * @since V1.0
 */
@Component
public class GithubLoginService {
    final Logger logger = LoggerFactory.getLogger(GithubLoginService.class);

    @Resource
    private RestTemplate restTemplate;

    public void login(String code) {
        logger.info("code {}", code);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("client_id", "8320a07d7b178010e4f2");
        multiValueMap.add("client_secret", "0f6f7854feae5e638aa247468e8918bcd1524635");
        multiValueMap.add("code", code);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(multiValueMap, httpHeaders);

        JsonNode jsonNode = restTemplate.postForObject("https://github.com/login/oauth/access_token", entity, JsonNode.class);

        var accessToken = Optional.ofNullable(jsonNode).map(m -> m.get("access_token").asText()).orElse("");

        // 根据 accessToken 获取用户信息
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("token %s", accessToken));
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        JsonNode userInfo = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, JsonNode.class).getBody();
        System.out.println(userInfo);

    }
}
