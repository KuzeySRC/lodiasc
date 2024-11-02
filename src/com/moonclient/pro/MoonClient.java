package com.moonclient.pro;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoonClient
implements ModInitializer {
    public static final String MOD_ID = "lodias";
    public static final Logger LOGGER = LoggerFactory.getLogger("lodias");

    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
    }
}
 