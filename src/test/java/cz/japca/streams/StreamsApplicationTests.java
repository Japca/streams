package cz.japca.streams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.japca.streams.client.Client;
import cz.japca.streams.model.DataMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamsApplicationTests {

    @Autowired
    private Client client;

    @Test
    public void send() {
        DataMessage testMessage = new DataMessage().setId(1L).setName("Test Message");
        client.send(testMessage);
    }


}
