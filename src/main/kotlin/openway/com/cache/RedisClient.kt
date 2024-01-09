package openway.com.cache

import openway.com.configs.Environments
import redis.clients.jedis.JedisPooled

class RedisClient {
    companion object {
        private val redisClient: JedisPooled? = null
        fun getInstance(): JedisPooled {
            if (redisClient == null) {
                return JedisPooled(Environments.redisHost, Environments.redisPort)
            }
            return redisClient
        }
    }
}