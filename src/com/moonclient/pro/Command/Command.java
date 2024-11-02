package com.moonclient.pro.Command;

import java.io.IOException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public interface Command {
    public void execute(String[] var1) throws IOException;

    public String getUsage();
}
 