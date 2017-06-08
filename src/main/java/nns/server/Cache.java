package nns.server;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;

public class Cache {

    //should  use distributed cache instead in the future
    static final Map<String, Channel> registeredUsers = new HashMap<>();
}
