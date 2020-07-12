package wang.l1n.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wang.l1n.api.exception.RedisConnectException;
import wang.l1n.api.function.JedisExecutor;

import java.util.Map;
import java.util.Set;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:35
 * @description： Redis 工具类
 */


@Component
public class CacheUtil {

    @Autowired
    JedisPool jedisPool;

    /**
     * 处理Redis 请求
     * @param j
     * @param <T>
     * @return
     * @throws RedisConnectException
     */
    private <T> T excuteByJedis(JedisExecutor<Jedis, T> j) throws RedisConnectException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            throw new RedisConnectException(e.getMessage());
        }
    }

    /**
     * 获取 key
     * @param pattern 正则
     * @return String
     * @throws RedisConnectException
     */
    public Set<String> getKeys(String pattern) throws RedisConnectException {
        return this.excuteByJedis(j -> j.keys(pattern));
    }

    /**
     * get命令
     * @param key key
     * @return String  OK代表设置成功
     * @throws RedisConnectException
     */
    public String get(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.get(key.toLowerCase()));
    }

    /**
     * set命令
     * @param key
     * @param value
     * @return String  OK代表设置成功
     * @throws RedisConnectException
     */
    public String set(String key, String value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.set(key.toLowerCase(), value));
    }

    /**
     * setex 命令
     * @param key key
     * @param value value
     * @param seconds 秒
     * @return String  OK代表设置成功
     * @throws RedisConnectException
     */
    public String setex(String key, String value, Long seconds) throws RedisConnectException {
        String result = this.set(key.toLowerCase(), value);
        this.pexpire(key, seconds * 1000);
        return result;
    }

    /**
     * strlen 命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long strlen(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.strlen(key));
    }

    /**
     * append 命令
     * @param key
     * @param appendValue
     * @return
     * @throws RedisConnectException
     */
    public Long append(String key, String appendValue) throws RedisConnectException {
        return this.excuteByJedis(j -> j.append(key, appendValue));
    }

    /**
     * getRange 命令
     * @param key
     * @param startOffset
     * @param endOffset
     * @return String
     * @throws RedisConnectException
     */
    public String getRange(String key, Long startOffset, Long endOffset) throws RedisConnectException {
        return this.excuteByJedis(j -> j.getrange(key, startOffset, endOffset));
    }

    /**
     * incr命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long incr(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.incr(key));
    }

    /**
     * incrBy
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long incrBy(String key, Long incr) throws RedisConnectException {
        return this.excuteByJedis(j -> j.incrBy(key, incr));
    }

    /**
     * decr命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long decr(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.decr(key));
    }

    /**
     * decrBy命令
     * @param key
     * @param decr
     * @return
     * @throws RedisConnectException
     */
    public Long decrBy(String key, Long decr) throws RedisConnectException {
        return this.excuteByJedis(j -> j.decrBy(key, decr));
    }

    /**
     * del 命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long del(String... key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.del(key));
    }

    /**
     * exists命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Boolean exists(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.exists(key));
    }

    /**
     * pttl命令
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long pttl(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.pttl(key));
    }

    /**
     * pexpire命令
     * @param key
     * @param milliseconds
     * @return
     * @throws RedisConnectException
     */
    public Long pexpire(String key, Long milliseconds) throws RedisConnectException {
        return this.excuteByJedis(j -> j.pexpire(key, milliseconds));
    }

    /**
     * hset命令
     * @param key
     * @param hash
     * @return
     * @throws RedisConnectException
     */
    public Long hset(String key, Map<String, String> hash) throws RedisConnectException {
        return this.excuteByJedis(j -> j.hset(key, hash));
    }

    /**
     * hsetnx
     * @param key
     * @param field
     * @param value
     * @return
     * @throws RedisConnectException
     */
    public Long hsetnx(String key, String field, String value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.hsetnx(key, field, value));
    }

    /**
     * hget 命令
     * @param key
     * @param field
     * @return
     * @throws RedisConnectException
     */
    public String hget(String key, String field) throws RedisConnectException {
        return this.excuteByJedis(j -> j.hget(key, field));
    }

    /**
     * hexists命令
     * @param key
     * @param field
     * @return
     * @throws RedisConnectException
     */
    public Boolean hexists(String key, String field) throws RedisConnectException {
        return this.excuteByJedis(j -> j.hexists(key, field));
    }

    /**
     * lpush
     * @param key
     * @param value
     * @return
     * @throws RedisConnectException
     */
    public Long lpush(String key, String... value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.lpush(key, value));
    }

    /**
     * rpush
     * @param key
     * @param value
     * @return
     * @throws RedisConnectException
     */
    public Long rpush(String key, String... value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.rpush(key, value));
    }

    /**
     * lpop
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public String lpop(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.lpop(key));
    }

    /**
     * rpop
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public String rpop(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.rpop(key));
    }

    /**
     * llen
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Long llen(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.llen(key));
    }

    /**
     * sadd
     * @param key
     * @param value
     * @return
     * @throws RedisConnectException
     */
    public Long sadd(String key, String... value) throws RedisConnectException {
        return this.excuteByJedis(j -> j.sadd(key, value));
    }

    /**
     * sismembers
     * @param key
     * @param member
     * @return
     * @throws RedisConnectException
     */
    public Boolean sismembers(String key, String member) throws RedisConnectException {
        return this.excuteByJedis(j -> j.sismember(key, member));
    }

    /**
     * spop
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public String spop(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.spop(key));
    }

    /**
     * smembers
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Set<String> smembers(String key) throws RedisConnectException {
        return this.excuteByJedis(j -> j.smembers(key));
    }

    /**
     * zadd命令
     * @param key
     * @param score
     * @param member
     * @return
     * @throws RedisConnectException
     */
    public Long zadd(String key, Double score, String member) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zadd(key, score, member));
    }

    /**
     * zscore
     * @param key
     * @param member
     * @return
     * @throws RedisConnectException
     */
    public Double zscore(String key, String member) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zscore(key, member));
    }

    /**
     * zrangeByScore 命令
     * @param key
     * @param min
     * @param max
     * @return
     * @throws RedisConnectException
     */
    public Set<String> zrangeByScore(String key, String min, String max) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zrangeByScore(key, min, max));
    }

    /**
     * zremrangeByScore
     * @param key
     * @param start
     * @param end
     * @return
     * @throws RedisConnectException
     */
    public Long zremrangeByScore(String key, String start, String end) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    /**
     * zrem 命令
     * @param key
     * @param members
     * @return
     * @throws RedisConnectException
     */
    public Long zrem(String key, String... members) throws RedisConnectException {
        return this.excuteByJedis(j -> j.zrem(key, members));
    }
}

