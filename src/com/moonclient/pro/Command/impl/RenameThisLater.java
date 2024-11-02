package com.moonclient.pro.Command.impl;

import com.moonclient.pro.Command.Command;
import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public class RenameThisLater
implements Command {
    @Override
    public void execute(String[] args) throws IOException {
        int packets = Integer.parseInt(args[1]);
        int threadcount = Integer.parseInt(args[2]);
    }

    @Override
    public String getUsage() {
        return null;
    }
}
 