package org.jaapelst.GotoPlayer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class GotoCommand extends Command implements TabExecutor {
    private final GotoPlayer core;

    public GotoCommand(GotoPlayer core) {
        super("goto");
        this.core = core;


    }

    public void execute(CommandSender commandSender, String[] strings) {

        if(0 >= strings.length) {
            commandSender.sendMessage(new TextComponent(getMessage("no-user")));
        } else {
            ProxyServer proxy = core.getProxy();
            ProxiedPlayer player = proxy.getPlayer(strings[0]);

            if(player == null) {
                commandSender.sendMessage(new TextComponent(getMessage("not-online")));

            } else {

                AtomicBoolean allowed = new AtomicBoolean(false);
                if(this.core.config.getBoolean("permissions") == false) allowed.set(true);
                if(commandSender.hasPermission("goto.*")) allowed.set(true);
                if(commandSender.hasPermission("goto." + player.getName())) allowed.set(true);
                player.getGroups().forEach(group -> {
                    if(commandSender.hasPermission("goto." + group)) allowed.set(true);
                });
                if(!allowed.get()) {
                    commandSender.sendMessage(new TextComponent(getMessage("no-perms")));
                    return;
                }
                ProxiedPlayer self = proxy.getPlayer(commandSender.getName());
                if(self.getUniqueId() == player.getUniqueId()) {
                    commandSender.sendMessage(new TextComponent(getMessage("go-to-self")));
                    return;
                };
                Server server = player.getServer();


                if(this.core.config.getStringList("denied-servers").contains(server.getInfo().getName())) {
                    commandSender.sendMessage(new TextComponent(getMessage("denied").replace("{server}", server.getInfo().getName())));
                    return;
                }
                if(server.getInfo().canAccess(commandSender)) {
                    self.connect(server.getInfo());
                    commandSender.sendMessage(new TextComponent(getMessage("moved")));
                } else {

                    commandSender.sendMessage(new TextComponent(getMessage("denied").replace("{server}", server.getInfo().getName())));
                };

            }
        }
    }

    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {




        Set<String> matches = new HashSet<>();

        ProxyServer proxy = this.core.getProxy();

        Collection<ProxiedPlayer> players = proxy.getPlayers();

        players.forEach(player -> {
            matches.add(player.getName());
        });

        return matches;




    }

    private String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.core.config.getString("messages." + path));
    }
}
