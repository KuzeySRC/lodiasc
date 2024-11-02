package com.moonclient.pro;

import com.moonclient.pro.Command.CommandManager;
import com.moonclient.pro.Module.ModuleManager;
import com.moonclient.pro.Screens.BlockedScreen;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_310;

@Environment(value=EnvType.CLIENT)
public class MoonClientClient
implements ClientModInitializer {
    String prefix = "§9[Lodias+]§b ";
    private static final String SERVER_HOST = "192.168.188.164";
    private static final int SERVER_PORT = 7777;

    public void onInitializeClient() {
        CommandManager.registerDefaultCommands();
        ModuleManager moduleManager = new ModuleManager(4L);
        moduleManager.registerModules();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.field_1724 != null) {
                String username = client.field_1724.method_5477().getString();
                this.checkUser(username);
            }
        });
    }

    private void checkUser(String username) {
        try (Socket socket = new Socket(SERVER_HOST, 7777);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
            out.println("isthere " + username);
            String response = in.readLine();
            System.out.println("Server response: " + response);
            if (response != null && response.toLowerCase().contains("not found")) {
                class_310.method_1551().method_1507(new BlockedScreen());
            } else {
                if (response != null && response.contains("found")) {
                    return;
                }
                if (response == null) {
                    class_310.method_1551().method_1507(new BlockedScreen());
                } else if (response.contains("found") && response.contains("not found")) {
                    // empty if block
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 