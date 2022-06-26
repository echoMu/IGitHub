package com.echomu.github.data

import com.echomu.github.data.model.repository.RepositoryData
import com.echomu.github.data.model.user.response.UserAccessTokenData
import com.echomu.github.data.model.user.response.UserInfoData
import com.echomu.github.utils.Language

/**
 * Created by echoMu.
 */

val userInfoDataJson = "{\n" +
        "  \"login\": \"echoMu\",\n" +
        "  \"id\": 19618232,\n" +
        "  \"node_id\": \"MDQ6VXNlcjE5NjE4MjMy\",\n" +
        "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/19618232?v=4\",\n" +
        "  \"gravatar_id\": \"\",\n" +
        "  \"url\": \"https://api.github.com/users/echoMu\",\n" +
        "  \"html_url\": \"https://github.com/echoMu\",\n" +
        "  \"followers_url\": \"https://api.github.com/users/echoMu/followers\",\n" +
        "  \"following_url\": \"https://api.github.com/users/echoMu/following{/other_user}\",\n" +
        "  \"gists_url\": \"https://api.github.com/users/echoMu/gists{/gist_id}\",\n" +
        "  \"starred_url\": \"https://api.github.com/users/echoMu/starred{/owner}{/repo}\",\n" +
        "  \"subscriptions_url\": \"https://api.github.com/users/echoMu/subscriptions\",\n" +
        "  \"organizations_url\": \"https://api.github.com/users/echoMu/orgs\",\n" +
        "  \"repos_url\": \"https://api.github.com/users/echoMu/repos\",\n" +
        "  \"events_url\": \"https://api.github.com/users/echoMu/events{/privacy}\",\n" +
        "  \"received_events_url\": \"https://api.github.com/users/echoMu/received_events\",\n" +
        "  \"type\": \"User\",\n" +
        "  \"site_admin\": false,\n" +
        "  \"name\": \"\",\n" +
        "  \"company\": \"\",\n" +
        "  \"blog\": \"\",\n" +
        "  \"location\": \"\",\n" +
        "  \"email\": \"\",\n" +
        "  \"hireable\": \"\",\n" +
        "  \"bio\": \"\",\n" +
        "  \"twitter_username\": \"\",\n" +
        "  \"public_repos\": 13,\n" +
        "  \"public_gists\": 0,\n" +
        "  \"followers\": 1,\n" +
        "  \"following\": 14,\n" +
        "  \"created_at\": \"2016-05-28T03:09:09Z\",\n" +
        "  \"updated_at\": \"2022-06-25T12:55:24Z\"\n" +
        "}"

val repositoryDataJson = "{\n" +
        "\"total_count\": 1,\n" +
        "\"incomplete_results\": false,\n" +
        "\"items\": [\n" +
        "{\n" +
        "\"id\": 0,\n" +
        "\"name\": \"echoMu\",\n" +
        "\"description\": \"描述\",\n" +
        "\"language\": \"Kotlin\",\n" +
        "\"starCount\": 0,\n" +
        "\"forkCount\": 0\n" +
        "}\n" +
        "]\n" +
        "}"

val userInfoData = UserInfoData(
        id = 19618232,
        login = "echoMu",
        nodeId = "MDQ6VXNlcjE5NjE4MjMy",
        avatarUrl = "https://avatars.githubusercontent.com/u/19618232?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/echoMu",
        htmlUrl = "https://github.com/echoMu",
        followersUrl = "https://api.github.com/users/echoMu/followers",
        followingUrl = "https://api.github.com/users/echoMu/following{/other_user}",
        gistsUrl = "https://api.github.com/users/echoMu/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/echoMu/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/echoMu/subscriptions",
        organizationsUrl = "https://api.github.com/users/echoMu/orgs",
        reposUrl = "https://api.github.com/users/echoMu/repos",
        eventsUrl = "https://api.github.com/users/echoMu/events{/privacy}",
        receivedEventsUrl = "https://api.github.com/users/echoMu/received_events",
        type = "User",
        siteAdmin = false,
        name = "",
        company = "",
        blog = "",
        location = "",
        email = "",
        hireable = "",
        bio = "",
        publicRepos = 13,
        publicGists = 0,
        followers = 1,
        following = 14,
        createdAt = "2016-05-28T03:09:09Z",
        updatedAt = "2022-06-25T12:55:24Z",
        privateGists = 0,
        totalPrivateRepos = 0,
        ownedPrivateRepos = 0,
        diskUsage = 0,
        collaborators = 0,
        twoFactorAuthentication = false
)

val repositoryData = RepositoryData(
        id = 0,
        name = "echoMu",
        description = "描述",
        language = Language.KOTLIN,
        starCount = 0,
        forkCount = 0
)