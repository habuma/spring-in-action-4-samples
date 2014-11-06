package cart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	public RedisConnectionFactory redisCF() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, Product> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Product> redis = new RedisTemplate<String, Product>();
		redis.setConnectionFactory(cf);
		return redis;
	}
	
}
