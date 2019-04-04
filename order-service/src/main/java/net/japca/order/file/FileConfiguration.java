package net.japca.order.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * Created by Jakub krhovják on 4/1/19.
 */
@Configuration
public class FileConfiguration {

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageChannel redisFileOutputChannel() {
        return new DirectChannel();
    }


    @Bean
    public LogService logService() {
        return new LogService();
    }

    @Bean
//    @InboundChannelAdapter(channel = "fileInputChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> fileReadingMessageSource() throws URISyntaxException {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("order-service/src/main/resources"));
        source.setFilter(new SimplePatternFileListFilter("*.csv"));
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileInputChannel")
    public MessageHandler processedFiles() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("order-service/src/main/resources/out"));
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setFileNameGenerator(message -> "transfer " + new Random().nextInt(100000));
//        handler.setDeleteSourceFiles(true);
        handler.setExpectReply(false);
        return handler;
    }


    @Transformer(inputChannel = "fileInputChannel", outputChannel = "redisFileOutputChannel")
    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

    @ServiceActivator(inputChannel = "redisFileOutputChannel")
    @Bean
    public RedisQueueOutboundChannelAdapter redisFileOutBoundAdapter() {
        RedisQueueOutboundChannelAdapter queueOutboundChannelAdapter =
                new RedisQueueOutboundChannelAdapter("file-queue", new JedisConnectionFactory());
        queueOutboundChannelAdapter.setSerializer(new StringRedisSerializer());
        return  queueOutboundChannelAdapter;
    }




}
