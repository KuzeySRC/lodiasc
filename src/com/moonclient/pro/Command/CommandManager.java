package com.moonclient.pro.Command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.moonclient.pro.Command.Command;
import com.moonclient.pro.Command.impl.ChatSpamm;
import com.moonclient.pro.Command.impl.ClickSlotCommand;
import com.moonclient.pro.Command.impl.CommandLock;
import com.moonclient.pro.Command.impl.ExploitFixer;
import com.moonclient.pro.Command.impl.FlyCrash;
import com.moonclient.pro.Command.impl.GmcCommand;
import com.moonclient.pro.Command.impl.Help;
import com.moonclient.pro.Command.impl.LPX;
import com.moonclient.pro.Command.impl.LPX2;
import com.moonclient.pro.Command.impl.NettyCrash;
import com.moonclient.pro.Command.impl.NettyCrash2;
import com.moonclient.pro.Command.impl.SendMsgAll;
import com.moonclient.pro.Command.impl.Test;
import com.moonclient.pro.Command.impl.Toggle;
import com.moonclient.pro.Command.impl.Viaspamm;
import com.moonclient.pro.Command.impl.brands;
import com.moonclient.pro.Command.impl.cspam;
import com.moonclient.pro.Module.ModuleManager;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.Level;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.flag.FeatureFlagSet;

@Environment(value=EnvType.CLIENT)
public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<String, Command>();
    public static final CommandDispatcher<SharedSuggestionProvider> DISPATCHER = new CommandDispatcher();
    public static final SharedSuggestionProvider SOURCE = new SharedSuggestionProvider(){
    

        public Collection<String> getOnlinePlayerNames() {
            return null;
        }
    
        public Collection<String> getAllTeams() {
            return null;
        }
    
        public Stream<ResourceLocation> getAvailableSounds() {
            return null;
        }
    
        public Stream<ResourceLocation> getRecipeNames() {
            return null;
        }
    
        public CompletableFuture<Suggestions> customSuggestion(CommandContext<?> context) {
            return null;
        }
    
        public Set<ResourceKey<Level>> levels() {
            return null;
        }
    
        public RegistryAccess registryAccess() {
            return null;
        }
    
        public FeatureFlagSet enabledFeatures() {
            return null;
        }
    
        public CompletableFuture<Suggestions> suggestRegistryElements(ResourceKey<? extends ResourceKey<?>> registryRef, SharedSuggestionProvider.SharedSuggestionProvider$ElementSuggestionType suggestedIdType, SuggestionsBuilder builder, CommandContext<?> context) {
            return null;
        }
    
        public boolean hasPermission(int level) {
            return false;
        }
    };

    public static void register(String name, Command command) {
        System.out.println("Registering command: " + name);
        commands.put(name.toLowerCase(), command);
    }

    public static void sendmsg(MutableComponent msg) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9LodiasClient§r] §7" + String.valueOf(msg)));
    }

    public static void execute(String content) throws IOException {
        if (content.startsWith("#") || content.startsWith("-") || content.startsWith(".") || content.startsWith("+") || content.startsWith("!")) {
            System.out.println("Command detected: " + content);
            String[] args = content.split(" ");
            String cmd = args[0].substring(1).toLowerCase();
            Command command = commands.get(cmd);
            if (command != null) {
                System.out.println("Executing command: " + cmd);
                command.execute(args);
            } else {
                System.out.println("Command " + cmd + " not found");
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("§b[§9MoonClient] §7 §cCommand " + cmd + " §3 not found: "));
            }
        }
    }

    public static void registerDefaultCommands() {
        ModuleManager moduleManager = new ModuleManager(5L);
        System.out.println("Registering default commands...");
        CommandManager.register("gm", new GmcCommand());
        CommandManager.register("lockchat", new CommandLock());
        CommandManager.register("Lpx", new LPX());
        CommandManager.register("sendmsgall", new SendMsgAll());
        CommandManager.register("clickslot", new ClickSlotCommand());
        CommandManager.register("chatspamm", new ChatSpamm());
        CommandManager.register("viaspamm", new Viaspamm());
        CommandManager.register("vialag", new cspam());
        CommandManager.register("Lpx2", new LPX2());
        CommandManager.register("efcrash", new ExploitFixer());
        CommandManager.register("netty", new NettyCrash());
        CommandManager.register("test", new Test());
        CommandManager.register("help", new Help());
        CommandManager.register("toggle", new Toggle(moduleManager));
        CommandManager.register("netty2", new NettyCrash2());
        CommandManager.register("brands", new brands());
        CommandManager.register("fly1", new FlyCrash());
    }
}
 