package net.japca.streams;

import com.japca.common.DataMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.japca.streams.client.Client;

/**
 * Created by Jakub krhovják on 3/17/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("rabbit")
public class RabbitTest {

    @Autowired
    private Client client;

    @Test
    public void send() {
        client.send(new DataMessage().setId(1L).setName("Test Message"));
    }


}
