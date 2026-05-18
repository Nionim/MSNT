package delta.cion.cherry.api.online;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class WhiteList {

	private static boolean ENABLE_WHITELIST = true;

	private static final ArrayList<PlayerInfo> WHITELIST = new ArrayList<>();

	public static boolean getStatus() {
		return ENABLE_WHITELIST;
	}

	public static void setStatus(boolean status) {
		ENABLE_WHITELIST = status;
	}

	public static boolean isWhitelisted(String playerName) {
		for (PlayerInfo playerInfo : WHITELIST) {
			if (playerInfo.name().isBlank()) return false;
			if (playerInfo.name().equals(playerName)) return true;
		}
		return false;
	}

	public static boolean isWhitelisted(UUID playerUUID) {
		for (PlayerInfo playerInfo : WHITELIST) {
			if (playerInfo.uuid() == null) return false;
			if (playerInfo.uuid().equals(playerUUID)) return true;
		}
		return false;
	}

	public static void addToWhitelist(String playerName) {
		if (isWhitelisted(playerName)) return;
		WHITELIST.add(isPlayerOnline(playerName));
	}

	public static void addToWhitelist(UUID playerUUID) {
		if (isWhitelisted(playerUUID)) return;
		WHITELIST.add(isPlayerOnline(playerUUID));
	}

	public static PlayerInfo isPlayerOnline(String playerName) {
		Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
		for (Player player : players) {
			if (player.getUsername().equals(playerName))
				return new PlayerInfo(playerName, player.getUuid(), null);
		}
		return new PlayerInfo(playerName, null, null);
	}

	public static PlayerInfo isPlayerOnline(UUID playerUUID) {
		Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
		for (Player player : players) {
			if (player.getUuid().equals(playerUUID))
				return new PlayerInfo(player.getUsername(), playerUUID, null);
		}
		return new PlayerInfo(null, playerUUID, null);
	}
}
