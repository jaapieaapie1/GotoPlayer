#Goto Player
####Find your friends easily in a bungeecord network
___________

####Commands:
 * /goto username
 
moves a player to the same server as another player.


####Permissions:
 * goto.* : Allows a player to go to everyone
 * goto.username : Allows a player to go to a specific person
 * goto.group : Allows a player to go to every player within a bungeecord group (the groups are managed by the bungeecord config or by your permission plugin)
 
[spigot resource](https://www.spigotmc.org/resources/gotoplayer.77915/)


####Config
```
# Set permissions to true if you want the plugin to only work with permissions
permissions: false

# Servers that no one is allowed to /goto to
denied-servers:
  - secretServer

messages:
  no-perms: "&4You don't have permissions to use this command!"
  no-user: "&4Please state a username after the command!"
  not-online: "&4That player is not online"
  go-to-self: "&4Can't go to yourself"
  moved: "&aMoved you!"
  denied: "&4You are not allowed to join {server}!"
```