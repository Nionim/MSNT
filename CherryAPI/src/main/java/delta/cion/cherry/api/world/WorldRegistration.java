package delta.cion.cherry.api.world;

/*
По сути можно обойтись и без этого, но тогда мир будет доступен только в рамках одного плагина.
Получить его извне будет невозможно.
 */

import net.minestom.server.instance.InstanceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WorldRegistration {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldRegistration.class);

	private static final Map<String, InstanceContainer> DELTA_WORLDS = new HashMap<>();

	public static void registerWorld(String worldName, InstanceContainer world) {
		if (!DELTA_WORLDS.containsKey(worldName)) DELTA_WORLDS.put(worldName, world);
	}

	public static InstanceContainer getWorld(String worldName) {
		if (DELTA_WORLDS.containsKey(worldName)) return DELTA_WORLDS.get(worldName);
		LOGGER.warn("Trying to get unregistered world with name: [{}]", worldName);
		return null;
	}

}
