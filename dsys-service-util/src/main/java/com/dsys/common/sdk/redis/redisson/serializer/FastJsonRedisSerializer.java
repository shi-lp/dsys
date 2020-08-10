package com.dsys.common.sdk.redis.redisson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.nio.charset.Charset;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @Package: com.dsys.base.util.serializer
 * @Description：redis定制value的序列化方式
 * @Author: shilp
 * @Date:  2019/12/31 9:54
 * @Modified By:
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer <T> {
    
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;
    
    static {
//        fastjson反序列化报错，添加白名单
        ParserConfig.getGlobalInstance().addAccept("com.dsys.api.bean.*");
//        defaultRedisConfig
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }

}
