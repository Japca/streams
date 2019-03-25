package net.japca.streams;

import net.japca.common.DataMessage;
import net.japca.streams.client.Client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("kafka")
public class KafkaTest {

    @Autowired
    private Client client;

    @Test
    public void send() {
        client.send(new DataMessage().setId(1L).setName("Test Message"));
    }


}
